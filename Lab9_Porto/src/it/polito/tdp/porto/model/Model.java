package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.alg.cycle.PatonCycleBase;
import org.jgrapht.alg.cycle.UndirectedCycleBase;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	private List<Article> articles;
	private List<Creator> creators;
	private List<Authorship> authorships;
	private PortoDAO dao;
	private SimpleGraph<Creator, DefaultEdge> graph;
	
	public Model() {
		this.dao = new PortoDAO();
	}
	
	public List<Creator> viewCoautori(Creator c){
		List<Creator> coautori = Graphs.neighborListOf(graph, c);
		
		return coautori;
	}
	
	public List<List<Creator>> viewCluster() {
		//CycleDetector<FermataSuLinea, DefaultWeightedEdge> cycleDetector;
		//cycleDetector = new CycleDetector<FermataSuLinea, DefaultWeightedEdge>(graph);
		
		UndirectedCycleBase<Creator, DefaultEdge> superCycleDetector;
		superCycleDetector = new PatonCycleBase<Creator, DefaultEdge>(graph);
		// System.out.println("Inizio");
		List<List<Creator>> cycles = superCycleDetector.findCycleBase();
		// System.out.println("Finito");

		
		return cycles;
	}
	
	public List<Article> viewArticle(Creator a, Creator b){
		List<Article> art = new ArrayList<>();
		
		if(!graph.containsEdge(a, b))
			return art;
		
		List<Authorship> authorA = getListByCreator(a);
		List<Authorship> authorB = getListByCreator(b);
		
		for(Authorship aA : authorA)
			for(Authorship aB : authorB)
				if(aA.getArticle().equals(aB.getArticle()))
					art.add(aA.getArticle());
		
		return articles;
	}
	
	public List<Authorship> getListByCreator(Creator a) {
		List<Authorship> author = new ArrayList<>();
		for(Authorship as : authorships)
			if(as.getCreator().equals(a))
				author.add(as);
		
		return author;				
	}

	public void creaGrafo() {
		
		this.articles = dao.getArticle();
		this.creators = dao.getCreator();
		this.authorships = dao.getAuthorship(creators, articles);
		this.graph = new SimpleGraph<Creator, DefaultEdge>(DefaultEdge.class);
		
		Graphs.addAllVertices(graph,creators);
		// System.out.println(graph.vertexSet());
		
		for(Authorship a : authorships)
			for(Authorship b : authorships)
				if(a.getArticle().equals(b.getArticle()) && !a.getCreator().equals(b.getCreator()))
					graph.addEdge(a.getCreator(), b.getCreator());
		
		// System.out.println(graph.edgeSet());
	}
	
	public List<Creator> getCreatorList() {
		return creators;
	}

	/* public static void main(String[] args) {
		Model m = new Model();
		m.creaGrafo();
		List<Creator> c = m.getCreatorList();
		Creator a = c.get(1);
		Creator b = c.get(12);
		
		System.out.println(m.viewCoautori(a));
		System.out.println(m.viewArticle(a, b));
		for(List<Creator> l : m.viewCluster())
			System.out.println("\nGrande: "+l.size()+"\n"+l);

	}
	*/
}
