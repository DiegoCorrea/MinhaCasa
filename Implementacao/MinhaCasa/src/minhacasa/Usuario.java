package minhacasa;

import java.util.Scanner;

public class Usuario {
	
	private static String Username = "user1";
	private static String Password = "user1";
	private static boolean logado_user = false;
	private static boolean logado_adm = false;
	
	public static void logar()
	{
	    System.out.println("Please enter username");
	    Scanner in = new Scanner(System.in);
	    String UN = in.nextLine();
	
	    System.out.println("Please enter password");
	    Scanner inn = new Scanner(System.in);
	    String PW = inn.nextLine();
	
	    if (UN.equals(Username) && PW.equals(Password)) {
	        System.out.println("User has logged in successfully!");
	        logado_user = true;
	    } else {
	        System.out.println("You have entered the wrong credentials, please try again.");
	        logado_user = false;
	    }
	}
	
	public static boolean logado() {
		return logado_user;
	}
}