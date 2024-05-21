package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.config.security.TokenService;
import br.com.fiap.contatos.dto.TokenDto;
import br.com.fiap.contatos.dto.UsuarioCadastroDto;
import br.com.fiap.contatos.dto.UsuarioExibitionDto;
import br.com.fiap.contatos.dto.UsuarioLoginDto;
import br.com.fiap.contatos.model.Usuario;
import br.com.fiap.contatos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto){
        UsernamePasswordAuthenticationToken usernamePassword=
                new UsernamePasswordAuthenticationToken(
                        usuarioLoginDto.email(),
                        usuarioLoginDto.senha()
                );
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibitionDto UsuarioExibitionDtoRegistrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
        UsuarioExibitionDto usuarioSalvo = null;
        usuarioSalvo = usuarioService.gravar(usuarioCadastroDto);
        return usuarioSalvo;
    }


}
