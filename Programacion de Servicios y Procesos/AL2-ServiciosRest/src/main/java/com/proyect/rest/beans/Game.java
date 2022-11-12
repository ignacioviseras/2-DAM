package com.proyect.rest.beans;

import java.util.Objects;



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


	@Override
	public int hashCode() {
		return Objects.hash(company, id, name, score);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Objects.equals(company, other.company) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(score, other.score);
	}

	
	
	
	
	
	
}