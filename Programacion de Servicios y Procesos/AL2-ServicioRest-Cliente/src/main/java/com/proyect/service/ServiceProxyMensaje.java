package com.proyect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//se da de alta el objeto ServiceProxyMensaje para usar el spring
@Service
public class ServiceProxyMensaje {

	//damos la url que tiene nuestro servidor
	public static final String URL = "http://localhost:8081/";
	
	
	//esto hace las peticiones http
	@Autowired
	private RestTemplate restTemplate;
	
	/**esto se utiliza para saber la direccion q se enviara al serv*/
	public String obtenerCadena(String path) {
		
		String pathFinal = URL + path;
		//String.class se utiliza como almacenamiento
		String mensaje = restTemplate.getForObject(pathFinal, String.class);
		return mensaje;
	}
}
