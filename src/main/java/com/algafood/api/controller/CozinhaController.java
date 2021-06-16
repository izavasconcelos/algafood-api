package com.algafood.api.controller;

import com.algafood.domain.entity.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
//@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> findAll() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cozinha findById(@PathVariable Long id) {
        return cozinhaRepository.getById(id);
    }
}
