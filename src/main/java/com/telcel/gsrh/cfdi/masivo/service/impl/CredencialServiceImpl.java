package com.telcel.gsrh.cfdi.masivo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.cfdi.masivo.domain.Credencial;
import com.telcel.gsrh.cfdi.masivo.mapper.CredencialMapper;
import com.telcel.gsrh.cfdi.masivo.service.CredencialService;

@Service("credencialService")
public class CredencialServiceImpl implements CredencialService {
	@Autowired
	private CredencialMapper credencialMapper;
	
	public Credencial getCredencialAppServerColdView() {
		return credencialMapper.getCredencialAppServerColdView();
	}
}
