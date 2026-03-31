package com.project.webProject.repositories;

import com.project.webProject.entities.Publication;
import com.project.webProject.enums.PublicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByStatus(PublicationStatus status);
    List<Publication> findByResearchersId(Long researcherId);
    Optional<Publication> findByDoi(String doi);
    List<Publication> findByTitleContainingIgnoreCase(String title);
}

