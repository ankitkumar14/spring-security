package com.loop.springBootSecurity.service;

import com.loop.springBootSecurity.model.Users;
import com.loop.springBootSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo repo;
    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    public Users register(Users user){
        try{
            user.setPassword(encoder.encode(user.getPassword()));
            return repo.save(user);
        }catch (Exception e){
            return null;
        }
    }

    public List<Users> getUsers() throws Exception {
        try{
            return repo.findAll();
        }catch(Exception e){
            throw new Exception("Database Error");
        }
    }

    public String verify(Users user) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(auth.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "fail";
    }
}
