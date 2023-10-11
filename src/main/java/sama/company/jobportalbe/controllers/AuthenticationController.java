package sama.company.jobportalbe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sama.company.jobportalbe.dto.LoginDTO;
import sama.company.jobportalbe.dto.LoginResponseDTO;
import sama.company.jobportalbe.dto.RegistrationDTO;
import sama.company.jobportalbe.models.ApplicationUser;
import sama.company.jobportalbe.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
        try {
            String firstName = body.getFirstName();
            String lastName = body.getLastName();
            String email = body.getEmail();
            String username = body.getUsername();
            String password = body.getPassword();
            String role = body.getRole();
            return authenticationService.registerUser(firstName, lastName, email, username, password, role);
        } catch (Exception e) {
            if (e instanceof ResponseStatusException) {
                throw (ResponseStatusException) e;
            } else {
                throw new ResponseStatusException(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                        "Something went wrong");
            }
        }
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginDTO body) {
        try {
            return authenticationService.loginUser(body.getUsername(), body.getPassword());
        } catch (Exception e) {
            if (e instanceof ResponseStatusException) {
                throw (ResponseStatusException) e;
            } else {
                throw new ResponseStatusException(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                        "Something went wrong");
            }
        }
    }
}
