import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Controlador.Casa.Casa;
import Controlador.Comodo.Comodo;

public class MinhaCasa{
	
	static Casa casa;

	public static void main() throws IOException{
		criarCasa();
		criarComodo();
		
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
		List<Comodo> comodos = casa.getTodosComodos();
                int i;
                
                if ( comodos.isEmpty() )
			System.out.println("Não existe comodo cadastrado");

                for(i = 0; i < this.comodos.size(); i++)
                {
                        if( this.comodos.get(i).getNome().equals(aNome) )
                        {
                                return this.comodos.get(i);
                        }
                }
        }
}
