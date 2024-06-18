package com.example.proyectoIntegrador11.security;

import com.example.proyectoIntegrador11.entity.Usuario;
import com.example.proyectoIntegrador11.entity.UsuarioRole;
import com.example.proyectoIntegrador11.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrarUser = "user";
        String passCifradoUser = passwordEncoder.encode(passSinCifrarUser);
        Usuario usuarioUser = new Usuario("usuarioUser", UsuarioRole.ROLE_USER, passCifradoUser, "user@user.com", "user");

        String passSinCifrarAdmin = "admin";
        String passCifradoAdmin = passwordEncoder.encode(passSinCifrarAdmin);
        Usuario usuarioAdmin = new Usuario("usuarioAdmin", UsuarioRole.ROLE_ADMIN, passCifradoAdmin, "admin@admin.com", "admin");

        usuarioRepository.save(usuarioUser);
        usuarioRepository.save(usuarioAdmin);

    }
}