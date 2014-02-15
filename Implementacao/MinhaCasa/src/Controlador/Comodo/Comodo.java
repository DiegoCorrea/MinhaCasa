package Controlador.Comodo;

import java.util.ArrayList;

import Controlador.Aparelho.Aparelho;
import Controlador.Janela.Janela;
import Controlador.Porta.Porta;
public class Comodo
{
	private String nome;
	private ArrayList<Aparelho> aparelhos;
	private ArrayList<Porta> portas;
	private ArrayList<Janela> janelas;
	
	public Comodo(String aNome) {
		this.nome = aNome;
		this.aparelhos = new ArrayList<Aparelho> ();
		this.setPortas(new ArrayList<Porta>());
		this.setJanelas(new ArrayList<Janela>());

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

	
	public ArrayList<Aparelho> getAparelhos()
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
	
	public void addPorta(Porta p){
		getPortas().add(p);
	}
	
	public void removePorta(Porta p){
		getPortas().remove(p);
	}
	
	public void addJanela(Janela j){
		getJanelas().add(j);
	}
	
	public void removeJanela(Janela j){
		getJanelas().remove(j);
	}

	public ArrayList<Janela> getJanelas() {
		return janelas;
	}
	
	public Janela getJanela(String name){
		for(Janela j : janelas)
			if(j.getNome().equals(name))
				return j;
		return null;
	}
	
	public Porta getPorta(String name){
		for(Porta p : portas)
			if(p.getNome().equals(name))
				return p;
		return null;
	}
	
	public void setJanelas(ArrayList<Janela> janelas) {
		this.janelas = janelas;
	}

	public ArrayList<Porta> getPortas() {
		return portas;
	}

	public void setPortas(ArrayList<Porta> portas) {
		this.portas = portas;
	}
}