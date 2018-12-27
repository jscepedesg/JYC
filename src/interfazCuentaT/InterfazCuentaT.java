package interfazCuentaT;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controlador.Controlador;
import InterfazApp.EventoVentana;
import Util.Util;
import interfazProducto.PanelTabla_p;

public class InterfazCuentaT extends JFrame{

	private PanelTablaT pnlTablaT;
	// Atributo controlador
	Controlador ctrl;
	
	public InterfazCuentaT(Controlador ctrl)
	{
		setTitle("Contabilidad");
		getContentPane( ).setLayout( null );
		//Color JFrame
		this.getContentPane().setBackground(Color.GRAY.darker().darker().darker());
		
		//Icono JFrame
		Toolkit mipantalla=  Toolkit.getDefaultToolkit();
		Image MiIcono=mipantalla.getImage("imagenes/Icon.png");//Icono
		setIconImage(MiIcono);
		
		// Integra el Controlador. 
		this.ctrl=ctrl;
		
		pnlTablaT = new PanelTablaT(ctrl);
		pnlTablaT.setBounds(10, 10, 775, 670);
		
		// Organizar el panel principal. 
	    getContentPane( ).add(pnlTablaT);
	    
	 // Conecta objetos al controlador.
	    ctrl.conectar(pnlTablaT);
	    
	 // Propiedades de la interfaz.
	    setSize(800,720);
	    setResizable(false);
	    
	//  Centrar ventana.
	    Util.centrarVentana(this);
	    
	    EventoVentana oyenteventana = new  EventoVentana();
		addWindowListener(oyenteventana);
		
	}
	
}
