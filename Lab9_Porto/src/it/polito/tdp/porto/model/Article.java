package it.polito.tdp.porto.model;

public class Article {
	
	private int ePrintId;
	private int year;
	private String title;
	
	public Article(int eprintid, int year, String title) {
		super();
		this.ePrintId = eprintid;
		this.year = year;
		this.title = title;
	}
	public Article(int eprintid) {
		this.ePrintId = eprintid;
	}
	public int getEprintid() {
		return ePrintId;
	}
	public void setEprintid(int eprintid) {
		this.ePrintId = eprintid;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ePrintId;
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
		Article other = (Article) obj;
		if (ePrintId != other.ePrintId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("%-10d %4d %s", ePrintId, year, title);
	}
	
	

}
