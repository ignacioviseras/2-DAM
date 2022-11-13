package com.proyect.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.rest.beans.Game;
import com.proyect.rest.daos.GameDaoImpl;


@RestController
public class GameController {
	
	@Autowired
	private GameDaoImpl gdao;
	
	@GetMapping(path="game/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> entity(@PathVariable("id")int id){
		System.out.println("Serching game...con ID: " + id);
		Game g= gdao.findById(id);
		System.out.println(g);
		if(g !=null) {
			return new ResponseEntity<Game>(g,HttpStatus.OK);
		}else {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
		}
		
		
	}

}
