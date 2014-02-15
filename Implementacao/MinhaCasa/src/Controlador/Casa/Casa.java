package Controlador.Casa;

import java.util.ArrayList;

import com.db4o.ObjectSet;

import Controlador.Comodo.Comodo;
import DbHandler.DbHandler;

public class Casa
{
	private String nome;
	private ArrayList<Comodo> comodos = null;

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

	public boolean addComodo(Comodo aComodo)
	{
		
		boolean e = this.comodos.add(aComodo);
		DbHandler.atualizarDb();
		return e;
	}
	
	public boolean delComodo(Comodo aComodo)
	{
		boolean e = this.comodos.remove(aComodo);
		DbHandler.atualizarDb();
		return e;
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
	
	public ArrayList<Comodo> getComodos(){
		return comodos;
	}
}