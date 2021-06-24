package com.algafood.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tab_usuario")
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "usuario_grupo",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private List<Grupo> grupos = new ArrayList<>();

}
