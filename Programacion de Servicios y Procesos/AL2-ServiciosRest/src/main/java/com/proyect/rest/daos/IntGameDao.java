package com.proyect.rest.daos;

import java.util.List;

import com.proyect.rest.beans.Game;

public interface IntGameDao {
	
	List<Game> findAll();
	int newGame (Game game);
	int deleteGame (int id);
	int modifyGame (int id);
	Game findById (int id);
	

}