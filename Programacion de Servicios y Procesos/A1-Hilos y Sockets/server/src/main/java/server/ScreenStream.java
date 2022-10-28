package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ScreenStream {

	private InputStreamReader charReader;
	private BufferedReader lineReader;

	public ScreenStream(InputStream inputStream) {

		this.charReader = new InputStreamReader(inputStream);
		this.lineReader = new BufferedReader(charReader);
	}

	/**
	 * @return the charReader
	 */
	public InputStreamReader getCharReader() {
		return charReader;
	}

	/**
	 * @param charReader the charReader to set
	 */
	public void setCharReader(InputStreamReader inputStreamReader) {
		this.charReader = inputStreamReader;
	}

	/**
	 * @return the lineReader
	 */
	public BufferedReader getLineReader() {
		return lineReader;
	}

	/**
	 * @param lineReader the lineReader to set
	 */
	public void setLineReader(BufferedReader bufferedReader) {
		this.lineReader = bufferedReader;
	}

	/**
	 * Método para cerrar las entradas de flujo de datos.
	 * @throws IOException
	 */
	public void close() throws IOException {
		lineReader.close();
		charReader.close();
	}

	/**
	 * El programa se quedará parado hasta que el cliente envie una string
	 * 
	 * @return Una String enviada del cliente
	 * @throws IOException
	 */
	public String read() throws IOException {
		return lineReader.readLine();
	}
}