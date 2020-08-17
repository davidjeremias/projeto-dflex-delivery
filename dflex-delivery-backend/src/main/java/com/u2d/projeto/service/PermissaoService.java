package com.u2d.projeto.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2d.projeto.dto.PermissaoDTO;
import com.u2d.projeto.model.Permissao;
import com.u2d.projeto.repository.PermissaoRepository;

@Service
public class PermissaoService {
	
	@Autowired
	private PermissaoRepository repository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public PermissaoDTO salvar(PermissaoDTO permissaoDTO) {
		return modelMapper.map(repository.save(modelMapper.map(permissaoDTO, Permissao.class)), PermissaoDTO.class);
	}

}
