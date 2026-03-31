package com.project.webProject.services;

import com.project.webProject.entities.Researcher;
import com.project.webProject.repositories.ResearcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ResearcherService {

    @Autowired
    private ResearcherRepository researcherRepository;

    // CREATE
    public Researcher createResearcher(Researcher researcher) {
        return researcherRepository.save(researcher);
    }

    // READ - Get all researchers
    public List<Researcher> getAllResearchers() {
        return researcherRepository.findAll();
    }

    // READ - Get researcher by ID
    public Optional<Researcher> getResearcherById(Long id) {
        return researcherRepository.findById(id);
    }

    // READ - Get researcher by email
    public Optional<Researcher> getResearcherByEmail(String email) {
        return researcherRepository.findByEmail(email);
    }

    // READ - Get researcher by ORCID ID
    public Optional<Researcher> getResearcherByOrcidId(String orcidId) {
        return researcherRepository.findByOrcidId(orcidId);
    }

    // READ - Get researchers by domain
    public List<Researcher> getResearchersByDomain(Long domainId) {
        return researcherRepository.findByDomainId(domainId);
    }

    // READ - Get researchers by institution
    public List<Researcher> getResearchersByInstitution(String institution) {
        return researcherRepository.findByInstitution(institution);
    }

    // UPDATE
    public Researcher updateResearcher(Long id, Researcher researcherDetails) {
        Optional<Researcher> researcher = researcherRepository.findById(id);
        if (researcher.isPresent()) {
            Researcher existingResearcher = researcher.get();
            if (researcherDetails.getFirstName() != null) {
                existingResearcher.setFirstName(researcherDetails.getFirstName());
            }
            if (researcherDetails.getLastName() != null) {
                existingResearcher.setLastName(researcherDetails.getLastName());
            }
            if (researcherDetails.getEmail() != null) {
                existingResearcher.setEmail(researcherDetails.getEmail());
            }
            if (researcherDetails.getInstitution() != null) {
                existingResearcher.setInstitution(researcherDetails.getInstitution());
            }
            if (researcherDetails.getPosition() != null) {
                existingResearcher.setPosition(researcherDetails.getPosition());
            }
            if (researcherDetails.getBio() != null) {
                existingResearcher.setBio(researcherDetails.getBio());
            }
            if (researcherDetails.getPhotoUrl() != null) {
                existingResearcher.setPhotoUrl(researcherDetails.getPhotoUrl());
            }
            if (researcherDetails.getOrcidId() != null) {
                existingResearcher.setOrcidId(researcherDetails.getOrcidId());
            }
            if (researcherDetails.getDomain() != null) {
                existingResearcher.setDomain(researcherDetails.getDomain());
            }
            return researcherRepository.save(existingResearcher);
        }
        return null;
    }

    // DELETE
    public void deleteResearcher(Long id) {
        researcherRepository.deleteById(id);
    }

    // DELETE all
    public void deleteAllResearchers() {
        researcherRepository.deleteAll();
    }
}

