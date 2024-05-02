package questionario.project.controller.login;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
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
import lombok.extern.slf4j.Slf4j;
import questionario.project.service.security.JwtService;
import questionario.project.service.security.JwtValidator;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Filtro implements Filter {
	
	@Autowired
	JwtService js;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        
        Collections.list(request.getHeaderNames())
        .forEach(headerName -> System.out.println("Header ricevuto dal frontend: " + headerName + ": " + request.getHeader(headerName)));

        String requestURI = request.getRequestURI();
        System.out.println("url: " + requestURI);
        String token = request.getHeader("Authorization");
        System.out.println(token);
        
        if (requestURI.contains("api") && js.validateToken(token)) {
            if (token == null || token.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}


/*
@Slf4j
@Component
@Order(1)
public class Filtro implements Filter {

    @Autowired
    private JwtValidator jwtValidator;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        String method = request.getMethod();

        log.info("API chiamata: {}", url);
        log.info("Metodo: {}", method);

        if (url.contains("/api")) {
            boolean publicApiWithoutToken = url.endsWith("loginTesserato");

            if (publicApiWithoutToken) {
                filterChain.doFilter(request, response);
            } else {
                log.info("Sono nel filtro che richiede un token di accesso");

                String token = request.getHeader("Authorization");
                System.out.println(token);
                if (token != null && jwtValidator.validateToken(token)) {
                    log.info("Hai superato il controllo");
                    filterChain.doFilter(request, response);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token non valido o assente");
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
        
        filterChain.doFilter(request, response);
    }

    // Metodi non utilizzati, mantenuti solo per l'implementazione dell'interfaccia Filter
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Metodo di inizializzazione del filtro
    }

    @Override
    public void destroy() {
        // Metodo di distruzione del filtro
    }
}*/
