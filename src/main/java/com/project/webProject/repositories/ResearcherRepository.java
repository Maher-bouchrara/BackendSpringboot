package com.project.webProject.repositories;

import com.project.webProject.entities.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ResearcherRepository extends JpaRepository<Researcher, Long> {
    Optional<Researcher> findByEmail(String email);
    Optional<Researcher> findByOrcidId(String orcidId);
    List<Researcher> findByDomainId(Long domainId);
    List<Researcher> findByInstitution(String institution);
}

