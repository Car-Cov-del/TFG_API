package es.severo.TFG.config;

import es.severo.TFG.entities.*;
import es.severo.TFG.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(RestauranteRepository restauranteRepo,
                                      UsuarioRepository usuarioRepo,
                                      TamanoRepository tamanoRepo,
                                      CategoriaRepository categoriaRepo,
                                      AlergenoRepository alergenoRepo) {
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
                familiar.setNombre("Familiar");

                tamanoRepo.saveAll(Arrays.asList(pequeno, mediano, grande, familiar));
            }

            // Crear categoría "Ninguna" si no existe
            if (!categoriaRepo.existsByNombre("Ninguna")) {
                Categoria categoria = new Categoria();
                categoria.setNombre("Ninguna");
                categoria.setImagen(null);

                categoriaRepo.save(categoria);
            }

            //Crear los alergenos
            if (alergenoRepo.count() == 0) {
                List<Alergeno> alergenos = List.of(
                        new Alergeno("Cereales con gluten", "https://images.icon-icons.com/2940/PNG/512/gluten_allergen_food_allergens_icon_183726.png", "Gluten"),
                        new Alergeno("Crustáceos y productos a base de crustáceos", "https://images.icon-icons.com/2940/PNG/512/crustacean_allergen_food_allergens_icon_183733.png", "Crustáceos"),
                        new Alergeno("Huevos y productos derivados", "https://images.icon-icons.com/2940/PNG/512/egg_allergen_food_allergens_icon_183730.png", "Huevos"),
                        new Alergeno("Pescado y productos a base de pescados", "https://images.icon-icons.com/2940/PNG/512/fish_allergen_food_allergens_icon_183728.png", "Pescado"),
                        new Alergeno("Cacahuetes, productos a base de cacahuetes y frutos secos", "https://images.icon-icons.com/2940/PNG/512/peanuts_allergen_food_allergens_icon_183731.png", "Cacahuetes"),
                        new Alergeno("Soja y productos a base de soja", "https://images.icon-icons.com/2940/PNG/512/soy_allergen_food_allergens_icon_183721.png", "Soja"),
                        new Alergeno("Leche y sus derivados (incluida la lactosa)", "https://images.icon-icons.com/2940/PNG/512/milk_allergen_food_allergens_icon_183724.png", "Lácteos"),
                        new Alergeno("Frutos de cáscara y productos derivados", "https://images.icon-icons.com/2940/PNG/512/nuts_allergen_food_allergens_icon_183722.png", "Frutos con cáscara"),
                        new Alergeno("Apio y productos derivados", "https://images.icon-icons.com/2940/PNG/512/celery_allergen_food_allergens_icon_183723.png", "Apio"),
                        new Alergeno("Mostaza y productos a base de mostaza", "https://images.icon-icons.com/2940/PNG/512/mustard_allergen_food_allergens_icon_183732.png", "Mostaza"),
                        new Alergeno("Granos o semillas de sésamo y productos a base de sésamo", "https://images.icon-icons.com/2940/PNG/512/sesame_allergen_food_allergens_icon_183729.png", "Sésamo"),
                        new Alergeno("Dióxido de azufre y sulfitos", "https://images.icon-icons.com/2940/PNG/512/sulfites_allergen_food_allergens_icon_183725.png", "Sulfitos"),
                        new Alergeno("Altramuces y productos a base de altramuces", "https://images.icon-icons.com/2940/PNG/512/lupins_allergen_food_allergens_icon_183720.png", "Altramuces"),
                        new Alergeno("Moluscos y crustáceos y productos a base de estos", "https://images.icon-icons.com/2940/PNG/512/shellfish_allergen_food_allergens_icon_183727.png", "Moluscos")
                );

                alergenoRepo.saveAll(alergenos);
            }
        };
    }
}
