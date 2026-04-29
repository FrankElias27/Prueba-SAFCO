package com.hexagonal.demo.utils.Keycloak.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hexagonal.demo.infraestructure.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "users")
@NamedQuery(name = UserConstants.FIND_USER_BY_EMAIL,
        query = "SELECT u FROM User u WHERE u.email = :email"
)
public class User  {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String dni;



}
