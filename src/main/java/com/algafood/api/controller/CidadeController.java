package com.algafood.api.controller;

import com.algafood.domain.entity.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll() {
        return ResponseEntity.ok(cidadeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id) {

		Cidade cidade = cidadeRepository.getById(id);
        if (cidade != null) {
            return ResponseEntity.ok(cidade);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
	public ResponseEntity<Cidade> save(@RequestBody Cidade cidade) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.save(cidade));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cidade> update(@PathVariable Long id, @RequestBody Cidade cidade) {
		Cidade cidadeAtual = cidadeRepository.getById(id);
		if (cidadeAtual != null) {
		  BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			cidadeService.save(cidadeAtual);

		  return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cidade> delete(@PathVariable Long id) {
		cidadeService.delete(id);

    	return ResponseEntity.noContent().build();
	}
}
