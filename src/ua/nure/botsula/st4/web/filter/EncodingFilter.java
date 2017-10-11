package ua.nure.botsula.st4.web.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
	// encoding
	private String encoding;

	public void init(FilterConfig config) throws ServletException {

		encoding = config.getInitParameter("requestEncoding");
		if (encoding == null) {
			encoding = "UTF-8";
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
