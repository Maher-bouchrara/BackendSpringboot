package com.project.webProject.entities;

import com.project.webProject.enums.PublicationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String abstractText;

    @Column(columnDefinition = "TEXT")
    private String keywords;

    @Column(length = 100)
    private String doi;

    @Column(length = 255)
    private String pdfUrl;

    @Column(length = 255)
    private String journal;

    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private PublicationStatus status = PublicationStatus.DRAFT;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // ManyToOne vers Domain
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id")
    private Domain domain;

    // ManyToMany vers Researcher (côté propriétaire)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "publication_researchers",
            joinColumns        = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "researcher_id")
    )
    @Builder.Default
    private Set<Researcher> researchers = new HashSet<>();
}