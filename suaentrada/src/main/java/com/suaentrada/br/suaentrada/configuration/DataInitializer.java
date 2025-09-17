package com.suaentrada.br.suaentrada.configuration;

import com.suaentrada.br.suaentrada.model.Role;
import com.suaentrada.br.suaentrada.model.UsuarioModel;
import com.suaentrada.br.suaentrada.repository.RoleRepository;
import com.suaentrada.br.suaentrada.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try {
            // Criar roles se não existirem
            Role adminRole = roleRepository.findByNomeRole("ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setNomeRole("ADMIN");
                        return roleRepository.save(role);
                    });

            Role userRole = roleRepository.findByNomeRole("USER")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setNomeRole("USER");
                        return roleRepository.save(role);
                    });

            // Criar usuário admin se não existir
            if (!usuarioRepository.existsByEmailUsuario("admin@suaentrada.com")) {
                UsuarioModel admin = new UsuarioModel();
                admin.setNomeUsuario("Administrador");
                admin.setEmailUsuario("admin@suaentrada.com");
                admin.setSenhaUsuario(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of(adminRole));

                usuarioRepository.save(admin);

                System.out.println("=== DADOS INICIAIS CRIADOS ===");
                System.out.println("Usuário admin criado!");
                System.out.println("Email: admin@suaentrada.com");
                System.out.println("Senha: admin123");
                System.out.println("Role: ADMIN");
                System.out.println("==============================");
            } else {
                System.out.println("Usuário admin já existe no banco.");
            }

        } catch (Exception e) {
            System.err.println("Erro ao criar dados iniciais: " + e.getMessage());
            e.printStackTrace();
        }
    }
}