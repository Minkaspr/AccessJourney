package com.mk.accessjourneybe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Email(message = "Por favor, introduzca una dirección de correo electrónico válida.")
    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    private String password;

    @NotBlank(message = "El nombre no puede estar vacío.")
    private String firstName;

    @NotBlank(message = "El apellido no puede estar vacío.")
    private String lastName;

    @NotBlank(message = "El tipo de documento de identidad no puede estar vacío.")
    private String identityDocumentType;

    @NotBlank(message = "El número de documento de identidad no puede estar vacío.")
    private String identityDocumentNumber;

    @Past(message = "La fecha de nacimiento no puede ser en el futuro.")
    private LocalDate dateOfBirth;

    @CreationTimestamp  // Establece automáticamente la fecha y hora de creación
    private LocalDateTime registrationDate;

    private Boolean isActive;

    @NotNull(message = "El rol de usuario no puede ser nulo.")
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "role_id",
            referencedColumnName = "roleId"
    )
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devuelve las autoridades (roles) del usuario
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Devuelve true si la cuenta no ha expirado
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Devuelve true si la cuenta no está bloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Devuelve true si las credenciales no han expirado
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Devuelve true si el usuario está habilitado
        return true;
    }
}
