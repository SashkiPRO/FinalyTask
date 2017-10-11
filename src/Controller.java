

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
import ua.nure.botsula.st4.command.*;
import ua.nure.botsula.st4.Path;
import ua.nure.botsula.st4.exception.*;



/**
 * Main servlet controller.
 * 
 * @author A.Botsula
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = -1912173867027220698L;

	//private Logger LOG = Logger.getLogger(this.getClass());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processDo(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
processPost(request, response);


	}

	/**
	 * Main method of this controller.
	 */
	private void processPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		//LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		//LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		//LOG.trace("Obtained command --> " + command);
		request.getContextPath();
		// execute command and get forward address
		String forward = Path.PAGE_INDEX;
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
		}
		
		//LOG.trace("Forward address --> " + forward);

		//LOG.debug("Controller finished, now go to forward address --> "
	//			+ forward);
		
	response.sendRedirect(request.getContextPath()+forward);
	
	}
	private void processDo(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		//LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		//LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		//LOG.trace("Obtained command --> " + command);
		request.getContextPath();
		// execute command and get forward address
		String forward = Path.PAGE_INDEX;
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
		}
		
		//LOG.trace("Forward address --> " + forward);

		//LOG.debug("Controller finished, now go to forward address --> "
	//			+ forward);
		
	
		request.getRequestDispatcher(forward).forward(request, response);
	}
	
}
