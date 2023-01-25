package com.proyect.rest.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.proyect.rest.beans.Game;
import com.proyect.rest.daos.GameDaoImpl;


@RestController
public class GameController {
	
	@Autowired
	private GameDaoImpl gdao;
	
	//----Buscar1----
	@GetMapping(path="game/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> findById(@PathVariable("id") int id){
		System.out.println("Serching game...con ID: " + id);
		Game g= gdao.findById(id);//devuelve un juego en caso de q exista
		System.out.println(g);
		if(g !=null) {
			return new ResponseEntity<Game>(g,HttpStatus.OK);//http 200 informa q todo esta bn
		}else {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);//404 informa de un error del serv
		}
		
		
	}
	
	//----listar todos----
	@GetMapping(path="game/findAll", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Game>> findAll(){
		System.out.println("Buscando toda la lista");
		List<Game> g = null;
		g = gdao.findAll();
		if(g !=null) {
			return new ResponseEntity<List<Game>>(g,HttpStatus.OK);
		}else {
			return new ResponseEntity<List<Game>>(HttpStatus.NOT_FOUND);
		}		
	}
	
	//----Borrar----
	@DeleteMapping(path="game/{id}")
	public ResponseEntity<Game> deleteGame(@PathVariable("id") int id){
		System.out.println("ID del juego que se borrara: " + id);
		Game g = gdao.deleteGame(id);
		System.out.println(g);
		if(g != null) {
			return new ResponseEntity<Game>(g, HttpStatus.OK);
		}else {
			return new ResponseEntity<Game>(g, HttpStatus.NOT_FOUND);
		}
	}

	//----Modificar----
	@PutMapping(path="game/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> modifyGame(@PathVariable("id")int id, @RequestBody Game g){
		System.out.println("juego q se modificara" + id);
		System.out.println("Nuevos datos " + g);
		g.setId(id);
		Game gmodif = gdao.modifyGame(g);
		if(gmodif != null) {
			return new ResponseEntity<Game>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
		}
	}
	
	//----Añadir----
	@PostMapping(path="game/newGame", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> newGame(@RequestBody Game g){
		System.out.println("Se añadira el juego: " + g);
		gdao.newGame(g);
		return new ResponseEntity<Game>(g, HttpStatus.CREATED);//201 avisa de q se creo con exito
	}
	
	
	@GetMapping(value = "inicio")
	public String obtenerMensaje() {
		return "Se esta cargando con exito";
	}
}
