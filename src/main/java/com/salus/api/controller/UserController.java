package com.salus.api.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.salus.api.model.Usuario;
import com.salus.api.repository.UsuarioRepository;

@RestController

@RequestMapping(value = "/v1/users")

public class UserController

{

    private UsuarioRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UsuarioRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder)

    {

        this.userRepository = userRepository;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }
    @PostMapping("/signup")

    public void signUp(@RequestBody Usuario user)

    {
    	RestTemplate restTemplate = new RestTemplate();
    	String urlCadastroUsuario
    	  = "http://localhost:8081/v1/users/signup";
    	
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        HttpEntity<Usuario> request = new HttpEntity<>(user);
        ResponseEntity<?> response = restTemplate
        		  .exchange(urlCadastroUsuario, HttpMethod.POST, request, Usuario.class);
        
    }

}