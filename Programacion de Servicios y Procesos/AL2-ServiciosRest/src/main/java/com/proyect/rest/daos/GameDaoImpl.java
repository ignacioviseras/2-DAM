package com.proyect.rest.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.proyect.rest.beans.Game;


@Component
public class GameDaoImpl implements IntGameDao{
	
	
	private List<Game> list;
	private int id;
	
	
	public GameDaoImpl() {
		 list = new ArrayList<Game>();
		 loadingGameList();
	}

	private void loadingGameList(){
		list.add(new Game(id++,"call of duty", "univision", 11.0));
		list.add(new Game(id++,"sims", "univision2", 12.0));
		list.add(new Game(id++,"warcraft", "univision3", 13.0));
		list.add(new Game(id++,"age of empires II", "univision4", 14.0));
		list.add(new Game(id++,"minecraft", "univision5", 15.0));
	}

	
	@Override
	public List<Game> findAll() {
		return list;
	}

	@Override
	public void newGame(Game g) {
		List<Game> listaux = new ArrayList<Game>();
		for(Game game : listaux)
		{
			if(!(g.getId() == game.getId() || g.getName().equalsIgnoreCase(game.getName()))) {
				g.setId(id++);
				list.add(g);
			}
		}
	}
	
	@Override
	public Game deleteGame(int id) {
		try {
			return list.remove(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("delete -> Persona fuera de rango");
			return null;
		}
	}

	@Override
	public Game modifyGame(Game g) {
		try {
			List<Game> listaux = new ArrayList<Game>();
			Game aux = list.get(g.getId());
			for(Game game : listaux)
			{
				if(!(g.getName().equalsIgnoreCase(game.getName()))) {
					aux.setName(g.getName());
					aux.setCompany(g.getCompany());
					aux.setScore(g.getScore());
					return aux;
				}
			}
			return null;			
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No se pudo añadir");
			return null;
		}
	}

	public Game findById(int posicion) {
		try {
			return list.get(posicion);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("No existe el juego");
			return null;
		}
	}
}