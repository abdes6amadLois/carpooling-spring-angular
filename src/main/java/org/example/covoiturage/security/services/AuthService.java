package org.example.covoiturage.security.services;


import lombok.RequiredArgsConstructor;
import org.example.covoiturage.dto.request.AuthRequest;
import org.example.covoiturage.entities.Conducteur;
import org.example.covoiturage.entities.Passager;
import org.example.covoiturage.repository.ConducteurRepository;
import org.example.covoiturage.repository.PassagerRepository;
import org.example.covoiturage.security.config.JwtUtils;
import org.example.covoiturage.security.entities.Role;
import org.example.covoiturage.security.entities.User;
import org.example.covoiturage.security.repository.UserRepository;
import org.example.covoiturage.service.ConducteurService;
import org.example.covoiturage.service.PassagerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final ConducteurService conducteurService;
    private final PassagerService passagerService;

    public String register(AuthRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        user.setRole(new ArrayList<>());
        user.getRole().add(Role.USER);

        userRepository.save(user);

        if ("CONDUCTEUR".equals(request.getRole())) {
            Conducteur conducteur = Conducteur.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .telephone(request.getTelephone())
                    .numCarteNational(request.getNumCarteNational())
                    .PhotoCarteNational(request.getPhotoCarteNational())
                    .PhotoPermis(request.getPhotoPermis())
                    .user(user)
                    .build();
            user.getRole().add(Role.CONDUCTEUR);

            conducteurService.save(conducteur);
        } else if ("PASSAGER".equals(request.getRole())) {
            Passager passager = Passager.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .telephone(request.getTelephone())
                    .nombrePersonnes(request.getNombrePersonnes())
                    .nombreTrajets(request.getNombreTrajets())
                    .user(user)
                    .build();
            user.getRole().add(Role.PASSAGER);

            passagerService.save(passager);
        }
        return jwtUtils.generateToken(user);
    }

    public String login(User user) {
        return jwtUtils.generateToken(user);
    }


}
