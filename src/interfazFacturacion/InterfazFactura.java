package interfazFacturacion;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controlador.Controlador;
import InterfazApp.EventoVentana;
import Util.Util;
import interfazCuentaT.PanelTablaT;

public class InterfazFactura extends JFrame
{
	

	private PanelPrincipalFactura pnlPrincipalFactura;
	// Atributo controlador
	Controlador ctrl;
	
	public InterfazFactura(Controlador ctrl)
	{
		setTitle("Facturación");
		getContentPane( ).setLayout( null );
		//Color JFrame
		this.getContentPane().setBackground(Color.GRAY.darker().darker().darker());
		
		//Icono JFrame
		Toolkit mipantalla=  Toolkit.getDefaultToolkit();
		Image MiIcono=mipantalla.getImage("imagenes/Icon.png");//Icono
		setIconImage(MiIcono);
		
		// Integra el Controlador. 
		this.ctrl=ctrl;
		
		pnlPrincipalFactura = new PanelPrincipalFactura(ctrl);
		pnlPrincipalFactura.setBounds(10, 10, 475, 120);
		
		// Organizar el panel principal. 
	    getContentPane( ).add(pnlPrincipalFactura);
	    
	 // Conecta objetos al controlador.
	    ctrl.conectar(pnlPrincipalFactura);
	    
	 // Propiedades de la interfaz.
	    setSize(500,170);
	    setResizable(false);
	    
	//  Centrar ventana.
	    Util.centrarVentana(this);
	    
	    EventoVentana oyenteventana = new  EventoVentana();
		addWindowListener(oyenteventana);
		
	}
	
}
