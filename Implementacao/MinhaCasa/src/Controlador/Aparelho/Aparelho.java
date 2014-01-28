package Controlador.Aparelho;

import java.util.List;

import Controlador.Aparelho.Aparelho;

public class Aparelho {
	private String nome;
	private boolean on;
	
	public Aparelho(String aNome) {
		this.nome = aNome;
		on = false;
	}
	
	public String getNome()
	{
		return this.nome;
	}

	public boolean getStatus()
	{
		return on;
	}
	
	public void ligaDesliga()
	{
		on = !on;
	}
	
	public boolean putNome(String aNome)
	{
		this.nome = aNome;
		if (!this.nome.equals(aNome))
			return false;
		return true;
	}
}
