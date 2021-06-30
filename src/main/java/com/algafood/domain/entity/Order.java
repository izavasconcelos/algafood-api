package com.algafood.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "orders")
public class Order {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String subtotal;
  @Column private String total;

  @Column(name = "shipping_fee")
  private BigDecimal shippingFee;

  @Embedded private Address address;

  private StatusOrder status;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, columnDefinition = "datetime")
  private LocalDateTime createdAt;

  @Column(name = "confirmation_date", nullable = false, columnDefinition = "datetime")
  private LocalDateTime confirmationDate;

  @Column(name = "cancellation_date", nullable = false, columnDefinition = "datetime")
  private LocalDateTime cancellationDate;

  @Column(name = "delivery_date", nullable = false, columnDefinition = "datetime")
  private LocalDateTime deliveryDate;

  @ManyToOne
  @JoinColumn(nullable = false)
  private PaymentType paymentType;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Restaurant restaurant;

  @ManyToOne
  @JoinColumn(name = "user_client_id", nullable = false)
  private User client;

  @OneToMany(mappedBy = "order")
  private List<ItemOrder> items = new ArrayList<>();
}
