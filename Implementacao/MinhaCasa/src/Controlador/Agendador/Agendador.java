package Controlador.Agendador;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Controlador.Aparelho.Aparelho;

public class Agendador implements Runnable{
	
	private static List<Agendador> agendadores;
	
	private Date date;
	private long time;
	private Object elemento;
	private String acao;
	private String estado = "RUNNING";

	public Agendador(Object element, Date date, String action){
		long time = 0;
		long mim = System.currentTimeMillis();
		
		try{
			time = date.getTime() - mim;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		this.time = time;
		this.date = date;
		this.elemento = element;
		this.acao = action;
	}
	
	@Override
    public void run() {

        try {
            Thread.sleep(time);
            
        } catch ( InterruptedException exc ) {
            exc.printStackTrace();
        }
        
        estado = "FINISHED";
        
        switch(acao){
        	case "ligar":
        		((Aparelho) elemento).liga();
        		break;
        	
        	case "desligar":
        		((Aparelho) elemento).desliga();
        		break;
        }
    }
	
	public static void agendar(Object elem, Date date, String acao) {
		Agendador agen = new Agendador(elem, date, acao);
		agendadores.add(agen);
		
		new Thread(agen).start();
    }
}
