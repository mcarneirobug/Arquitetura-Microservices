package com.matheus.auth.modules.auth.controller;

import com.matheus.auth.jwt.JwtTokenProvider;
import com.matheus.auth.modules.user.service.impl.UserRepository;
import com.matheus.auth.modules.user.vo.UserVO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @RequestMapping("/testeSecurity")
    public String teste() {
        return "testado";
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/y-yaml"},
            consumes = {"application/json", "application/xml", "application/y-yaml"})
    public ResponseEntity<?> login(@RequestBody UserVO userVO) {

        var username = userVO.getUserName();
        var password = userVO.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        var user = this.userRepository.findByUserName(username);
        var token = "";

        if(user != null) {
            token = this.jwtTokenProvider.createToken(username, user.getRoles());
        } else {
            throw new UsernameNotFoundException("Username not found");
        }

        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);

        return ResponseEntity.ok(model);
    }



}
