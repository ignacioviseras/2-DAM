package com.proyect.rest.beans;




public class Game {
	int id;
	String name;
	String company;
	Double score;
	
	public Game() {
		super();
		
	}


	public Game(int id, String name, String company, Double score) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.score = score;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public Double getScore() {
		return score;
	}


	public void setScore(Double score) {
		this.score = score;
	}


	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", company=" + company + ", score=" + score + "]";
	}
}