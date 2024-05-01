package com.techartistry.flexidorms.rental_management.domain.entities;

import com.techartistry.flexidorms.security_management.domain.entities.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Arrender extends User {
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isVerified;

    /**
     * -Info: UN "arrendador" puede tener MUCHAS "habitaciones"
     * -MappedBy: estará mapeando por el atributo "arrender" de la clase Room
     * -Cascade all & OrphanRemoval: cada vez que se elimine un objeto (un user) automáticamente se eliminan todos los demás datos asociados a este
     */
    @OneToMany(mappedBy = "arrender", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms = new HashSet<>();

    public Arrender() {}
}
