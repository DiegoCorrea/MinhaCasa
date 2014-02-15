package Controlador.Usuarios;

import java.util.ArrayList;

public class User {
	private ArrayList<String> msgs;
	boolean admin = false;
	
	public User(){
		msgs = new ArrayList<String>();
	}
	
	public void receberMsg(String msg){
		msgs.add(msg);
	}
	
	public ArrayList<String> getMsgs(){
		return msgs;
	}
	
	public void setAdmin(boolean a){
		this.admin = a;
	}
	
	public boolean isAdmin(){
		return this.admin;
	}
}
