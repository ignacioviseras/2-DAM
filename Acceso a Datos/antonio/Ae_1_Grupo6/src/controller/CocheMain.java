package controller;

import java.io.File;
import java.util.ArrayList;

import beans.Coche;

public class CocheMain {

	public static void main(String[] args) {
		
		File fl = new File("coches.dat");
		
		if (!fl.exists()){ //NO existe
			
			ArrayList<Coche> listaCoches = new ArrayList<>();
		    
		}else {// si existe 
			
			
			
		}

	}

}
