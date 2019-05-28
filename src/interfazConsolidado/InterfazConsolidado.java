package interfazConsolidado;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controlador.Controlador;
import InterfazApp.EventoVentana;
import Util.Util;
import interfazCuentaT.PanelTablaT;

public class InterfazConsolidado extends JFrame
{
	

	private PanelPrincipalConsolidado pnlPrincipalConsolidado;
	// Atributo controlador
	Controlador ctrl;
	
	public InterfazConsolidado(Controlador ctrl)
	{
		setTitle("Consolidado");
		getContentPane( ).setLayout( null );
		//Color JFrame
		this.getContentPane().setBackground(Color.GRAY.darker().darker().darker());
		
		//Icono JFrame
		Toolkit mipantalla=  Toolkit.getDefaultToolkit();
		Image MiIcono=mipantalla.getImage("imagenes/Icon.png");//Icono
		setIconImage(MiIcono);
		
		// Integra el Controlador. 
		this.ctrl=ctrl;
		
		pnlPrincipalConsolidado = new PanelPrincipalConsolidado(ctrl);
		pnlPrincipalConsolidado.setBounds(10, 10, 475, 220);
		
		// Organizar el panel principal. 
	    getContentPane( ).add(pnlPrincipalConsolidado);
	    
	 // Conecta objetos al controlador.
	    ctrl.conectar(pnlPrincipalConsolidado);
	    
	 // Propiedades de la interfaz.
	    setSize(500,270);
	    setResizable(false);
	    
	//  Centrar ventana.
	    Util.centrarVentana(this);
	    
	    EventoVentana oyenteventana = new  EventoVentana();
		addWindowListener(oyenteventana);
		
	}
	
}
