package questionario.project.controller.login;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
//import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import questionario.project.service.security.JwtService;

@Component
@Order(1)
public class Filtro implements Filter {
	
	@Autowired
	JwtService js;
	// filtro basic per cercare se è presente il token o no
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        
//        Collections.list(request.getHeaderNames())
//        .forEach(headerName -> System.out.println("Header ricevuto dal frontend: " + headerName + ": " + request.getHeader(headerName)));
        
        System.out.println("sei nel filtro dell'utente");

        String requestURI = request.getRequestURI();
        System.out.println("url: " + requestURI);
        
        String requestMethod = request.getMethod();
        System.out.println("method: " + requestMethod);
        
      String token = request.getHeader("Authorization");
      System.out.println(token);
        
//        if (!requestURI.contains("loginUtente") && !requestURI.contains("admin")) {
//            String token = request.getHeader("Authorization");
//            System.out.println(token);
//            System.out.println("token filtro 1 :" + token);
//            
//            if (!js.validateToken(token)) {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                return;
//            }
//            request.setAttribute("token", token);
//        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
