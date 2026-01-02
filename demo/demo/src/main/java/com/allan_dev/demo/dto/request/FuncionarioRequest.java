package com.allan_dev.demo.dto.request;

import com.allan_dev.demo.entity.enums.PerfilFuncionario;

public record FuncionarioRequest(String nome, String email, String senha, PerfilFuncionario perfilFuncionario) {
}
