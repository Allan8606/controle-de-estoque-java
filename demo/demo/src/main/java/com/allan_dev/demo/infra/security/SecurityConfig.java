package com.allan_dev.demo.infra.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{

        return http
                // Desativa a proteção CSRF.
                // Em APIs REST com JWT não usamos sessão nem cookies,
                // então essa proteção não é necessária.
                .csrf(csrf -> csrf.disable())

                // Define que a aplicação não usa sessão.
                // Cada requisição precisa se autenticar sozinha através do token JWT.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Define as regras de acesso aos endpoints
                .authorizeHttpRequests(authorize -> {
                    // Libera o endpoint de login para qualquer pessoa
                    authorize.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();

                    //// Libera o endpoint de registrar para qualquer pessoa
                    authorize.requestMatchers(HttpMethod.POST, "/auth/registrar").permitAll();

                    // Qualquer outro endpoint exige autenticação
                    authorize.anyRequest().authenticated();
                        }
                )

                // Adiciona seu filtro JWT antes do filtro padrão de autenticação por usuário e senha
                // Isso garante que o token será validado antes do Spring tentar autenticar alguém.
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

                // Finaliza e constrói a cadeia de segurança
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        /*
         * Cria o objeto responsável por criptografar as senhas.
         * Ele transforma a senha digitada pelo usuário em um texto seguro,
         * impossível de ser revertido para a senha original.
         *
         * Esse mesmo objeto é usado:
         * - no cadastro, para salvar a senha criptografada
         * - no login, para comparar a senha digitada com a senha salva no banco
         */
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        /*
         * Recupera o mecanismo interno do Spring Security responsável
         * por validar login e senha.
         *
         * Ele usa automaticamente:
         * - o método loadUserByUsername()
         * - o PasswordEncoder (BCrypt)
         *
         * Você não cria isso manualmente.
         * O Spring já monta tudo por dentro.
         */
        return authenticationConfiguration.getAuthenticationManager();
    }



}
