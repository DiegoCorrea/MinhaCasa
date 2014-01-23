import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Controlador.Casa.Casa;
import Controlador.Comodo.Comodo;

public class MinhaCasa{
	static Casa casa;
	public static void main() throws IOException{
		criarCasa();
		

	}

	public static void criarCasa() throws IOException
	{
		System.out.println("Digite o nome da Casa:");
		casa = new Casa(lerTeclado());
	}

	public static void criarComodo() throws IOException
	{
		String nome;

		System.out.println("Digite o nome da Comodo:");
		Comodo comodo = new Comodo(lerTeclado());

		casa.addComodo(comodo);
	}

	public static String lerTeclado() throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));  
		return buf.readLine();
	}
}