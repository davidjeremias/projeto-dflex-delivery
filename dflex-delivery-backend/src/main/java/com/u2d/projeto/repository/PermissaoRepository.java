package com.u2d.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import com.u2d.projeto.model.Permissao;

@Eager
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
}
