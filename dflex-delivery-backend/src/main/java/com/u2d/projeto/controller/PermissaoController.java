package com.u2d.projeto.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.u2d.projeto.dto.PermissaoDTO;
import com.u2d.projeto.event.RecursoCriadoEvent;
import com.u2d.projeto.service.PermissaoService;

@RestController
@RequestMapping("/permissao")
public class PermissaoController {
	
	@Autowired
	private ApplicationEventPublisher publishe;
	
	@Autowired
	private PermissaoService service;
	
	@PostMapping
	public ResponseEntity<PermissaoDTO> salvar(@Valid @RequestBody PermissaoDTO permissaoDTO, HttpServletResponse response){
		PermissaoDTO retorno = service.salvar(permissaoDTO);
		publishe.publishEvent(new RecursoCriadoEvent(this, response, retorno.getId()));
		return new ResponseEntity<>(retorno, HttpStatus.CREATED);
	}

}
