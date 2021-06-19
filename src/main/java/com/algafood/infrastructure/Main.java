package com.algafood.infrastructure;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.repository.*;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

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
    FormaPagamentoRepository formaPagamentoRepository = context.getBean(FormaPagamentoRepository.class);
    PermissaoRepository permissaoRepository = context.getBean(PermissaoRepository.class);

  }
}
