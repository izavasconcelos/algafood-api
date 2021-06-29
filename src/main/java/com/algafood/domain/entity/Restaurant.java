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
@Table(name = "restaurant")
public class Restaurant {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;

  @Column(name = "shipping_fee", nullable = false)
  private BigDecimal shippingFee;

  @ManyToOne
  @JoinColumn(name = "kitchen_id")
  private Kitchen kitchen;

  @JsonIgnore
  @CreationTimestamp
  @Column(name = "created_at", nullable = false, columnDefinition = "datetime")
  private LocalDateTime createdAt;

  @JsonIgnore
  @UpdateTimestamp
  @Column(name = "changed_at", nullable = false, columnDefinition = "datetime")
  private LocalDateTime changedAt;

  @JsonIgnore @Embedded private Address address;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
      name = "restaurant_payment_type",
      joinColumns = @JoinColumn(name = "restaurant_id"),
      inverseJoinColumns = @JoinColumn(name = "payment_type_id"))
  private List<FormaPagamento> paymentType = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "restaurant")
  private List<Product> products = new ArrayList<>();
}
