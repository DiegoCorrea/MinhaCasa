package Controlador.ObjetoCasa;

import GUIs.MinhaCasa;

public class ObjetoCasa {
	private boolean defeituoso;
	protected String nome;
	
	public ObjetoCasa(String nome){
		this.nome = nome;
		defeituoso = false;
	}
	
	public void alertarDefeito(){
		defeituoso = true;
	}
	
	public boolean estaComDefeito(){
		return defeituoso;
	}
	
	public void consertado(){
		defeituoso = false;
	}

	public static void alertarDefeito(ObjetoCasa c){
			MinhaCasa.admin.receberMsg("Mal funcionamento encontrado no aparelho: "+c.getNome());
	}
	
	
	public String getNome()
	{
		return this.nome;
	}

}