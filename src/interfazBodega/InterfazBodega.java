package interfazBodega;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controlador.Controlador;
import InterfazApp.EventoVentana;
import Util.Util;
import interfazVendedor.PanelAgregar_v;
import interfazVendedor.PanelEliminar_v;
import interfazVendedor.PanelModificar_v;
import interfazVendedor.PanelTabla_v;

public class InterfazBodega extends JFrame{

	private PanelAgregar_b pnlAgregar;
	private PanelModificar_b pnlModificar;
	private PanelEliminar_b pnlEliminar;
	private PanelTabla_b pnlTabla;
	private PanelOpcion_b pnlOpcion;
	// Atributo controlador
	Controlador ctrl;
	public InterfazBodega(Controlador ctrl)
	{
		setTitle("Bodega");
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
		pnlOpcion = new PanelOpcion_b(ctrl);
		pnlOpcion.setBounds(10, 10, 775,110);
		pnlAgregar = new PanelAgregar_b(ctrl);
		pnlAgregar.setBounds(10, 130, 775,250);
		pnlModificar = new PanelModificar_b (ctrl);
		pnlModificar.setBounds(10, 130, 775,250);
		pnlEliminar = new PanelEliminar_b(ctrl);
		pnlEliminar.setBounds(10, 130, 775,250);
		pnlTabla = new PanelTabla_b(ctrl);
		pnlTabla.setBounds(10, 500, 775, 180);
		
		
		// Organizar el panel principal.
		getContentPane( ).add(pnlOpcion);
	    getContentPane( ).add(pnlAgregar);
	    getContentPane( ).add(pnlEliminar);
	    getContentPane( ).add(pnlModificar);
	    //getContentPane( ).add(pnlTabla);
	    
	    
	 // Conecta objetos al controlador.
	    ctrl.conectar(pnlAgregar,pnlEliminar,pnlTabla,pnlModificar,pnlOpcion);
	    
	 // Propiedades de la interfaz.
	    setSize(800,720);
	    setResizable(false);
	    
	//  Centrar ventana.
	    Util.centrarVentana(this);
	    
	    EventoVentana oyenteventana = new  EventoVentana();
		addWindowListener(oyenteventana);
		
	}
}
