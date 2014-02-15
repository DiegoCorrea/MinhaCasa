package Controlador.Aparelho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Controlador.Agendador.Agendador;
import Controlador.ObjetoCasa.ObjetoCasa;
import DbHandler.DbHandler;

public class Aparelho extends ObjetoCasa{
	private String nome;
	private boolean on = false;
	private int consumo = 0;
	private int durabilidade =0;
	
	public Aparelho(String aNome) {
		super(aNome);
		on = false;
	}
	
	/** 
	 * time: uma string do tipo 'dd/MM/yyyy HH:mm'
	 * @throws ParseException 
	 * */
	
	public void agendar(String time) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = format.parse(time);
		
		agendar(date);
	}
	
	public void agendar(Date date){
		Agendador.agendar(this, date, (on) ? "ligar" : "desligar");
	}
	
	public boolean getStatus()
	{
		return on;
	}
	
	public void ligaDesliga()
	{
		on = !on;
	}
	
	public void ligar(){on = true;}
	public void desligar(){on = false;}
	
	public boolean putNome(String aNome)
	{
		this.nome = aNome;
		if (!this.nome.equals(aNome))
			return false;
		return true;
	}

	public int getConsumo() {
		return consumo;
	}

	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}

	public int getDurabilidade() {
		return durabilidade;
	}

	public void setDurabilidade(int durabilidade) {
		this.durabilidade = durabilidade;
		DbHandler.atualizarDb();
	}
	
	public String toString(){
		return "Aparelho: "+getNome();
	}
}
