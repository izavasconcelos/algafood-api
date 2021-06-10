package com.algafood.infrastructure;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.entity.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context =
        new SpringApplicationBuilder(AlgafoodApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    CozinhaRepository repository = context.getBean(CozinhaRepository.class);
    List<Cozinha> cozinhaList = repository.findAll();
    cozinhaList.forEach(System.out::println);

    Cozinha createCozinha = new Cozinha();
    createCozinha.setNome("Brasil");

    Cozinha cozinhaAdd = repository.save(createCozinha);
    System.out.println(cozinhaAdd);

    System.out.println("\n**"+ repository.getById(1L));

    repository.delete(cozinhaAdd);
  }
}
