import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import Controlador.Aparelho.Aparelho;
import Controlador.Casa.Casa;
import Controlador.Comodo.Comodo;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.*;

public class MinhaCasa {
	
	static Casa casa = null;
	@SuppressWarnings("deprecation")
	static ObjectContainer db = Db4o.openFile("db.dbo");
	
	public static void main(String [] args)
	{
		do {
			Usuario.logar();
		} while(!Usuario.logado());	
		
		try {
			criarCasa();
			criarComodo();
			criarComodo();
			
			imprimirCasa();
			imprimirComodos();

			criarAparelho();
			criarAparelho();
			criarAparelho();
			
			relatorioAparelhos();
			//removerComodo();
			imprimirComodos();
			//removerCasa();
			//imprimirCasa();
			
		} finally {
			db.close();
		}
		


	}
	
	/** CRUD CASA **/
	public static void criarCasa() 
	{
		String nome;
		
		System.out.println("Digite o nome da Casa:");
		nome = lerTeclado();
		
		ObjectSet<Casa> casas = db.queryByExample(new Casa(nome));
		if (casas.isEmpty()) {
			System.out.println("Casa não existe. Criando ...");
			casa = new Casa(nome);
			db.store(casa);
		} else {
			System.out.println("Casa já existe. Obtendo do banco...");
			casa = casas.get(0);
			relatorioAparelhos();
		}
	}
	
	public static void removerCasa() 
	{
		if (casa == null) {
			System.out.println("ERRO: Casa nao cadastrada");
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
		db.ext().store(casa,5);
		
	}

	public static void removerComodo() 
	{
		System.out.println("Digite o nome da Comodo:");
		
		if (casa.delComodo(casa.getComodo(lerTeclado())))
			System.out.println("Ok!");
		else
			System.out.println("Comodo nao existe");
	}
	
	public static void criarAparelho() 
	{
		System.out.println("Digite o nome do comodo em que esta o aparelho:");
		Comodo c = casa.getComodo(lerTeclado());
		Aparelho a;
		
		if (c == null) {
			System.out.println("Comodo nao existe");
			return;
		}
		
		System.out.println("Digite o nome do aparelho:");
		a = new Aparelho(lerTeclado());
		
		c.addAparelho(a);
		db.ext().store(casa,5);
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
	
	public static void relatorioAparelhos() {
		List<Comodo> comodos = casa.getTodosComodos();
		
        if (comodos.isEmpty())
        	System.out.println("Não existe comodo cadastrado");
        
		for (Comodo c : comodos) {
			System.out.println("Aparelhos no comodo: " + c.getNome());
			c.relatorioAparelhos();
		}
	}
	
	public static void imprimirComodos() 
	{
		List<Comodo> comodos = casa.getTodosComodos();
                
        if (comodos.isEmpty())
        	System.out.println("Não existe comodo cadastrado");

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
