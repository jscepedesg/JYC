package Mundo;

public class Bodega {
	
	private int id_p;
	private String nombre_p;
	private String linea_p;
	private int can;
	
	public Bodega(int id_p,String nombre_p,String linea_p,int can)
	{
		this.id_p = id_p;
		this.nombre_p = nombre_p;
		this.linea_p = linea_p;
		this.can = can;
	}

	public int getId_p() {
		return id_p;
	}

	public String getNombre_p() {
		return nombre_p;
	}

	public String getLinea_p() {
		return linea_p;
	}

	public int getCan() {
		return can;
	}
	
	

}
