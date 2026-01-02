package com.allan_dev.demo.mapper;

import com.allan_dev.demo.dto.request.FuncionarioRequest;
import com.allan_dev.demo.dto.response.FuncionarioResponse;
import com.allan_dev.demo.entity.Funcionario;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FuncionarioMapper {

    public static Funcionario paraFuncionario(FuncionarioRequest request){
        return Funcionario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(request.senha())
                .perfilFuncionario(request.perfilFuncionario())
                .build();
    }

    public static FuncionarioResponse paraResponse(Funcionario funcionario){
        return FuncionarioResponse.builder()
                .funcionario_id(funcionario.getFuncionarioId())
                .nome(funcionario.getNome())
                .email(funcionario.getEmail())
                .perfilFuncionario(funcionario.getPerfilFuncionario())
                .build();
    }
}
