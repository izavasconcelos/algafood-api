package com.algafood.infrastructure;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.entity.Cidade;
import com.algafood.domain.entity.Estado;
import com.algafood.domain.repository.CidadeRepository;
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
    CidadeRepository cidadeRepository = context.getBean(CidadeRepository.class);

    List<Estado> restauranteList = estadoRepository.findAll();
    restauranteList.forEach(System.out::println);
    Estado estado = new Estado();
    estado.setNome("RS");
    Estado estado1 = estadoRepository.save(estado);
    System.out.println(estado1);
    System.out.println("\n**"+ estadoRepository.getById(1L));
    //restauranteRepository.delete(restauranteAdd);

    List<Cidade> cidadeList = cidadeRepository.findAll();
    cidadeList.forEach(System.out::println);
    Cidade cidade = new Cidade();
    cidade.setNome("RS");
    cidade.setEstado(estado1);
    Cidade cidade1 = cidadeRepository.save(cidade);
    System.out.println(cidade1);
    System.out.println("\n**"+ cidadeRepository.getById(1L));

  }
}
