package Controlador.Aparelho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Controlador.Agendador.Agendador;

public class Aparelho {
	private String nome;
	private boolean on;
	
	public Aparelho(String aNome) {
		this.nome = aNome;
		this.on = false;
	}
	
	/** 
	 * time: uma string do tipo 'dd/MM/yyyy HH:mm'
	 * */
	
	public void agendar(String time){
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		agendar(date);
	}
	
	public void agendar(Date date){
		Agendador.agendar(this, date, (on) ? "ligar" : "desligar");
	}
	
	public String getNome(){
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
	
	public void liga(){on = true;}
	public void desliga(){on = false;}
	
	public boolean putNome(String aNome)
	{
		this.nome = aNome;
		if (!this.nome.equals(aNome))
			return false;
		return true;
	}
	
	@Override
	public String toString() 
	{
		return this.getNome();
	}
}
