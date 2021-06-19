package com.algafood.domain.service;

import com.algafood.domain.entity.Estado;
import com.algafood.domain.exception.EntityUsedException;
import com.algafood.domain.exception.StateNotFoundException;
import com.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado save(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void delete(Long id) {
		try {
			Optional<Estado> estado = estadoRepository.findById(id);
			if (estado.isPresent()) {
				estadoRepository.delete(estado.get());
			} else {
				throw new StateNotFoundException("Estado Não Encontrado!");
			}
		} catch (DataIntegrityViolationException ex) {
			throw new EntityUsedException(String.format("Entidade Id=%d em uso!", id));
		}
	}
}
