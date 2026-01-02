package com.allan_dev.demo.controller;

import com.allan_dev.demo.dto.request.FuncionarioRequest;
import com.allan_dev.demo.dto.request.LoginRequest;
import com.allan_dev.demo.dto.request.RegistrarRequest;
import com.allan_dev.demo.dto.response.FuncionarioResponse;
import com.allan_dev.demo.dto.response.LoginResponse;
import com.allan_dev.demo.entity.Funcionario;
import com.allan_dev.demo.infra.security.TokenService;
import com.allan_dev.demo.repository.FuncionarioRepository;
import com.allan_dev.demo.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioService funcionarioService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){

        Funcionario funcionario = funcionarioRepository.findByEmail(loginRequest.email()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        if (passwordEncoder.matches(funcionario.getSenha(), loginRequest.senha())){
            String token = this.tokenService.gerarToken(funcionario);

            return ResponseEntity.ok(new LoginResponse(funcionario.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody FuncionarioRequest funcionarioRequest){

        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(funcionarioRequest.email());

        if (funcionario.isEmpty()){
            FuncionarioResponse novoFuncionario = funcionarioService.criar(funcionarioRequest);

            String token = this.tokenService.gerarToken(novoFuncionario);
        }



            return ResponseEntity.ok(new LoginResponse(funcionario.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }

}
