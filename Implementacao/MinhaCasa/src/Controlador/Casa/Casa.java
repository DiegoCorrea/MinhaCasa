package Controlador.Casa;

import java.util.ArrayList;
import java.util.List;

import Controlador.Comodo.Comodo;


public class Casa
{
	private String nome;
	private List<Comodo> comodos;

	public Casa(String aNome){
		this.nome = aNome;
		this.comodos = new ArrayList<>();
	}

	public String getNome()
	{
		return this.nome;
	}

	public boolean putNome(String aNome)
	{
		this.nome = aNome;
		if (!this.nome.equals(aNome))
			return false;
		return true;
	}

	public List<Comodo> getTodosComodos()
	{
		return this.comodos;
	}

	public boolean addComodo(Comodo aComodo)
	{
		if (this.comodos.add(aComodo) == false)
			return false;
		return true;
	}

	public Comodo getComodo(String aNome)
	{
		int i;
		if ( this.comodos.isEmpty() )
			return null;

		for(i = 0; i < this.comodos.size(); i++)
		{
			if( this.comodos.get(i).getNome().equals(aNome) )
			{
				return this.comodos.get(i);
			}
		}
		return null;
	}
}