package ua.nure.botsula.st4.date.tag;
import javax.servlet.jsp.tagext.*;

import ua.nure.botsula.st4.util.DateUtils;

import javax.servlet.jsp.*;
import java.io.*;
import java.sql.Date;
import java.util.Calendar;

public class DateTag extends SimpleTagSupport {

   private String message;

   public void setMessage(String msg) {
      this.message = msg;
   }

   StringWriter sw = new StringWriter();

   public void doTag()
      throws JspException, IOException
    {
	   Calendar c=Calendar.getInstance(); 

	   int year=c.get(c.YEAR); 
	   int month=c.get(c.MONTH)+1; 
	   int day=c.get(c.DAY_OF_MONTH);
	   StringBuilder currentDay= new StringBuilder();
	   currentDay.append(String.valueOf(year));
	   currentDay.append("-");
	   currentDay.append(String.valueOf(month));
	   currentDay.append("-");
	   currentDay.append(String.valueOf(day));

       if (message != null) {
          /* Use message from attribute */
    	   Date now = DateUtils.getDateFromString(currentDay.toString(), DateUtils.DATE_FORMAT);
   	    System.out.println(now);
   	    Date finish = DateUtils.getDateFromString(message, DateUtils.DATE_FORMAT);
   	 Long dayToFinish=DateUtils.fullDaysBetweenDates( now,finish);
    	   System.out.println(message);
          JspWriter out = getJspContext().getOut();
          out.println( dayToFinish );
       }
       else {
          /* use message from the body */
          getJspBody().invoke(sw);
          getJspContext().getOut().println(sw.toString());
       }
   }

}
