package GenericUtilities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Java_Utility {
	public int getRandomNumber() {
		Random r=new Random();
		int Rno=r.nextInt(5000);
		return Rno;
	}
	public String getStartdate() {
		
		Date dobj = new Date(0);
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd"); 
		String currentdate=sim.format(dobj);
		return currentdate;
		
	}

	public String getDateAftergivendays(int days) {
		
		Date dobj = new Date(0);
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd"); 
		sim.format(dobj);
		Calendar cal= sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String dateaftergivendays=sim.format(cal.getTime());
		return dateaftergivendays;
		
		
	}
	
}
