package questionario.project.service.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import questionario.project.service.security.JwtService;
import questionario.project.service.security.JwtValidator;

@Slf4j
@Component
@Order(1)
public class AuthenticationFilter implements Filter {

	@Autowired
	private JwtService jwtS;

	@Autowired
	private JwtValidator jwtV;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {

	    HttpServletRequest httpReq = (HttpServletRequest) request;
	    HttpServletResponse httpResp = (HttpServletResponse) response;
	    String url = httpReq.getRequestURI();
	    String method = httpReq.getMethod();

	    log.info("Api chiamata: {}", url);
	    log.info("stampo metodo : {}", method);

	    if (url.contains("/api")) {
	        boolean GET = "GET".equals(method);

	        boolean publicApiWithoutToken = url.endsWith("loginUtente");
	        boolean publicApiWithoutTokenCreateUser = url.endsWith("add");
	        boolean signIn = url.endsWith("signin");
	        boolean signUp = url.endsWith("signup");
	        if (signUp || signIn || publicApiWithoutToken || publicApiWithoutTokenCreateUser) {
	            chain.doFilter(request, response);
	        } else {
	            String token = httpReq.getHeader("Authorization");

	            if (token == null || token.isEmpty()) {
	                httpResp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                httpResp.getWriter().write("Token assente nell'intestazione della richiesta");
	            } else if (jwtV.validateToken(token)) {
	                chain.doFilter(request, response);
	            } else {
	            	System.out.println("TOKEN PRESENTE MA SCADUTO O NON VALIDO");
	                httpResp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                httpResp.getWriter().write("Token non valido");
	            }
	        }
	    } else {
	        // Qui gestisci il filtro per le richieste non relative alle API
	        String token = httpReq.getHeader("Authorization");

	        if (token == null || token.isEmpty()) {
	            httpResp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            httpResp.getWriter().write("Token assente nell'intestazione della richiesta");
	        } else if (jwtV.validateToken(token)) {
	            chain.doFilter(request, response);
	        } else {
	            httpResp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            httpResp.getWriter().write("Token non valido");
	        }
	    }
	}
}
