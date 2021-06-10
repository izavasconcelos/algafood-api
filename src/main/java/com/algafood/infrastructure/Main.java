package com.algafood.infrastructure;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.entity.Cozinha;
import com.algafood.domain.entity.Restaurante;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context =
        new SpringApplicationBuilder(AlgafoodApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    CozinhaRepository repository = context.getBean(CozinhaRepository.class);
    RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
    List<Cozinha> cozinhaList = repository.findAll();
    cozinhaList.forEach(System.out::println);

    Cozinha createCozinha = new Cozinha();
    createCozinha.setNome("Brasil");

    Cozinha cozinhaAdd = repository.save(createCozinha);
    System.out.println(cozinhaAdd);

    System.out.println("\n**"+ repository.getById(1L));

    repository.delete(cozinhaAdd);

    // *****************************************

    List<Restaurante> restauranteList = restauranteRepository.findAll();
    restauranteList.forEach(System.out::println);

    Restaurante restaurante = new Restaurante();
    restaurante.setNome("Brasil");
    restaurante.setTaxaFrete(BigDecimal.valueOf(2.50));

    Restaurante restauranteAdd = restauranteRepository.save(restaurante);
    System.out.println(restauranteAdd);

    System.out.println("\n**"+ restauranteRepository.getById(1L));

    //restauranteRepository.delete(restauranteAdd);
  }
}
