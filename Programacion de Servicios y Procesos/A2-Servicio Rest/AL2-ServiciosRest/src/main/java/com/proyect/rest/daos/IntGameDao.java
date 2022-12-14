package com.proyect.rest.daos;

import java.util.List;

import com.proyect.rest.beans.Game;

public interface IntGameDao {
	
	List<Game> findAll();
	Game deleteGame (int id);
	Game findById (int id);
	Game newGame (Game g);
	Game modifyGame (Game g);
	

}