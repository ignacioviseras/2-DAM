package com.proyect.cliente.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.proyect.cliente.beans.Game;

//se da de alta el objeto ServiceProxyMensaje para usar el spring
@Service
public class ServiceProxyGame {
	
	//url del rest para game
	public static final String URL = "http://localhost:8081/game/";
	
	//esto hace las peticiones http
	@Autowired
	private RestTemplate restTemplate;
	
	
	//-----Alta-----
	public Game newGame(Game g) {
		try {
			ResponseEntity<Game> game = restTemplate.postForEntity(URL + "newGame", g, Game.class);
			System.out.println("Cliente: "+ game.getStatusCodeValue());
			return game.getBody();
		} catch (HttpClientErrorException e) {//Erores de cliente -> 400
			System.out.println("No se encontro el juego con id: " + g.getId()+ "\n Tipo de error: "+ e.getStatusCode());
			return null;
		}
	}
	
	//-----Eliminar-----
	public Boolean deleteGame(int id) {
		try {
			restTemplate.delete(URL +id);
			return true;
		} catch (HttpClientErrorException e) {//Erores de cliente -> 400
			System.out.println("No se llego a borar el juego con id: " + id + "\n Tipo de error: "+ e.getStatusCode());
			return null;
		}
	}
	
	//-----Modificar-----
	public Boolean modifyGame(Game g) {
		try {
			restTemplate.put(URL + g.getId(), g, Game.class);
			return true;
		} catch (HttpClientErrorException e) {//Erores de cliente -> 400
			System.out.println("No se llego a modificar el juego con id: " + g.getId() + "\n Tipo de error: "+ e.getStatusCode());
			return false;
		}
	}
	
	//-----Listar todos-----
	public List<Game> findAll() {
		try {
			ResponseEntity<Game[]> response = restTemplate.getForEntity(URL + "findAll",Game[].class);
			Game[] arrayGame = response.getBody();
			return Arrays.asList(arrayGame);
		} catch (HttpClientErrorException e) {
			System.out.println("No se pudo listar los juegos: " + "\n Tipo de error: "+ e.getStatusCode());
			return null;
		}
	}
	
	//-----Listar 1-----
	public Game findById(int id) {
		try {
			ResponseEntity<Game> game = restTemplate.getForEntity(URL + id, Game.class);
			HttpStatus http = game.getStatusCode();
			if(http == HttpStatus.OK) {
				return game.getBody();
			}else {
				return null;
			}
		} catch (HttpClientErrorException e) {//Erores de cliente -> 400
			System.out.println("No se encontro el juego con id: " + id + "\n Tipo de error: "+ e.getStatusCode());
			return null;
		}
	}
	
}
