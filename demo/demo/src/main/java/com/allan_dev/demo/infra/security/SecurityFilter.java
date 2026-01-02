package com.allan_dev.demo.infra.security;


import com.allan_dev.demo.entity.Funcionario;
import com.allan_dev.demo.repository.FuncionarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final FuncionarioRepository funcionarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Recupera o token JWT do header Authorization da requisição
        // Ex: "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
        String token = this.recoverToken(request);

        // Valida o token recebido.
        // Se for válido, retorna o email do usuário.
        // Se for inválido ou expirado, retorna null.
        String login = tokenService.validarToken(token);

        // Verifica se o token foi considerado válido
        if (login != null) {

            // Busca no banco o funcionário usando o email extraído do token
            Funcionario funcionario = funcionarioRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

            // Cria a lista de permissões (roles) do usuário.
            // Como o funcionário só possui UM perfil,
            // aqui criamos uma lista com apenas uma role.
            var authorities = List.of(
                    new SimpleGrantedAuthority(
                            "ROLE_" + funcionario.getPerfilFuncionario().name()
                    )
            );

            // Cria o objeto que representa o usuário autenticado dentro do Spring Security
            var authentication = new UsernamePasswordAuthenticationToken(
                    funcionario,     // objeto do usuário
                    null,            // não usamos senha aqui
                    authorities      // permissões do usuário
            );

            // Registra o usuário autenticado no contexto de segurança do Spring
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continua o fluxo da requisição até o Controller
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        if(authHeader == null) return null;

        return authHeader.replace("Bearer", "");

    }
}
