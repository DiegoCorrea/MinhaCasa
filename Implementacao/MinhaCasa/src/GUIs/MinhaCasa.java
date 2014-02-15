package GUIs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controlador.Aparelho.Aparelho;
import Controlador.Casa.Casa;
import Controlador.Comodo.Comodo;
import Controlador.Usuarios.User;
import DbHandler.DbHandler;

public class MinhaCasa {
	static boolean modoec = true;
	static boolean modoSeg = false;
	
	public static User admin = null;
	static MainGUI f;

	static Casa casa = null;
	public static enum Acoes{
		TRAVAR,DESTRAVAR,FECHAR,ABRIR
	};
	
	public static Casa getCasa(){
		return casa;
	}
	
	public static void start() throws IOException{
		f  = new MainGUI(casa);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new JPanel());
//        f.pack();
        f.setTitle("Minha Casa");
        f.setVisible(true);
		DbHandler.start();
	}
	
	public static void main(String [] args) throws IOException	{
		new UsuarioGUI().show();
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
		List<Comodo> comodos = casa.getComodos();
		
        if (comodos.isEmpty())
        	System.out.println("Não existe comodo cadastrado");
        
		for (Comodo c : comodos) {
			System.out.println("Aparelhos no comodo: " + c.getNome());
			c.relatorioAparelhos();
		}
	}
	
	public static void imprimirComodos() 
	{
		List<Comodo> comodos = casa.getComodos();
                
        if (comodos.isEmpty())
        	System.out.println("Não existe comodo cadastrado");

        for(Comodo c : comodos)
            System.out.println(c.getNome());

	}
        
    public static void imprimirComodo()
    {
    	if (casa == null)
    		System.out.println("Casa nao cadastrada");
    	else
    		System.out.println(casa.getNome());
    }
}
