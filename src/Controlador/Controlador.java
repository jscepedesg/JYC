package Controlador;

import java.util.Calendar;

import Mundo.Mundo;
import interfazBodega.PanelAgregar_b;
import interfazBodega.PanelEliminar_b;
import interfazBodega.PanelModificar_b;
import interfazBodega.PanelOpcion_b;
import interfazBodega.PanelTabla_b;
import interfazCliente.PanelAgregar_c;
import interfazCliente.PanelEliminar_c;
import interfazCliente.PanelModificar_c;
import interfazCliente.PanelTabla_c;
import interfazCuentaT.PanelTablaT;
import interfazProducto.PanelAgregar_p;
import interfazProducto.PanelEliminar_p;
import interfazProducto.PanelModificar_p;
import interfazProducto.PanelTabla_p;
import interfazVendedor.PanelAgregar_v;
import interfazVendedor.PanelEliminar_v;
import interfazVendedor.PanelModificar_v;
import interfazVendedor.PanelTabla_v;


public class Controlador {

	// Atributos
	private  Mundo mundo;
	
	private PanelAgregar_c pnlAgregar_c;
	private PanelModificar_c pnlModificar_c;
	private PanelEliminar_c pnlEliminar_c;
	private PanelTabla_c pnlTabla_c;
	private PanelAgregar_p pnlAgregar_p;
	private PanelModificar_p pnlModificar_p;
	private PanelEliminar_p pnlEliminar_p;
	private PanelTabla_p pnlTabla_p;
	private PanelTablaT pnlTablaT;
	private PanelAgregar_v pnlAgregar_v;
	private PanelModificar_v pnlModificar_v;
	private PanelEliminar_v pnlEliminar_v;
	private PanelTabla_v pnlTabla_v;
	private PanelAgregar_b pnlAgregar_b;
	private PanelTabla_b pnlTabla_b;
	private PanelModificar_b pnlModificar_b;
	private PanelEliminar_b pnlEliminar_b;
	private PanelOpcion_b pnlOpcion_b;
	public Controlador()
	{
		mundo = new Mundo();
	}
	public void conectar(PanelAgregar_c pnlAgregar_c, PanelModificar_c pnlModificar_c, PanelEliminar_c pnlEliminar_c,PanelTabla_c pnlTabla_c) 
	{
		this.pnlAgregar_c=pnlAgregar_c;
		this.pnlModificar_c=pnlModificar_c;
		this.pnlEliminar_c=pnlEliminar_c;
		this.pnlTabla_c=pnlTabla_c;
	}
	public void conectar(PanelAgregar_p pnlAgregar_p, PanelModificar_p pnlModificar_p, PanelEliminar_p pnlEliminar_p,PanelTabla_p pnlTabla_p) 
	{
		this.pnlAgregar_p=pnlAgregar_p;
		this.pnlModificar_p=pnlModificar_p;
		this.pnlEliminar_p=pnlEliminar_p;
		this.pnlTabla_p=pnlTabla_p;
	}
	public void conectar(PanelTablaT pnlTablaT) 
	{
		this.pnlTablaT=pnlTablaT;
	}	
	public void conectar(PanelAgregar_v pnlAgregar_v, PanelModificar_v pnlModificar_v, PanelEliminar_v pnlEliminar_v,
			PanelTabla_v pnlTabla_v) 
	{
		this.pnlAgregar_v=pnlAgregar_v;
		this.pnlModificar_v=pnlModificar_v;
		this.pnlEliminar_v=pnlEliminar_v;
		this.pnlTabla_v=pnlTabla_v;
	}
	public void conectar(PanelAgregar_b pnlAgregar_b,PanelEliminar_b pnlEliminar_b, PanelTabla_b pnlTabla_b, PanelModificar_b pnlModificar_b
			,PanelOpcion_b pnlOpcion_b) 
	{
		this.pnlAgregar_b=pnlAgregar_b;
		this.pnlModificar_b=pnlModificar_b;
		this.pnlEliminar_b=pnlEliminar_b;
		this.pnlTabla_b=pnlTabla_b;
		this.pnlOpcion_b=pnlOpcion_b;
	}
	public void setCrearProducto(String cod,String nom,String valor,String linea,String casa)
	{
		mundo.setCrearProducto(cod, nom, valor, linea, casa);
	}
	public void setCrearCliente(String id,String nom_r,String nom_c,String apel_c,String dire_c,String tel,String correo,String dia)
	{
		mundo.setCrearCliente(id, nom_r, nom_c, apel_c, dire_c,tel,correo,dia);
	}
	
	public void setCrearVendedor(String cod, String nom, String apel, String ruta, String tele, String correo) 
	{
		mundo.setCrearVendedor(cod, nom, apel, ruta, tele,correo);
	}
	public void setAgregarBodega()
	{
		
	}
	public void setOpcionPanelBodega(int numero)
	{
		if(numero == 1)
		{
			pnlAgregar_b.setEstado(true);
			pnlModificar_b.setEstado(false);
			pnlEliminar_b.setEstado(false);
		}
		else if(numero == 2)
		{
			pnlAgregar_b.setEstado(false);
			pnlModificar_b.setEstado(true);
			pnlEliminar_b.setEstado(false);
		}
		else if(numero == 3)
		{
			pnlAgregar_b.setEstado(false);
			pnlModificar_b.setEstado(false);
			pnlEliminar_b.setEstado(true);
		}
	}
}
