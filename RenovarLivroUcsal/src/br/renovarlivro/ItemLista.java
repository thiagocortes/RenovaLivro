package br.renovarlivro;

public class ItemLista {
	
	private int id;
	private String titulo;
	private String autor;
	private String isdn;
	private String data_entrega;
	 

	public ItemLista(int id,String titulo,String autor,String isdn) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.isdn = isdn;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getIsdn() {
		return isdn;
	}
	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}
	public String getData_entrega() {
		return data_entrega;
	}
	public void setData_entrega(String data_entrega) {
		this.data_entrega = data_entrega;
	}
	 
}
