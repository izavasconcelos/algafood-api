package com.algafood.infrastructure;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.entity.Cidade;
import com.algafood.domain.entity.Estado;
import com.algafood.domain.entity.FormaPagamento;
import com.algafood.domain.entity.Permissao;
import com.algafood.domain.repository.*;
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
    FormaPagamentoRepository formaPagamentoRepository = context.getBean(FormaPagamentoRepository.class);
    PermissaoRepository permissaoRepository = context.getBean(PermissaoRepository.class);


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

    List<FormaPagamento> formaPagamentoList = formaPagamentoRepository.findAll();
    formaPagamentoList.forEach(System.out::println);
    FormaPagamento formaPagamento = new FormaPagamento();
    formaPagamento.setDescricao("Visa");
    formaPagamento.setRestaurante(restauranteRepository.getById(1L));
    FormaPagamento formaPagamento1 = formaPagamentoRepository.save(formaPagamento);
    System.out.println(formaPagamento1);
    System.out.println("\n**"+ formaPagamentoRepository.getById(1L));

    List<Permissao> permissaoList = permissaoRepository.findAll();
    permissaoList.forEach(System.out::println);
    Permissao permissao = new Permissao();
    permissao.setDescricao("Liberado");
    permissao.setNome("Alvara IX");
    Permissao permissao1 = permissaoRepository.save(permissao);
    System.out.println(permissao1);
    System.out.println("\n**"+ permissaoRepository.getById(1L));

    System.out.println(repository.findByName("Tailandesa"));
  }
}
