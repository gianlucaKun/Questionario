package questionario.project.controller.login;
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
import questionario.project.service.security.JwtValidator;

@Slf4j
@Component
@Order(1)
public class Filtro implements Filter {

//    @Autowired
//    private JwtService jwtService;
//
    @Autowired
    private JwtValidator jwtValidator;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        String url = httpReq.getRequestURI();
        String method = httpReq.getMethod();
        
        
        System.out.println("api chiamata: "+ url);
        log.info("Metodo: {}", method);
        if (url.contains("/api")) {
//            boolean GET = "GET".equals(method);
            boolean publicApiWithoutToken = url.endsWith("loginTesserato");

            if (publicApiWithoutToken) {
                chain.doFilter(request, response);
            } else {
                
                String token = httpReq.getHeader("Authorization");
                if (token != null && jwtValidator.validateToken(token)) {
                    chain.doFilter(request, response);
                } else {
                    
                    httpResp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpResp.getWriter().write("Token non valido o assente");
                }
            }
            
        } else if (!url.contains("/api")) {
            chain.doFilter(request, response);
        }
    }
}
