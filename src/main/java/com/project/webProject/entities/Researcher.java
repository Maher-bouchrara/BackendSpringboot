package com.project.webProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "researchers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Researcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 100)
    private String institution;

    @Column(length = 100)
    private String position;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(length = 255)
    private String photoUrl;

    @Column(length = 100)
    private String orcidId;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // ManyToOne vers Domain (un chercheur appartient à un domaine principal)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id")
    @JsonIgnoreProperties({"researchers", "publications"})
    private Domain domain;

    // OneToOne optionnel vers User (un chercheur peut avoir un compte)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    @JsonIgnoreProperties({"researcher"})
    private User user;

    // ManyToMany vers Publication (côté inverse)
    @ManyToMany(mappedBy = "researchers")
    @JsonIgnoreProperties({"domain", "researchers"})
    @Builder.Default
    private Set<Publication> publications = new HashSet<>();
}