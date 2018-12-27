package Mundo;

public class Producto {
	
	private int cod_p;
	private String nom_p;
	private int pre_p;
	private String linea;
	private String casa;
	
	public Producto(int cod_p,String nom_p,int pre_p,String linea,String casa)
	{
		this.cod_p=cod_p;
		this.nom_p=nom_p;
		this.pre_p=pre_p;
		this.linea=linea;
		this.casa=casa;
	}

	public int getCod_p() {
		return cod_p;
	}

	public String getNom_p() {
		return nom_p;
	}

	public int getPre_p() {
		return pre_p;
	}

	public String getLinea() {
		return linea;
	}

	public String getCasa() {
		return casa;
	}
	
	

}
