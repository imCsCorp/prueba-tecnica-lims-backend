package com.camilosoto.pruebatecnica.web.controller;

import com.camilosoto.pruebatecnica.domain.dto.UserDTO;
import com.camilosoto.pruebatecnica.domain.service.UserService;
import com.camilosoto.pruebatecnica.web.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> findByEmail(@RequestHeader("Authorization") String token){
        String jwtToken = token.substring(7); // Eliminar el prefijo "Bearer " del encabezado Authorization
        String subject = jwtTokenUtil.extractSubject(jwtToken);
        return  userService.findByUsername(subject)
                .map(u -> new ResponseEntity<> (u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user){
        return new ResponseEntity<>(userService.update(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int userId){
        if(userService.remove(userId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
