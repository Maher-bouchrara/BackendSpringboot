package com.project.webProject.entities;

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
    @Builder.Default
    private Set<Researcher> researchers = new HashSet<>();

    // OneToMany vers Publication
    @OneToMany(mappedBy = "domain", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Publication> publications = new HashSet<>();
}