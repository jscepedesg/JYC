package InterfazApp;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import interfazBodega.PanelTabla_b;
import interfazCliente.PanelTabla_c;
import interfazProducto.PanelTabla_p;
import interfazVendedor.PanelTabla_v;

public class EventoVentana extends WindowAdapter{

public void windowClosing(WindowEvent e) {

	PanelPrin ventana = new PanelPrin();
	PanelTabla_p hilo= new PanelTabla_p();
	PanelTabla_c hilo2= new PanelTabla_c();
	PanelTabla_v hilo3= new PanelTabla_v();
	PanelTabla_b hilo4= new PanelTabla_b();
	hilo.setpararHilo();
	hilo2.setpararHilo();
	hilo3.setpararHilo();
	hilo4.setpararHilo();
	ventana.setVentana(0);
	//System.out.println("Cerrada");
			
}
}
