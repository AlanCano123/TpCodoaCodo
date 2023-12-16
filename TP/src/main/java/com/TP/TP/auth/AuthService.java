package com.TP.TP.auth;

import com.TP.TP.jwt.JwtService;
import com.TP.TP.models.User;
import com.TP.TP.models.enums.UserRole;
import com.TP.TP.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) throws Exception {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token=jwtService.getToken(user);

        if (token.isEmpty()){
            throw new Exception("Usuario o contraseña incorrecto.");
        }else{
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                //.password(request.getPassword())
                .name(request.getName())
                .surname(request.getSurname())
                .dni(request.getDni())
                .email(request.getEmail())
                .role(UserRole.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
