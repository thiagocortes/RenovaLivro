package br.model;

public class LivroModel {
	private String titulo;
	private String autor;
	private String data;
	private int devolvido;
	private int isdn;
	
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getDevolvido() {
		return devolvido;
	}
	public void setDevolvido(int devolvido) {
		this.devolvido = devolvido;
	}
	public int getIsdn() {
		return isdn;
	}
	public void setIsdn(int isdn) {
		this.isdn = isdn;
	}

}
