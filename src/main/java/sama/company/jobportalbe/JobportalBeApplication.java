package sama.company.jobportalbe;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import sama.company.jobportalbe.models.ApplicationUser;
import sama.company.jobportalbe.models.Role;
import sama.company.jobportalbe.repository.RoleRepository;
import sama.company.jobportalbe.repository.UserRepository;

@SpringBootApplication
public class JobportalBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobportalBeApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent())
                return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("GIVER"));
            roleRepository.save(new Role("SEEKER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            ApplicationUser admin = new ApplicationUser(1, "Admin", "Admin", "admin@admin.com", "admin",
                    passwordEncoder.encode("admin"), roles, null);
            userRepository.save(admin);
        };
    }

}
