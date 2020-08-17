package com.u2d.projeto.controller;

import com.u2d.projeto.dto.EnderecoDTO;
import com.u2d.projeto.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<EnderecoDTO> findByCepApi(WebRequest request){
        Optional<EnderecoDTO> enderecoRetorno = service.findByCepApi(request.getParameterMap());
        return enderecoRetorno.isPresent() ? new ResponseEntity<>(enderecoRetorno.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
