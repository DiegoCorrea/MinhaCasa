import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Controlador.Casa.Casa;
import Controlador.Comodo.Comodo;

public class MinhaCasa{

	static Casa casa;

	public static void main(String[] args) throws IOException{

		criarCasa();

		criarComodo();
		criarComodo();
		criarComodo();
		criarComodo();

		imprimirCasa();
		imprimirComodos();

	}

	public static void criarCasa() throws IOException
	{
		System.out.println("Digite o nome da Casa:");
		casa = new Casa(lerTeclado());
	}

	public static void criarComodo() throws IOException
	{
		System.out.println("Digite o nome da Comodo:");
		casa.addComodo(new Comodo(lerTeclado()));
	}

	public static String lerTeclado() throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));  
		return buf.readLine();
	}

	public static void imprimirComodos() throws IOException
	{
		ArrayList<Comodo> comodos = (ArrayList<Comodo>) casa.getTodosComodos();
                int i;
                
                if ( comodos.isEmpty() )
			System.out.println("Não existe comodo cadastrado");

                for(i = 0; i < comodos.size(); i++)
                {
                	System.out.println(comodos.get(i).getNome());
                }
	}

        public static void imprimirCasa()
        {
        	System.out.println(casa.getNome());
        }
}