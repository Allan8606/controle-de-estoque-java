package com.allan_dev.demo.infra.security;


import com.allan_dev.demo.entity.Funcionario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    //Chave Privada
    @Value("${api.security.tokn.secret}")
    private String secret;

    //Método para criar o token.
    public String gerarToken(Funcionario funcionario){
        try{
            /*Define a nossa chave de gerar token para fazer as tarefas.
            Ela define como o token vai ser trancado com uma chave secreta.
            HMAC256 é um tipo de algoritmo de assinatura.
             */
            Algorithm algorithm = Algorithm.HMAC256(secret);

            //Geraçcao do token
            String token = JWT.create()
                    //Quem está gerando o token
                    .withIssuer("API controle de estoque")
                    //Quem é o sujeito que está ganhando o token
                    .withSubject(funcionario.getEmail())
                    .withExpiresAt(this.gerarDataExpiracao())// quando vai expirar
                    .sign(algorithm);//Ela assina o token JWT usando a chave secreta e o algoritmo que você definiu. Essa linha é o momento em que o token deixa de ser “só um texto” e passa a ser um token seguro e confiável.

            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao autenticar");
        }
    }


    //Validar o token
    public String validarToken(String token){
        try{
            /*
             * Cria o mesmo algoritmo de assinatura usado na criação do token.
             * Se o algoritmo ou o secret forem diferentes,
             * a assinatura não será reconhecida e o token será inválido.
             */
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    /*
                     * Define quem deveria ter emitido esse token.
                     * Se o token não tiver sido criado com esse mesmo issuer,
                     * ele será automaticamente considerado inválido.
                     */
                    .withIssuer("API controle de estoque")
                    /*
                     * Monta o verificador com todas as regras definidas acima
                     * (algoritmo + issuer).
                     */
                    .build()
                    /*
                     * Verifica o token recebido.
                     * Aqui o Spring JWT faz tudo:
                     * - confere a assinatura
                     * - verifica se o token foi alterado
                     * - verifica se já expirou
                     */
                    .verify(token)
                    /*
                     * Se tudo estiver correto, devolve o subject do token,
                     * que no seu caso é o email do funcionário.
                     */
                    .getSubject();

        } catch (JWTVerificationException exception){
            return null;
        }
    }


    //Método que define quando vai expirar nosso token.
    private Instant gerarDataExpiracao(){
        /*
        * Pega o instante atual do mundo e soma 2 horas.
        * O token vale por 2 horas, em qualquer lugar do planeta.
        */
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }
}
