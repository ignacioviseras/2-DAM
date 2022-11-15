package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import vista.VentanaPrincipal;

public class ManejadorEventos implements ActionListener{

	private VentanaPrincipal ventana;
	
	public ManejadorEventos(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}
	
	//validar que es un double
	public Boolean isNumeric(String str) {
		boolean result;
		
		try {
			Double.parseDouble(str);
			result = true;
		}catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	
	//validar campo
	public Boolean validarcampos(Double numero) {
		//Pattern p = Pattern.compile("^[0-9]+?([.][0-9]{2})?$");
		Pattern p = Pattern.compile("^[0-9]+([.][0-9]+)?$");
		Matcher m = p.matcher(numero.toString());
		//int DoubleToInt;
		
		//if((DoubleToInt = Integer.parseInt(numero.toString())) = true)
			//return true;
		return m.matches();
	}
	
	
	//accion de los botones
	@Override
	public void actionPerformed(ActionEvent e) {
		//Double n1 = 0.00;
		//Double n2 = 0.00;
		
		//botonSuma
		if(e.getSource() == ventana.getBtnSuma()) {
			//si esta vacia la caja de texto
			if(ventana.getTxtN1().getText().isEmpty() || ventana.getTxtN2().getText().isEmpty()) 
				JOptionPane.showMessageDialog(null, "Campo vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);//mensaje de info
			else {
				//validamos que sea un numero
				if((isNumeric(ventana.getTxtN1().getText()) && isNumeric(ventana.getTxtN2().getText()))) {
					Double n1 = Double.parseDouble(ventana.getTxtN1().getText());
					Double n2 = Double.parseDouble(ventana.getTxtN2().getText());
					
					//validamos que cumpla el filtro regex
					if(validarcampos(n1) && validarcampos(n2)) {
						ventana.getValorResultado().setText(suma(n1, n2).toString());//hacemos la suma y la devolvemos
						ventana.getTxtN1().setText(null);//limpiamos los campos
						ventana.getTxtN2().setText(null);//limpiamos los campos
						ventana.getTxtN1().requestFocus();//marcamos la caja1
					}else {//error de formato
						JOptionPane.showMessageDialog(null, "Error de formato", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {//error al ser algo q no es un numero
					JOptionPane.showMessageDialog(null, "Tienes que introducir numeros", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		
		//botonResta
		if(e.getSource() == ventana.getBtnResta()) {
				//si esta vacia la caja de texto
				if(ventana.getTxtN1().getText().isEmpty() || ventana.getTxtN2().getText().isEmpty()) 
					JOptionPane.showMessageDialog(null, "Campo vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);//mensaje de info
				else {
					//validamos que sea un numero
					if((isNumeric(ventana.getTxtN1().getText()) && isNumeric(ventana.getTxtN2().getText()))) {
						Double n1 = Double.parseDouble(ventana.getTxtN1().getText());
						Double n2 = Double.parseDouble(ventana.getTxtN2().getText());
						
						//validamos que cumpla el filtro regex
						if(validarcampos(n1) && validarcampos(n2)) {
							ventana.getValorResultado().setText(resta(n1, n2).toString());//hacemos la resta y la devolvemos
							ventana.getTxtN1().setText(null);//limpiamos los campos
							ventana.getTxtN2().setText(null);//limpiamos los campos
							ventana.getTxtN1().requestFocus();//marcamos la caja1
						}else {//error de formato
							JOptionPane.showMessageDialog(null, "Error de formato", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
						}
					}else {//error al ser algo q no es un numero
						JOptionPane.showMessageDialog(null, "Tienes que introducir numeros", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		
		
		//botonMultiplicacion
		if(e.getSource() == ventana.getBtnMulti()) {
				//si esta vacia la caja de texto
				if(ventana.getTxtN1().getText().isEmpty() || ventana.getTxtN2().getText().isEmpty()) 
					JOptionPane.showMessageDialog(null, "Campo vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);//mensaje de info
				else {
					//validamos que sea un numero
					if((isNumeric(ventana.getTxtN1().getText()) && isNumeric(ventana.getTxtN2().getText()))) {
						Double n1 = Double.parseDouble(ventana.getTxtN1().getText());
						Double n2 = Double.parseDouble(ventana.getTxtN2().getText());
						
						//validamos que cumpla el filtro regex
						if(validarcampos(n1) && validarcampos(n2)) {
							ventana.getValorResultado().setText(multi(n1, n2).toString());//hacemos la multiplicacion y la devolvemos
							ventana.getTxtN1().setText(null);//limpiamos los campos
							ventana.getTxtN2().setText(null);//limpiamos los campos
							ventana.getTxtN1().requestFocus();//marcamos la caja1
						}else {//error de formato
							JOptionPane.showMessageDialog(null, "Error de formato", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
						}
					}else {//error al ser algo q no es un numero
						JOptionPane.showMessageDialog(null, "Tienes que introducir numeros", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		
		
		//botonDivision
		if(e.getSource() == ventana.getBtnDiv()) {
				//si esta vacia la caja de texto
				if(ventana.getTxtN1().getText().isEmpty() || ventana.getTxtN2().getText().isEmpty()) 
					JOptionPane.showMessageDialog(null, "Campo vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);//mensaje de info
				else {
					//validamos que sea un numero
					if((isNumeric(ventana.getTxtN1().getText()) && isNumeric(ventana.getTxtN2().getText()))) {
						Double n1 = Double.parseDouble(ventana.getTxtN1().getText());
						Double n2 = Double.parseDouble(ventana.getTxtN2().getText());
						
						//validamos que cumpla el filtro regex
						if(validarcampos(n1) && validarcampos(n2)) {
							ventana.getValorResultado().setText(div(n1, n2).toString());//hacemos la suma y la devolvemos
							ventana.getTxtN1().setText(null);//limpiamos los campos
							ventana.getTxtN2().setText(null);//limpiamos los campos
							ventana.getTxtN1().requestFocus();//marcamos la caja1
						}else {//error de formato
							JOptionPane.showMessageDialog(null, "Error de formato", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
						}
					}else {//error al ser algo q no es un numero
						JOptionPane.showMessageDialog(null, "Tienes que introducir numeros", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		
		
		//botonRaiz2
		if(e.getSource() == ventana.getBtnRaiz2()) {
			JOptionPane.showMessageDialog(null, "Funcionalidad no disponible", "Raiz Cuadrada", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		//botonRaiz3
		if(e.getSource() == ventana.getBtnRaiz3()) {
			String contraseña = JOptionPane.showInputDialog(null, "Contraseña", "Input", JOptionPane.QUESTION_MESSAGE);
			Pattern p = Pattern.compile("asd");//Contraseña real
			Matcher m = p.matcher(contraseña);//crea un comprobador para ver si es la misma contraseña
			
			if(m.find()) {//comprueba si es la misma contraseña
				//JOptionPane.showMessageDialog(null, "Contraseña Correcta", "Contraseña", JOptionPane.INFORMATION_MESSAGE);
				String valor = JOptionPane.showInputDialog(null, "Introduce un numero", "Input", JOptionPane.QUESTION_MESSAGE);
				if(isNumeric(valor)) {
					if(validarcampos(Double.parseDouble(valor))) {
						ventana.getValorResultado().setText(raiz3(Double.parseDouble(valor)).toString());						
					}else {//error de formato
						JOptionPane.showMessageDialog(null, "Error de formato", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {//error al ser algo q no es un numero
					JOptionPane.showMessageDialog(null, "Tienes que introducir numeros", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Contraseña Incorrecta", "Contraseña", JOptionPane.INFORMATION_MESSAGE);
			}
				
		}
		
		
	}
	
	private Double suma(double n1, double n2) {
		return n1 + n2;
	}

	private Double resta(double n1, double n2) {
		return n1 - n2;
	}
	
	private Double multi(double n1, double n2) {
		return n1 * n2;
	}
	
	private Double div(double n1, double n2) {
		return n1 / n2;
	}
	
	private Double raiz3(double n1) {
		return Math.cbrt(n1);
	}
}
