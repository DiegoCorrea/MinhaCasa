package Controlador.Comodo;

import java.util.ArrayList;
import java.util.List;
import Controlador.Aparelho.Aparelho;
public class Comodo
{
	private String nome;
	private List<Aparelho> aparelhos;

	public Comodo(String aNome) {
		this.nome = aNome;
		aparelhos = new ArrayList<Aparelho> ();

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

	
	public List<Aparelho> getTodosAparelhos()
	{
		return this.aparelhos;
	}

	public boolean addAparelho(Aparelho aAparelho)
	{
		return this.aparelhos.add(aAparelho);
	}
	
	public boolean delAparelho(Aparelho aAparelho)
	{
		return this.aparelhos.remove(aAparelho);
	}
	
	public Aparelho getAparelho(String aNome)
	{
		int i;
		if ( this.aparelhos.isEmpty() )
			return null;

		for(i = 0; i < this.aparelhos.size(); i++)
		{
			if( this.aparelhos.get(i).getNome().equals(aNome) )
			{
				return this.aparelhos.get(i);
			}
		}
		return null;
	}
	
	public void relatorioAparelhos()
	{
		for (Aparelho a : aparelhos)
			System.out.println("Aparelho: " + a.getNome() + " ; Ligado? " + a.getStatus());
		
		return;
	}
	//public List<AparelhoDomestico> getTodosAparelhos()
	//{
	//	return this.aparelhos;
	//}

	//public boolean addNovoAparelho(AparelhoDomestico aAparelhoDomestico)
	//{
	//	if (this.aparelhos.add(aAparelhoDomestico) == false)
	//		return false;
	//	return true;
	//}

	//public AparelhoDomestico getAparelho(String aNome)
	//{
	//	int i;
	//	if (this.aparelhos.isEmpty())
	//		return null;

	//	for(i = 0; i < this.aparelhos.size(); i++)
	//	{
	//		if( this.aparelhos.get(i).getNome().equals(aNome) )
	//		{
	//			return this.aparelhos.get(i);
	//		}
	//	}
	//}
}