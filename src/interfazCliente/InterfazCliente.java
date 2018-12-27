package interfazCliente;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controlador.Controlador;
import InterfazApp.EventoVentana;
import Util.Util;

public class InterfazCliente extends JFrame{
			
	private PanelAgregar_c pnlAgregar_c;
	private PanelModificar_c pnlModificar_c;
	private PanelEliminar_c pnlEliminar_c;
	private PanelTabla_c pnlTabla_c;
	// Atributo controlador
		Controlador ctrl;
		
		
	public InterfazCliente(Controlador ctrl)
	{
		setTitle("Cliente");
		getContentPane( ).setLayout( null );
		//Color JFrame
		this.getContentPane().setBackground(Color.GRAY.darker().darker().darker());
		
		//Icono JFrame
		Toolkit mipantalla=  Toolkit.getDefaultToolkit();
		Image MiIcono=mipantalla.getImage("imagenes/Icon.png");//Icono
		setIconImage(MiIcono);
		
		// Integra el Controlador. 
		this.ctrl=ctrl;
		
		// Instancia los paneles 
		pnlAgregar_c = new PanelAgregar_c(ctrl);
		pnlAgregar_c.setBounds(10, 10, 775,160);
		pnlModificar_c = new PanelModificar_c (ctrl);
		pnlModificar_c.setBounds(10, 180, 775, 150);
		pnlEliminar_c = new PanelEliminar_c(ctrl);
		pnlEliminar_c.setBounds(10, 340, 775, 150);
		pnlTabla_c = new PanelTabla_c(ctrl);
		pnlTabla_c.setBounds(10, 500, 775, 180);
		
		// Organizar el panel principal. 
	    getContentPane( ).add( pnlAgregar_c);
	    getContentPane( ).add( pnlModificar_c);
	    getContentPane( ).add( pnlEliminar_c);
	    getContentPane( ).add( pnlTabla_c);
	    
	    // Conecta objetos al controlador.
	    ctrl.conectar(pnlAgregar_c, pnlModificar_c, pnlEliminar_c,pnlTabla_c);
	    
	    // Propiedades de la interfaz.
	    setSize(800,720);
	    setResizable(false);
	    
	    //  Centrar ventana.
	    Util.centrarVentana(this);
	    
	    EventoVentana oyenteventana = new  EventoVentana();
		addWindowListener(oyenteventana);
	}
	

}
