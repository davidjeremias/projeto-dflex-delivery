package com.u2d.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Repository;

import com.u2d.projeto.model.Empresa;

@Eager
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
}
