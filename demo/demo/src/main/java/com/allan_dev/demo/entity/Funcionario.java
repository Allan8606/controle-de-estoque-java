package com.allan_dev.demo.entity;


import com.allan_dev.demo.entity.enums.PerfilFuncionario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "funcionario")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Funcionario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Long funcionarioId;

    private String nome;

    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private PerfilFuncionario perfilFuncionario;





}
