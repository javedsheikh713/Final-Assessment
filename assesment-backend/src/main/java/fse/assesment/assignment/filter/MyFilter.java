package fse.assesment.assignment.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class MyFilter implements Filter {

	Logger log = LoggerFactory.getLogger(MyFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response; 
		log.info("Inside filter");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Headers",	"Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With");
		resp.addHeader("Access-Control-Allow-Methods", "POST,GET, PUT, OPTIONS, X-XSRF-TOKEN");
		chain.doFilter(request, response);

		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
