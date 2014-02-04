package Controlador.Casa;


import java.util.ArrayList;
import java.util.List;

import Controlador.Comodo.Comodo;


public class Casa
{
	private String nome;
	private List<Comodo> comodos = null;

	public Casa(String aNome){
		this.nome = aNome;
		comodos = new ArrayList<Comodo>();
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
		return this.comodos.add(aComodo);
	}
	
	public boolean delComodo(Comodo aComodo)
	{
		return this.comodos.remove(aComodo);
	}
	
	public Comodo getComodo(String aNome)
	{
		for (Comodo c : comodos)
			if (c.getNome().equals(aNome))
				return c;
	
		return null;
		/*
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
		*/
	}
}