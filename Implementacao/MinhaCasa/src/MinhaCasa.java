import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import Controlador.Casa.Casa;
import Controlador.Comodo.Comodo;

public class MinhaCasa {
	
	static Casa casa = null;

	public static void main(String [] args)
	{
		criarCasa();
		criarComodo();
		criarComodo();
		
		imprimirCasa();
		imprimirComodos();

	}

	public static void criarCasa() 
	{
		System.out.println("Digite o nome da Casa:");
		casa = new Casa(lerTeclado());
	}
	
	/* patrick */
	public static void removerCasa() 
	{
		if (casa == null) {
			System.out.println("ERRO: Casa nao cadastrada");
		} else {
			casa = null;
		}
	}

	public static void criarComodo() 
	{
		System.out.println("Digite o nome da Comodo:");
		casa.addComodo(new Comodo(lerTeclado()));
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
	
	public static void imprimirComodos() 
	{
		List<Comodo> comodos = casa.getTodosComodos();
        int i;
                
        if (comodos.isEmpty())
        	System.out.println("Não existe comodo cadastrado");

        for(i = 0; i < comodos.size(); i++)
            System.out.println(comodos.get(i).getNome());

	}
        
    public static void imprimirCasa()
    {
		System.out.println(casa.getNome());
    }
}
