package com.allan_dev.demo.infra.security;

import com.allan_dev.demo.entity.Funcionario;
import com.allan_dev.demo.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final FuncionarioRepository funcionarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o funcionário no banco usando o email (username)
        // Se não encontrar, lança exceção e o login falha automaticamente
        Funcionario funcionario = this.funcionarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

        /*
         * Cria um objeto User do Spring Security.
         * Esse objeto é diferente da sua entidade Funcionario.
         * Ele é usado apenas pelo mecanismo interno de autenticação.
         */
        return new org.springframework.security.core.userdetails.User(
                funcionario.getEmail(),   // username usado pelo Spring Security
                funcionario.getSenha(),   // senha criptografada do banco
                new ArrayList<>()         // lista de permissões (vazia por enquanto)
        );
    }
}
