package com.algafood.domain.restaurant.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemOrder {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private Integer quantity;
  @Column private BigDecimal unitPrice;
  @Column private BigDecimal totalPrice;
  @Column private String observation;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Order order;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Product product;
}
