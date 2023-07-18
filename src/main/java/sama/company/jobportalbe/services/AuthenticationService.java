package sama.company.jobportalbe.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import sama.company.jobportalbe.dto.LoginResponseDTO;
import sama.company.jobportalbe.models.ApplicationUser;
import sama.company.jobportalbe.models.Role;
import sama.company.jobportalbe.repository.RoleRepository;
import sama.company.jobportalbe.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenservice;

    public ApplicationUser registerUser(String firstName, String lastName, String email, String username,
            String password, String role) throws Exception {

        if (userRepository.findByUsername(username).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");

        if (userRepository.findByEmail(email).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority(role).get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository
                .save(new ApplicationUser(0, firstName, lastName, email, username, encodedPassword, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenservice.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }
    }

}
