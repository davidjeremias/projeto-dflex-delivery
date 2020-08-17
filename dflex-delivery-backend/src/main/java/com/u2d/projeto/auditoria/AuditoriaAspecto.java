package com.u2d.projeto.auditoria;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.u2d.projeto.model.Auditoria;
import com.u2d.projeto.repository.AuditoriaRepository;


@Aspect
@Component
@Transactional
public class AuditoriaAspecto {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AuditoriaRepository auditoriaRepository;
	
	 @Around("@annotation(auditoriainterface)")
	 public Object around(ProceedingJoinPoint point, AuditoriaInterface auditoriainterface) throws Throwable {
		 //Principal principal = SecurityContextHolder.getContext().getAuthentication();
		 registrarAuditoria(auditoriainterface.funcionalidade(),auditoriainterface.evento(), "EU",request.getLocalName());
		 return point.proceed();
	 }
	 
	 private void registrarAuditoria(String funcionalidade, TipoEventoAuditoriaEnum tipoEvento, String usuario, String host) {
		 
		 Auditoria aud = Auditoria.builder()
				 .funcionalidade(funcionalidade)
				 .evento(tipoEvento)
				 .dataHora(LocalDateTime.now())
				 .usuario(usuario)
				 .hostname(host).build();
		 
		 auditoriaRepository.save(aud);
	 }
}
