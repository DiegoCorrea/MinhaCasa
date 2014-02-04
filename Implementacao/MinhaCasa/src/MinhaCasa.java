import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import Controlador.Aparelho.Aparelho;
import Controlador.Casa.Casa;
import Controlador.Comodo.Comodo;

public class MinhaCasa {
	
	static Casa casa = null;

	public static void main(String [] args)
	{
		do {
			Usuario.logar();
		} while(!Usuario.logado());	
		
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

	}
	
	/** CRUD CASA **/
	public static void criarCasa() 
	{
		System.out.println("Digite o nome da Casa:");
		casa = new Casa(lerTeclado());
	}
	
	public static void removerCasa() 
	{
		if (casa == null)
			System.out.println("ERRO: Casa nao cadastrada");
		else
			casa = null;
	}

	public static void criarComodo() 
	{
		System.out.println("Digite o nome da Comodo:");

		casa.addComodo(new Comodo(lerTeclado()));
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
		
		if (c == null) {
			System.out.println("Comodo nao existe");
			return;
		}
		
		System.out.println("Digite o nome do aparelho:");
		c.addAparelho(new Aparelho(lerTeclado()));
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
