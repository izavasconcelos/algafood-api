package com.algafood.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tab_restaurantes")
public class Restaurante {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String nome;

  @Column(name = "taxa_frete", nullable = false)
  private BigDecimal taxaFrete;

  @ManyToOne
  @JoinColumn(name = "cozinha_id")
  private Kitchen kitchen;

  @JsonIgnore
  @CreationTimestamp
  @Column(nullable = false, columnDefinition = "datetime")
  private LocalDateTime dataCadastro;

  @JsonIgnore
  @UpdateTimestamp
  @Column(nullable = false, columnDefinition = "datetime")
  private LocalDateTime dataAtualizacao;

  @JsonIgnore @Embedded private Endereco endereco;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
      name = "restaurante_forma_pagamento",
      joinColumns = @JoinColumn(name = "restaurante_id"),
      inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
  private List<FormaPagamento> formaPagamentos = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "restaurante")
  private List<Produto> produtos = new ArrayList<>();
}
