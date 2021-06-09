package com.algafood.infra.jpa;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.model.Cozinha;
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

    CRUDCozinha crudCozinha = context.getBean(CRUDCozinha.class);
    List<Cozinha> cozinhaList = crudCozinha.listar();
    cozinhaList.forEach(System.out::println);

    Cozinha createCozinha = new Cozinha();
    createCozinha.setNome("Brasil");
    createCozinha.setId(1L);

    Cozinha cozinhaAdd = crudCozinha.salvar(createCozinha);
    System.out.println(cozinhaAdd);

    System.out.println("\n**"+ crudCozinha.getById(1L));
  }
}
