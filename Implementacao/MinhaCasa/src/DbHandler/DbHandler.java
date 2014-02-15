package DbHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Controlador.Casa.Casa;
import GUIs.MainGUI;
import GUIs.MinhaCasa;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class DbHandler {
	public static ObjectContainer db;
	public static final int profundidade_db = 5;
	public static FileHandler fh;
    public static final Logger logger = Logger.getLogger("minhacasa");
    
    
    @SuppressWarnings("deprecation")
	public static void start(){
    	// logging
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
		
		    logger.info(">>> log pra usuario: admin");
		    // db
			db = Db4o.openFile("db.dbo");
		} finally { 
//			System.out.println("Fechando banco de dados...");
//			db.close();
//			fh.close();
		}
    }
    public void dbClose(){
    	System.out.println("Fechando banco de dados...");
		db.close();
		fh.close();
    }
    public static void listResult(ObjectSet result){
//    	System.out.println(result.size());
    	while(result.hasNext()){
//    		System.out.println(result.next());
    	}
    }
    
	public static void atualizarDb() {
		DbHandler.db.ext().store(MinhaCasa.getCasa(), DbHandler.profundidade_db);
	}
	
	public static void restoreDb(){
//		Casa casa = db.queryByExample(new Casa("casa"));
	}
}
