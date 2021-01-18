package com.salus.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.salus.api.model.Usuario;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

	@Value("${endereco.autentica}")
	private String urlAutentica;
	
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @PostMapping("/signup")
    public void signUp(@RequestBody Usuario user) {
    	RestTemplate restTemplate = new RestTemplate();
    	String urlCadastroUsuario= urlAutentica + "/v1/users/signup";
    	
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        HttpEntity<Usuario> request = new HttpEntity<>(user); 
        restTemplate.exchange(urlCadastroUsuario, HttpMethod.POST, request, Usuario.class);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {
    	RestTemplate template = new RestTemplate();
    	String urlLoginUsuario = urlAutentica + "/login";
    	HttpEntity<Usuario> request = new HttpEntity<>(user);

    	HttpEntity<String> response = template.exchange(urlLoginUsuario, HttpMethod.POST, request, String.class);

    	HttpHeaders headers = response.getHeaders();

    	return ResponseEntity.ok().headers(headers).build();
    }

}