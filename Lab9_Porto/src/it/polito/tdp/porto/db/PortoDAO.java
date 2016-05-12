package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.Article;
import it.polito.tdp.porto.model.Authorship;
import it.polito.tdp.porto.model.Creator;

public class PortoDAO {
	
	public List<Creator> getCreator() {
		
		String sql = "SELECT* "
				+ "FROM creator";
		List<Creator> creators = new ArrayList<>();
				
		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				creators.add(new Creator(rs.getInt(1),
										rs.getString(2),
										rs.getString(3)));
			}
			
			return creators;
		} catch (SQLException e) {
			throw new RuntimeException("Errore query", e);
		}
		
	}

	public List<Article> getArticle() {
		
		String sql = "SELECT* "
				+ "FROM article";
		List<Article> articles = new ArrayList<>();
				
		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				articles.add(new Article(rs.getInt(1),
										rs.getInt(2),
										rs.getString(3)));
			}
			
			return articles;
		} catch (SQLException e) {
			throw new RuntimeException("Errore query", e);
		}
		
	}
	
	public List<Authorship> getAuthorship(List<Creator> c, List<Article> a) {
		
		String sql = "SELECT* "
				+ "FROM authorship";
		List<Authorship> authorships = new ArrayList<>();
				
		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				authorships.add(new Authorship(rs.getInt(1),
										a.get(a.indexOf(rs.getInt(2))),
										c.get(c.indexOf(rs.getInt(3)))));
			}
			
			return authorships;
		} catch (SQLException e) {
			throw new RuntimeException("Errore query", e);
		}
		
	}

	

}
