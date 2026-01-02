package com.allan_dev.demo.dto.response;

import com.allan_dev.demo.entity.enums.PerfilFuncionario;
import lombok.Builder;

@Builder
public record FuncionarioResponse(Long funcionario_id, String nome, String email, PerfilFuncionario perfilFuncionario) {
}
