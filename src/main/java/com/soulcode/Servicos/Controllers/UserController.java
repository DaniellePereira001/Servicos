package com.soulcode.Servicos.Controllers;


import com.soulcode.Servicos.Models.User;
import com.soulcode.Servicos.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passawordEncoder;

    @GetMapping("/usuarios")
    public List<User> usuarios(){
        return userService.listar();
    }

    @PostMapping("/usuarios")
    public ResponseEntity<User> inserirUsuario(@RequestBody User user){
        String senhaCodificada = passawordEncoder.encode(user.getPassword());
        user.setPassword(senhaCodificada);
        user = userService.cadastrar(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
