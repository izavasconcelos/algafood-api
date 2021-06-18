package com.algafood.domain.service;

import com.algafood.domain.entity.Cidade;
import com.algafood.domain.entity.Estado;
import com.algafood.domain.exception.CityBadRequestException;
import com.algafood.domain.exception.CityNotFoundException;
import com.algafood.domain.exception.EntityUsedException;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public Cidade save(Cidade cidade) {
		Estado estado = estadoRepository.getById(cidade.getEstado().getId());
		if(estado != null) {
			return cidadeRepository.save(cidade);
		} else {
			throw new CityBadRequestException("Estado Não Existe");
		}
	}

	public void delete(Long id) {
		try {
			Cidade cidade = cidadeRepository.getById(id);
			if (cidade != null) {
				cidadeRepository.delete(cidade);
			} else {
				throw new CityNotFoundException("Cidade Não Encontrada!");
			}
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}
}
