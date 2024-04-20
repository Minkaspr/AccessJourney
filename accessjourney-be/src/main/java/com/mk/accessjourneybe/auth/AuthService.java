package com.mk.accessjourneybe.auth;

import com.mk.accessjourneybe.entity.Role;
import com.mk.accessjourneybe.entity.User;
import com.mk.accessjourneybe.jwt.JwtService;
import com.mk.accessjourneybe.repository.RoleRepository;
import com.mk.accessjourneybe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        UserDetails userDetails = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request){

        // Busca el rol "Client"
        Role userRole = roleRepository.findByName("Client")
                .orElseThrow(() -> new RuntimeException("Role not found: Client"));

        // Crea el nuevo usuario
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .identityDocumentType(request.getIdentityDocumentType())
                .identityDocumentNumber(request.getIdentityDocumentNumber())
                .dateOfBirth(request.getDateOfBirth())
                .isActive(true)
                .role(userRole)
                .build();

        // Guarda el nuevo usuario en la base de datos
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}

