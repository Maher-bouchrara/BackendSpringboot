package com.project.webProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "domains")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    // OneToMany vers Researcher
    @OneToMany(mappedBy = "domain", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"domain", "publications", "user"})
    @Builder.Default
    private Set<Researcher> researchers = new HashSet<>();

    // OneToMany vers Publication
    @OneToMany(mappedBy = "domain", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"domain", "researchers"})
    @Builder.Default
    private Set<Publication> publications = new HashSet<>();
}