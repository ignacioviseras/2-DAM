package com.proyect.rest.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.rest.beans.Game;


@Service
public class GameDaoImpl implements IntGameDao{
	
	@Autowired
	private IntGameDao gdao;
	private List<Game> list;
	
	
	
	public GameDaoImpl() {
		 list = new ArrayList<Game>();
		loadingGameList();
	}

	private void loadingGameList(){
		list.add(new Game(1,"call of duty", "univision", 11.0));
		list.add(new Game(2,"sims", "univision2", 12.0));
		list.add(new Game(3,"warcraft", "univision3", 13.0));
		list.add(new Game(4,"age of empires II", "univision4", 14.0));
		list.add(new Game(5,"minecraft", "univision5", 15.0));
	}

	
	@Override
	public List<Game> findAll() {
		
		return gdao.findAll();
	}

	@Override
	public int newGame(Game game) {
		if(list.contains(game)) {
            return 1;
        }
            list.add(game);
		return 0;
	}

	@Override
	public int deleteGame(int id) {
		Game aux = new Game();
		aux.setId(id);
		int pos = list.indexOf(aux);
		if (pos == -1) 
			return 0;
		else
			 list.remove(aux);
		return 1;
	}

	@Override
	public int modifyGame(int id) {
		Game aux = new Game();
		aux.setId(id);
		int pos = list.indexOf(aux);
		if (pos == -1) 
			return 0;
		else
			 list.set(pos, aux);
		return 1;
	}

	@Override
	public Game findById(int id) {
		Game aux = new Game();
		aux.setId(id);
		int pos = list.indexOf(aux);
		if (pos == -1)
			return null;
		else
			return list.get(pos);
	}

}