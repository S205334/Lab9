package it.polito.tdp.porto.model;


public class Authorship {
	
	private int idAuthorship;
	private Article article;
	private Creator creator;
	
	public Authorship(int idAuthorship, Article article, Creator creator) {
		super();
		this.idAuthorship = idAuthorship;
		this.article = article;
		this.creator = creator;
	}

	public int getIdAuthorship() {
		return idAuthorship;
	}

	public void setIdAuthorship(int idAuthorship) {
		this.idAuthorship = idAuthorship;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAuthorship;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authorship other = (Authorship) obj;
		if (idAuthorship != other.idAuthorship)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Authorship [idAuthorship=" + idAuthorship + ", article=" + article + ", creator=" + creator + "]";
	}
	
	

}
