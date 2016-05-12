package it.polito.tdp.porto.model;

import java.util.List;

import org.jgrapht.Graphs;
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
	

	public void creaGrafo() {
		
		this.articles = dao.getArticle();
		this.creators = dao.getCreator();
		this.authorships = dao.getAuthorship(creators, articles);
		this.graph = new SimpleGraph<Creator, DefaultEdge>(DefaultEdge.class);
		
		Graphs.addAllVertices(graph,creators);
		
		for(Authorship a : authorships)
			for(Authorship b : authorships)
				if(a.getArticle().equals(b.getArticle()) && !a.getCreator().equals(b.getCreator()))
					graph.addEdge(a.getCreator(), b.getCreator());
		
	}
	
	public List<Creator> getCreatorList() {
		return creators;
	}


	
}
