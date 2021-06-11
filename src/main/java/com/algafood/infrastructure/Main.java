package com.algafood.infrastructure;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.entity.Estado;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.repository.EstadoRepository;
import com.algafood.domain.repository.RestauranteRepository;
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
    RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
    EstadoRepository estadoRepository = context.getBean(EstadoRepository.class);

    List<Estado> restauranteList = estadoRepository.findAll();
    restauranteList.forEach(System.out::println);

    Estado estado = new Estado();
    estado.setNome("RS");


    Estado estado1 = estadoRepository.save(estado);
    System.out.println(estado1);

    System.out.println("\n**"+ estadoRepository.getById(1L));

    //restauranteRepository.delete(restauranteAdd);
  }
}
