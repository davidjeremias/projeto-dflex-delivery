package com.u2d.projeto.service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.u2d.projeto.dto.EmpresaDTO;
import com.u2d.projeto.util.DateUtil;
import com.u2d.projeto.util.RequestUtil;
import com.u2d.projeto.util.StringUtil;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.u2d.projeto.model.Empresa;
import com.u2d.projeto.repository.EmpresaRepository;
import com.u2d.projeto.util.ZipArchive;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmpresaService {

	private static final String CNPJ = "cnpj";
	private static final String URL_RECEITA = "https://www.receitaws.com.br/v1/cnpj";

	@Autowired
	private EmpresaRepository repository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(EmpresaService.class);

	public List<Empresa> findAll() {
		return repository.findAll();
	}

	public Optional<EmpresaDTO> save(EmpresaDTO empresaDTO) {
		Empresa empresa = modelMapper.map(empresaDTO, Empresa.class);
		if(empresa.getCnpj() != null){
			empresa.setCnpj(StringUtil.removeMascara(empresa.getCnpj()));
		}
		empresa.setDataAbertura(empresaDTO.getDataAbertura());
		return Optional.of(modelMapper.map(repository.save(empresa), EmpresaDTO.class));
	}

	public Empresa findById(Long codigo) {
		Optional<Empresa> empresa = repository.findById(codigo);
		if(empresa.isPresent())
			return empresa.get();
		return null;
	}

	public Empresa update(Long codigo, Empresa empresa) {
		Empresa empresaSalva = findById(codigo);
		if(empresaSalva.getId() != null)
			return repository.save(empresa);
		return null;
	}

	public void delete(Long codigo) {
		Empresa empresaSalva = findById(codigo);
		if(empresaSalva != null)
			repository.delete(empresaSalva);
	}

	public Optional<EmpresaDTO> findByCnpjReceita(Map<String, String[]> parameterMap) {
		String cnpj = RequestUtil.extrairParametro(parameterMap, CNPJ);
		ResponseEntity<EmpresaDTO> retorno = null;
		try {
			retorno = restTemplate.getForEntity(RequestUtil.montaPathRequisicao(URL_RECEITA, StringUtil.removeMascara(cnpj)), EmpresaDTO.class);
			if(retorno != null & retorno.getBody() != null & retorno.getBody().getAbertura() != null){
				retorno.getBody().setDataAbertura(DateUtil.stringFormatBrasilToLocalDate(retorno.getBody().getAbertura()));
				retorno.getBody().setAbertura(null);
			}
		}catch (HttpStatusCodeException e){
			logger.error("Empresa não Localizada");
		}
		return Optional.of(retorno.getBody());
	}
}
