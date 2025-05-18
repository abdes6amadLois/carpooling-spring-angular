package org.example.covoiturage.security.entities;

public enum Role {
    USER,        // par défaut à l'inscription
    PASSAGER,    // quand il demande un trajet
    CONDUCTEUR   // quand il crée un trajet
}

