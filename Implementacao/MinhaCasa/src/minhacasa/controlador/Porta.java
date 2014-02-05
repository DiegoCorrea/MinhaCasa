package minhacasa.controlador;

public class Porta {
	private String nome;
	private boolean fechada;
	
	public Porta(String aNome) {
		this.nome = aNome;
		fechada = false;
	}
	
	public String getNome()
	{
		return this.nome;
	}

	public boolean getStatus()
	{
		return fechada;
	}
	
	public void abreFecha()
	{
		fechada = !fechada;
	}
	
	public boolean putNome(String aNome)
	{
		this.nome = aNome;
		if (!this.nome.equals(aNome))
			return false;
		return true;
	}
}
