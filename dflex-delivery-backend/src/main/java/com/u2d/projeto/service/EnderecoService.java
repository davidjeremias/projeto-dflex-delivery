package com.u2d.projeto.service;

import com.u2d.projeto.dto.EnderecoDTO;
import com.u2d.projeto.repository.EnderecoRepository;
import com.u2d.projeto.util.RequestUtil;
import com.u2d.projeto.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
public class EnderecoService {

    private static final String CEP = "cep";
    private static final String URL_VIA_CEP = "https://viacep.com.br/ws";
    private static final String TYPE = "json";

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(EnderecoService.class);

    public Optional<EnderecoDTO> findByCepApi(Map<String, String[]> parameterMap) {
        String cep = RequestUtil.extrairParametro(parameterMap, CEP);
        ResponseEntity<EnderecoDTO> retorno = null;
        try {
            retorno = restTemplate.getForEntity(RequestUtil.montaPathRequisicao(URL_VIA_CEP, StringUtil.removeMascara(cep), TYPE), EnderecoDTO.class);
        }catch (HttpStatusCodeException e){
            logger.error("Empresa não Localizada");
        }
        return Optional.of(retorno.getBody());
    }
}
