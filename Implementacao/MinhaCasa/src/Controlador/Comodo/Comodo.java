package Controlador.Comodo;

import java.util.List;

public class Comodo
{
	private String nome;
	//private List<AparelhoDomestico> aparelho;


	public Comodo(String aNome){
		this.nome = aNome;
		//aparelhos = new List<AparelhoDomestico> ();

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