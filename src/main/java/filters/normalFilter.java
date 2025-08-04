package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet Filter implementation class normalFilter
 */
@WebFilter("/*")
public class normalFilter extends HttpFilter implements Filter {
  
	public void destroy() {
		System.out.println("destroy the normalfilter");
	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println(request.getRequestURI());
		chain.doFilter(request, response);
		System.out.println("the post process normal filter");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		System.out.println("Init the normalfilter");
	}

}
