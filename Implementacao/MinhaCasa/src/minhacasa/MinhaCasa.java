package minhacasa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.*;

import minhacasa.controlador.Agendador;
import minhacasa.controlador.Aparelho;
import minhacasa.controlador.Casa;
import minhacasa.controlador.Comodo;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.*;

public class MinhaCasa
{
	static Casa casa = null; 		// a casa a ser controlada
	static ObjectContainer db;		// o banco de dados em arquivo
	static final int profundidade_db = 5; 	// a cada save de objeto o banco salva objetos 
						// referenciados ate esta profundidade
	static FileHandler fh; 			// filehandler para logging
	static final Logger logger = Logger.getLogger("minhacasa");

	@SuppressWarnings("deprecation")	// supress deprecation por causa de Db4o.openFile 
	public static void main(String [] args) 
	{
		// seta arquivo de texto para logging
		try {
			fh = new FileHandler("log.txt", true); // true para append no arquivo.
			fh.setFormatter(new SimpleFormatter());
			logger.addHandler(fh);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		// login, db e operacoes
		try {
			// login
			do {
				Usuario.logar();
			} while(!Usuario.logado());
			logger.info(">>> log pra usuario: user1");
		    
			// db
			db = Db4o.openFile("db.dbo");
			
			// operacoes
			realizarOperacoesDeTeste();
			
			Comodo c = null;
			Aparelho a = null;
			
			// query de comodo direto no banco
			/*
			ObjectSet<Comodo> comodos = db.queryByExample(new Comodo("quarto"));
			a = comodos.get(0).getAparelho("pc");
			*/
			
			// query usando a casa para obter comodo
			// teste de liga e desliga e persistencia OK
			c = casa.getComodo("quarto");
			if (c != null)
				a = c.getAparelho("pc");
			
			if (a != null) {
				a.ligaDesliga();
				System.out.println("Ligando PC no Quarto...");
				logger.info("Ligando/Desligando aparelho " + a);
				db.ext().store(casa, profundidade_db); // salva objeto
			}


			// teste de remocao e persistencia ok
			//casa.delComodo(casa.getComodo("sala"));
			//db.ext().store(casa, profundidade_db);
		} finally { 
			System.out.println("Fechando banco de dados...");
			db.close();
			fh.close();
		}
	}
	
	public static void realizarOperacoesDeTeste()
	{
		criarCasa();
		//criarComodo();
		//criarComodo();
		imprimirCasa();
		imprimirComodos();
		//criarAparelho();
		//criarAparelho();
		//criarAparelho();
		
		relatorioAparelhos();
		//removerComodo();
		//imprimirComodos();
		//removerCasa();
		//imprimirCasa();
		criarAparelho();
		//criarAparelho();
		//criarAparelho();
		relatorioAparelhos();
		//removerComodo();
		//imprimirComodos();
		//removerCasa();
		//imprimirCasa();
	}
	
	/** CRUD CASA **/
	public static void criarCasa() 
	{
		String nome;
		
		System.out.println("Digite o nome da Casa:");
		nome = lerTeclado();
		
		ObjectSet<Casa> casas = db.queryByExample(new Casa(nome));
		if (casas.isEmpty()) {
			logger.info("Casa " + nome + " nao existe. Criando ...");
			casa = new Casa(nome);
			db.store(casa);
			logger.info("Casa " + casa + " criada");
		} else {
			logger.info("Casa " + nome + " já existe. Obtendo do banco...");
			casa = casas.get(0);
			relatorioAparelhos();
		}
	}
	
	public static void removerCasa() 
	{
		if (casa == null) {
			logger.warning("ERRO: Casa nao cadastrada");
		} else {
			// db.delete(casa);
			casa = null;
		}
	}

	public static void criarComodo() 
	{
		System.out.println("Digite o nome da Comodo:");
		Comodo comodo = new Comodo(lerTeclado());
		casa.addComodo(comodo);
		logger.info("Comodo " + comodo + " adicionado na casa " + casa);
		db.ext().store(casa, profundidade_db);
		
	}

	public static void removerComodo() 
	{
		System.out.println("Digite o nome da Comodo:");
		String nomeComodo = lerTeclado();
		Comodo c = casa.getComodo(nomeComodo);
		if (casa.delComodo(c)) {
			logger.info("Comodo " + nomeComodo + " removido da casa " + casa);
			db.ext().store(casa, profundidade_db);
		} else {
			System.out.println("Comodo nao existe");
			logger.info("Comodo " + nomeComodo + " nao existe na casa " + casa);
		}
	}
	
	public static void criarAparelho() 
	{
		System.out.println("Digite o nome do comodo em que esta o aparelho:");
		String nomeComodo = lerTeclado();
		Comodo c = casa.getComodo(nomeComodo);
		Aparelho a;
		
		if (c == null) {
			logger.info("Comodo " + nomeComodo + " nao existe na casa " + casa);
			System.out.println("Comodo nao existe");
			return;
		}
		
		System.out.println("Digite o nome do aparelho:");
		a = new Aparelho(lerTeclado());
		
		c.addAparelho(a);
		db.ext().store(casa, profundidade_db);
		logger.info("Aparelho adicionado: " + a + " no " + c + " da casa " + casa);
	}
	
	public static String lerTeclado() 
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));  
		try {
			return buf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public static void relatorioAparelhos()
	{
		List<Comodo> comodos = casa.getTodosComodos();
		
		logger.info("Solicitado relatorio de aparelhos na casa " + casa);
		
        	if (comodos.isEmpty())
        		System.out.println("Nao existe comodo cadastrado");
        
		for (Comodo c : comodos) {
			System.out.println("Aparelhos no comodo: " + c.getNome());
			c.relatorioAparelhos();
		}
	}
	
	public static void imprimirComodos() 
	{
		List<Comodo> comodos = casa.getTodosComodos();
                
		if (comodos.isEmpty())
			System.out.println("Nao existe comodo cadastrado");

		for(Comodo c : comodos)
			System.out.println(c.getNome());

	}
        
	public static void imprimirCasa()
	{
    		if (casa == null)
			System.out.println("Casa nao cadastrada");
		else
			System.out.println(casa.getNome());
	}
}
