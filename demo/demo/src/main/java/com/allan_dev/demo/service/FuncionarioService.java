package com.allan_dev.demo.service;

import com.allan_dev.demo.dto.request.FuncionarioRequest;
import com.allan_dev.demo.dto.response.FuncionarioResponse;
import com.allan_dev.demo.entity.Funcionario;
import com.allan_dev.demo.mapper.FuncionarioMapper;
import com.allan_dev.demo.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public FuncionarioResponse criar(FuncionarioRequest funcionarioRequest){
        Optional<Funcionario> byEmail = funcionarioRepository.findByEmail(funcionarioRequest.email());

        if (byEmail.isPresent())
        {
            Funcionario funcionario = FuncionarioMapper.paraFuncionario(funcionarioRequest);
            funcionario.setSenha(passwordEncoder.encode(funcionarioRequest.senha()));

            funcionarioRepository.save(funcionario);
            return FuncionarioMapper.paraResponse(funcionario);
        }
        return null;
    }
}
