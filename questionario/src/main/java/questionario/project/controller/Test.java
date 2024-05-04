package questionario.project.controller;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class Test {

    @GetMapping("/test")
    public Map<String, String> test(@RequestHeader(value="Authorization") String token, HttpServletRequest request) {
    	System.out.println("sei in test");
    	System.out.println(request.getHeader("Authorization"));
    	System.out.println(token);
        Collections.list(request.getHeaderNames())
                .forEach(headerName -> System.out.println("Header ricevuto dal frontend: " + headerName + ": " + request.getHeader(headerName)));

        return Collections.singletonMap("message", "Risposta dal backend");
    }
}
