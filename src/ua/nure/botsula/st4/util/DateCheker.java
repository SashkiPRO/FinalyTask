package ua.nure.botsula.st4.util;

import java.sql.Date;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import ua.nure.botsula.st4.db.DBManager;
import ua.nure.botsula.st4.entity.Course;
import ua.nure.botsula.st4.entity.CourseState;
import ua.nure.botsula.st4.exception.DBException;


public class DateCheker implements Runnable{
	@Override
	public void run() {
		DBManager manager = null;
		try {
			manager = DBManager.getInstance();
		} catch (DBException e1) {
			//LOG.error("BookingDateChecker error, DBManager#getInstance", e1);
		}
		while (true) {
			List<Course> courses = null;
			try {
				courses = manager.findAllCourses();
			} catch (DBException e1) {
			//	LOG.error(
				//		"BookingDateChecker error, manager#findAllBookedBookingRequest",
					//	e1);
			}
			for (Course courseUnit : courses) {
				if (DateUtils.fullDaysBetweenDates(DateUtils.getCurrentDate(), courseUnit.getStartDate()) > 0) {
					System.out.println(DateUtils.fullDaysBetweenDates(DateUtils.getCurrentDate(), courseUnit.getStartDate()) > 0);
					System.out.println(DateUtils.getCurrentDate());
					System.out.println((courseUnit.getFinishDate()));
					System.out.println(courseUnit.getName());
					try {
						manager.updateCourseState(CourseState.RECRUITED.ordinal(), courseUnit.getId());
						System.out.println("recrited");
					} catch (DBException e) {
				/*		LOG.error(
								"BookingDateChecker error, manager#findAllBookedBookingRequest",
								e);
				*/	}
				/*	LOG.trace("Booking request with id= "
							+ bookingRequest.getId()
							+ " - deleted (expired of date)");
				*/}else if(DateUtils.fullDaysBetweenDates(DateUtils.getCurrentDate(), courseUnit.getFinishDate())>0&&(DateUtils.fullDaysBetweenDates(courseUnit.getStartDate(),DateUtils.getCurrentDate())>0)){
					try {
						manager.updateCourseState(CourseState.DURING.ordinal(), courseUnit.getId());
						System.out.println("during");
		
						System.out.println(courseUnit.getName());
					} catch (DBException e) {
				/*		LOG.error(
								"BookingDateChecker error, manager#findAllBookedBookingRequest",
								e);
				*/	}
				/*	LOG.trace("Booking request with id= "
							+ bookingRequest.getId()
							+ " - deleted (expired of date)");
				*/
					
				}else if(DateUtils.fullDaysBetweenDates(DateUtils.getCurrentDate(), courseUnit.getFinishDate())<0){
					try {
						manager.updateCourseState(CourseState.FINISHED.ordinal(), courseUnit.getId());
						System.out.println("finished");
						System.out.println("during");
						
						System.out.println(courseUnit.getName());
					} catch (DBException e) {
				/*		LOG.error(
								"BookingDateChecker error, manager#findAllBookedBookingRequest",
								e);
				*/	}
				/*	LOG.trace("Booking request with id= "
							+ bookingRequest.getId()
							+ " - deleted (expired of date)");
				*/
				}
			}
			try {
				Thread.sleep(60*60*1000);
			} catch (InterruptedException e) {
				//LOG.error("BookingDateChecker error, InterruptedException", e);
			}
		}
	}
}
