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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/dashboard" , "/addpost" , "/updatepost" , "/deletepost" , "/publicpost"})
public class authFilter extends HttpFilter implements Filter {
 
	public void destroy() {
		System.out.println("destroy method called");
	}

	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Do preprocess of authfilter");
		HttpSession s = request.getSession(false);
       if(s != null && s.getAttribute("username") != null)
       {
    	   chain.doFilter(request, response);
       }
       else
       {
    	   response.sendRedirect(request.getContextPath() + "/login");
       }
       System.out.println("Do postprocess of authfilter");
    }

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init method called");
	}

}
