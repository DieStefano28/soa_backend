package com.example.sercoplus.sercoplus_soa;

import com.example.sercoplus.sercoplus_soa.model.Cliente;
import com.example.sercoplus.sercoplus_soa.model.Rol;
import com.example.sercoplus.sercoplus_soa.model.Usuario;
import com.example.sercoplus.sercoplus_soa.repository.ClienteRepository;
import com.example.sercoplus.sercoplus_soa.repository.RolRepository;
import com.example.sercoplus.sercoplus_soa.repository.UsuarioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class SercoplusSoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SercoplusSoaApplication.class, args);
    }
@Bean
CommandLineRunner runner(
        ClienteRepository clienteRepository,
        UsuarioRepository usuarioRepository,
        RolRepository rolRepository,
        PasswordEncoder encoder
) {
    return args -> {
        // 👤 Crear Cliente si no existe
        if (clienteRepository.findByCorreoElectronico("diego@email.com").isEmpty()) {
            Cliente cliente = Cliente.builder()
                    .nombre("Diego")
                    .apellidos("Prueba")
                    .correoElectronico("diego@email.com")
                    .contrasena(encoder.encode("654321"))
                    .fechaNacimiento(LocalDate.of(2000, 1, 1))
                    .estado(true)
                    .build();

            clienteRepository.save(cliente);
            System.out.println("✅ Cliente insertado con éxito.");
        } else {
            System.out.println("ℹ️ Cliente ya existe, no se inserta.");
        }

        // 🛡️ Crear Rol si no existe
        Rol rol = rolRepository.findByNombreRol("ROLE_ADMIN")
                .orElseGet(() -> {
                    Rol nuevoRol = new Rol();
                    nuevoRol.setNombreRol("ROLE_ADMIN");
                    nuevoRol.setDescripcion("Rol de administrador");
                    return rolRepository.save(nuevoRol);
                });

        // 👨‍💼 Crear Usuario si no existe
        if (usuarioRepository.findByCorreoElectronico("admin@sercoplus.com").isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setNombre("Admin");
            usuario.setApellidos("Sercoplus");
            usuario.setCorreoElectronico("admin@sercoplus.com");
            usuario.setContrasena(encoder.encode("admin123"));
            usuario.setRol(rol);
            usuario.setEstado(true);
            usuario.setFechaCreacion(java.time.LocalDateTime.now() );
                
            usuarioRepository.save(usuario);
            System.out.println("✅ Usuario ADMIN insertado.");
        } else {
            System.out.println("ℹ️ Usuario ya existe, no se inserta.");
        }
    };
}

}
