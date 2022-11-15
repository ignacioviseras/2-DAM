package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.ManejadorEventos;

public class VentanaPrincipal extends JFrame{
	
	private JLabel icono, numero1, numero2, resultado, valorResultado;
	private JTextField txtN1, txtN2;
	private JButton btnSuma, btnResta, btnMulti, btnDiv, btnRaiz2, btnRaiz3;
	
	
	public VentanaPrincipal() {
		//-----Creacion de ventana-----
		setSize(320, 476);//dimensiones ventana
		setLocationRelativeTo(null);//ubicacion
		setDefaultCloseOperation(EXIT_ON_CLOSE);//se cierra el programa al pulsar la x
		setTitle("Calculadora");//titulo
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/fotos/solucion.png"));//icono app
		setResizable(false);//que no se pueda cambiar el tamaño
		setLayout(null);//desactivamos el layout
		getContentPane().setBackground(new Color(90, 141, 61));
		
		inicializarComponentes();//añadimos los componentes
		setVisible(true);//se hace visible la ventana
	}
	
	private void inicializarComponentes() {
		//icono inicio
		icono = new JLabel (new ImageIcon("src/fotos/inicio.gif"));//foto inicio
		icono.setBounds(98, 6, 112, 112);//ubicacion / tamaño
		add(icono);//se añade
		
		//numero1
		numero1 = new JLabel("Numero 1");//creacion Texto numero1
		numero1.setBounds(58, 144, 69, 14);//ubicacion / tamaño
		numero1.setFont(new Font(null, Font.BOLD, 14));//tamaño texto
		numero1.setForeground(new Color(0, 0, 0));//color texto -> negro
		add(numero1);//se añade
		
		
		//CajaNumero1
		txtN1 = new JTextField();//creacion Cajatexto numero1
		txtN1.setBounds(169, 144, 79, 20);//ubicacion / tamaño
		txtN1.requestFocus();//hacemos que este seleccionada la caja
		add(txtN1);//se añade
		
		//numero2
		numero2 = new JLabel("Numero 2");//creacion Texto numero2
		numero2.setBounds(58, 185, 69, 14);//ubicacion / tamaño
		numero2.setFont(new Font(null, Font.BOLD, 14));//tamaño texto
		numero2.setForeground(new Color(0, 0, 0));//color texto -> negro
		add(numero2);//se añade
		
		//CajaNumero2
		txtN2 = new JTextField();//creacion cajatexto numero1
		txtN2.setBounds(169, 185, 79, 20);//ubicacion / tamaño
		add(txtN2);//se añade
		
		
		//-----Botones-----
		//botonSuma
		btnSuma = new JButton("Sumar");
		btnSuma.setBounds(58, 251, 82, 23);
		btnSuma.setBackground(new Color(184, 189, 122));
		add(btnSuma);
		
		//botonResta
		btnResta = new JButton("Restar");
		btnResta.setBounds(169, 251, 82, 23);
		btnResta.setBackground(new Color(184, 189, 122));
		add(btnResta);
		
		//botonMultiplicacion
		btnMulti = new JButton("Múltiplicar");
		btnMulti.setBounds(58, 285, 82, 23);
		btnMulti.setFont(new Font("Tahoma", ABORT, 11));
		btnMulti.setBackground(new Color(184, 189, 122));
		add(btnMulti);
		
		//botonDivision
		btnDiv = new JButton("Dividir");
		btnDiv.setBounds(169, 285, 82, 23);
		btnDiv.setBackground(new Color(184, 189, 122));
		add(btnDiv);
		
		//botonRaiz2
		btnRaiz2 = new JButton("Raiz 2");
		btnRaiz2.setBounds(58, 319, 82, 23);
		btnRaiz2.setBackground(new Color(184, 189, 122));
		add(btnRaiz2);
		
		//botonRaiz3
		btnRaiz3 = new JButton("Raiz 3");
		btnRaiz3.setBounds(169, 319, 82, 23);
		btnRaiz3.setBackground(new Color(184, 189, 122));
		add(btnRaiz3);
		
		//-----Resultado-----
		resultado = new JLabel("Resultado");
		resultado.setBounds(119, 370, 75, 14);
		resultado.setHorizontalAlignment(SwingConstants.CENTER);
		resultado.setFont(new Font(null, Font.BOLD, 14));//tamaño texto
		resultado.setForeground(new Color(0, 0, 0));//color texto -> negro
		add(resultado);
		
		valorResultado = new JLabel("");
		valorResultado.setBounds(125, 395, 60, 14);
		valorResultado.setHorizontalAlignment(SwingConstants.CENTER);
		valorResultado.setFont(new Font(null, Font.BOLD, 14));//tamaño texto
		valorResultado.setForeground(new Color(0, 0, 0));//color texto -> negro
		add(valorResultado);
		
	}

	
	public void establecerManejador(ManejadorEventos manejador) {
		btnSuma.addActionListener(manejador);
		btnResta.addActionListener(manejador);
		btnMulti.addActionListener(manejador);
		btnDiv.addActionListener(manejador);
		btnRaiz2.addActionListener(manejador);
		btnRaiz3.addActionListener(manejador);
	}
	
	
	//-----Gets y sets CajasdeTexto
	public JTextField getTxtN1() {
		return txtN1;
	}
	
	public void setTxtN1(JTextField txtN1) {
		this.txtN1 = txtN1;
	}
	
	public JTextField getTxtN2() {
		return txtN2;
	}
	
	public void setTxtN2(JTextField txtN2) {
		this.txtN2 = txtN2;
	}
	
	
	//-----Gets botones-----
	public JButton getBtnSuma() {
		return btnSuma;
	}


	public JButton getBtnResta() {
		return btnResta;
	}

	public JButton getBtnMulti() {
		return btnMulti;
	}

	public JButton getBtnDiv() {
		return btnDiv;
	}

	public JButton getBtnRaiz2() {
		return btnRaiz2;
	}

	public JButton getBtnRaiz3() {
		return btnRaiz3;
	}

	
	//-----Get Resultado-----
	
	public JLabel getValorResultado() {
		return valorResultado;
	}
	
	
}
