package com.u2d.projeto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2d.projeto.config.security.util.PasswordUtil;
import com.u2d.projeto.dto.UsuarioDTO;
import com.u2d.projeto.exception.NegocioException;
import com.u2d.projeto.model.Empresa;
import com.u2d.projeto.model.Usuario;
import com.u2d.projeto.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    private EmpresaService empresaService;

    public UsuarioDTO save(UsuarioDTO usuarioDTO) throws NegocioException {
        validaDuplicidadeCPF(usuarioDTO);
        Empresa empresa = empresaService.findById(usuarioDTO.getIdEmpresa());
        String passwordEncoder = PasswordUtil.encoder(usuarioDTO.getSenha());
        usuarioDTO.setSenha(passwordEncoder);
        usuarioDTO.setEmpresa(empresa);
        Usuario retorno = repository.save(modelMapper.map(usuarioDTO, Usuario.class));
        return modelMapper.map(retorno, UsuarioDTO.class);
    }

    private void validaDuplicidadeCPF(UsuarioDTO usuarioDTO) throws NegocioException {
        Optional<Usuario> user = repository.findByCpf(usuarioDTO.getCpf());
        if(user.isPresent()){
            throw new NegocioException("Usuario já cadastrado com esse cpf");
        }
    }

    public List<UsuarioDTO> findAll() {
        List<Usuario> listaUsuario = repository.findAll();
        List<UsuarioDTO> listaRetorno = new ArrayList<>();
        listaUsuario.forEach(e -> {
            listaRetorno.add(modelMapper.map(e, UsuarioDTO.class));
        });
        return listaRetorno;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public UsuarioDTO findByCpf(String cpf) {
    	Optional<Usuario> user = repository.findByCpf(cpf);
    	if(user.isPresent()) {
    		user.get().setEmpresa(null);
    		user.get().setSenha(null);
    	}
    	return modelMapper.map(user.get(), UsuarioDTO.class);
    	
    }
}
