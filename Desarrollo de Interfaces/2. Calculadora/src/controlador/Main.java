package controlador;

import vista.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		VentanaPrincipal ventanaPrincip = new VentanaPrincipal();

		//Comunicamos el manejadorEventos con la ventana
		//De esta manera cuando se pulse un boton podremos controlar sus acciones
		ManejadorEventos manejador = new ManejadorEventos(ventanaPrincip);
		ventanaPrincip.establecerManejador(manejador);
	}

}
