package Controlador.Porta;

import Controlador.ObjetoCasa.ObjetoCasa;

/*
 * A classe Janela e Porta poderiam ser uma classe apenas, chamada por exemplo: Saída
 * com um atributo tipo onde especificaríamos se ela é uma porta ou uma janela. Diminuiria a replicação de código.
 * Porém estou fazendo de acordo com o diagrama de classes, para evitar problemas no trabalho da disciplina
 * de engenharia de software II. //Marcelo
 */

public class Porta extends ObjetoCasa{
	private boolean fechada;
	private boolean travada;
	
	public Porta(String aNome) {
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
		return "Porta: "+getNome();
	}
	
}
