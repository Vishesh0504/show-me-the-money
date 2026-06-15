package com.vishesh.core;

import com.vishesh.model.Role;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "StoredUser.findByEmail",
                query = "SELECT u FROM StoredUser u WHERE u.email = :email"
        )
})
public class StoredUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name="email",nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();

    public static StoredUser of(String email, String passwordHash, Set<Role> roles) {
        StoredUser user = new StoredUser();
        user.email = email;
        user.passwordHash = passwordHash;
        user.roles = new HashSet<>(roles);
        return user;
    }
}
