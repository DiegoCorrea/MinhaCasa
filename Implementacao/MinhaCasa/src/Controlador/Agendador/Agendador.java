package Controlador.Agendador;

import java.util.Date;
import java.util.List;

import Controlador.Aparelho.Aparelho;
import Controlador.Janela.Janela;
import Controlador.Porta.Porta;

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
			time = date.getTime() - mim;//novo
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
        
        
        //SAIDAS (PORTAS E JANELAS)
        switch(acao){
    	case "Travar":
			if(elemento instanceof Porta)	
				((Porta)elemento).travar();	
			else ((Janela)elemento).travar();
			break;
    	case "Destravar":
			if(elemento instanceof Porta)	
				((Porta)elemento).destravar();	
			else ((Janela)elemento).destravar();
    		break;
    	case "Abrir":
			if(elemento instanceof Porta)	
			((Porta)elemento).fechar();	
		else ((Janela)elemento).fechar();
    		break;
    	case "Fechar":
    		if(elemento instanceof Porta)	
    			((Porta)elemento).abrir();	
    		else ((Janela)elemento).abrir();
    		break;
    	case "ligar":
    		((Aparelho) elemento).ligar();
    		break;
    	
    	case "desligar":
    		((Aparelho) elemento).desligar();
    		break;
        }
    }
	
	public static void agendar(Object elem, Date date, String acao) {
		Agendador agen = new Agendador(elem, date, acao);
		agendadores.add(agen);
		
		new Thread(agen).start();
    }
}
