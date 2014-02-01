import java.util.Date;
import java.text.SimpleDateFormat;


public class Main {
	
	static void main(String[] args){
		String time = "01/30/2014 12:00:00";
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
		long mim = System.currentTimeMillis();
		Date date = null;
		
		try {
			date = format.parse(time);
			long diff = mim - date.getTime();
			
			
			System.out.println("diff " + diff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
