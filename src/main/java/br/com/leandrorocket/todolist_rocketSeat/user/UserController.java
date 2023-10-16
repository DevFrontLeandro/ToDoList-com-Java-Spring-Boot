package br.com.leandrorocket.todolist_rocketSeat.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/*
 * Modificadores:
 * public
 * private
 * protected 
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
     private IUserRepository userRepository;

    @PostMapping("/")
     // @RequestBody significa que estou chamando o que está dentro do UserModel
    public ResponseEntity create(@RequestBody UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null){
            //Mensagem de erro
            //Status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }

       var passwordHashred =  BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

       userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
