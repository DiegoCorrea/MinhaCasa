package Controlador.Janela;

import Controlador.ObjetoCasa.ObjetoCasa;

/*
 * A classe Janela e Porta poderiam ser uma classe apenas, chamada por exemplo: Sa�da
 * com um atributo especificar�amos se ela � uma porta ou uma janela. Facilitaria as vis�es. Diminuiria a replica��o de c�digo.
 * Por�m estou fazendo de acordo com o diagrama de classes, para evitar problemas no trabalho da disciplina
 * de engenharia de software II. //Marcelo
 */
public class Janela extends ObjetoCasa{
	private boolean fechada;
	private boolean travada;
	
	public Janela(String aNome) {
		super(aNome);
		fechada = false;
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
	
	public boolean setFechada(boolean value){
		if(travada)
			return false;
		fechada = value;
		return true;
	}
	
	public void destravar(){
		if(travada)
			travada = false;
	}
	
	public void travar(){
		fechar();
		if(!travada)
			travada = true;
	}
	
	public void abrir(){
		if(fechada)
			fechada = false;
	}
	
	public void fechar(){
		if(!fechada)
			fechada = true;
	}
	
	public boolean getTravada(){
		return travada;
	}
	
	public String toString(){
		return "Janela: "+getNome();
	}
}
