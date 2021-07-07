package br.com.habernate01.GerarPDF;

public class Colaborador {
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Colaborador [nome=" + nome + "]";
	}
	
}
