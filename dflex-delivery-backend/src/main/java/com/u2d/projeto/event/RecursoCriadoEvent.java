package com.u2d.projeto.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class RecursoCriadoEvent extends ApplicationEvent{

	private static final long serialVersionUID = -2234986035738235103L;
	
	private HttpServletResponse response;
	private Long codigo;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
	}

}
