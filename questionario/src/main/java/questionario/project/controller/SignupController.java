package questionario.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import questionario.project.dto.UtenteDTO;
import questionario.project.service.UtenteService;

@RestController
public class SignupController {

    @Autowired
    private UtenteService us;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody UtenteDTO utenteDTO) {
       UtenteDTO createdUser = us.add(utenteDTO);
       if (createdUser == null){
           return new ResponseEntity<>("User not created, come again later!", HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
