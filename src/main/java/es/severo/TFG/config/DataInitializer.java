package es.severo.TFG.config;

import es.severo.TFG.entities.Restaurante;
import es.severo.TFG.entities.Tamano;
import es.severo.TFG.entities.Usuario;
import es.severo.TFG.repository.RestauranteRepository;
import es.severo.TFG.repository.TamanoRepository;
import es.severo.TFG.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(RestauranteRepository restauranteRepo,
                                      UsuarioRepository usuarioRepo,
                                      TamanoRepository tamanoRepo) {
        return args -> {

            // Crear restaurante si no existe
            if (restauranteRepo.count() == 0) {
                Restaurante restaurante = new Restaurante();
                restaurante.setNombre("Kebab del Centro");
                restaurante.setCalle("Calle Genérica");
                restaurante.setTelefono("123456789");
                restaurante.setCif("A12345678");
                restaurante.setEmail("kebab@gmail.com");
                restaurante.setWeb("http://kebab.com");

                restaurante = restauranteRepo.save(restaurante);

                // Crear usuario admin
                Usuario admin = new Usuario();
                admin.setNombre("admin");
                admin.setContrasena("admin");
                admin.setRol("Administrador");
                admin.setRestaurante(restaurante);

                usuarioRepo.save(admin);
            }

            // Crear tamaños si no existen
            if (tamanoRepo.count() == 0) {
                Tamano pequeno = new Tamano();
                pequeno.setNombre("Pequeño");

                Tamano mediano = new Tamano();
                mediano.setNombre("Mediano");

                Tamano grande = new Tamano();
                grande.setNombre("Grande");

                Tamano familiar = new Tamano();
                grande.setNombre("Familiar");

                tamanoRepo.saveAll(Arrays.asList(pequeno, mediano, grande, familiar));
            }
        };
    }
}
