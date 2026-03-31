package com.project.webProject.services;

import com.project.webProject.entities.Publication;
import com.project.webProject.enums.PublicationStatus;
import com.project.webProject.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    // CREATE
    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    // READ - Get all publications
    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    // READ - Get publication by ID
    public Optional<Publication> getPublicationById(Long id) {
        return publicationRepository.findById(id);
    }

    // READ - Get publications by status
    public List<Publication> getPublicationsByStatus(PublicationStatus status) {
        return publicationRepository.findByStatus(status);
    }

    // READ - Get publications by researcher
    public List<Publication> getPublicationsByResearcher(Long researcherId) {
        return publicationRepository.findByResearchersId(researcherId);
    }

    // READ - Get publication by DOI
    public Optional<Publication> getPublicationByDoi(String doi) {
        return publicationRepository.findByDoi(doi);
    }

    // READ - Search publications by title
    public List<Publication> searchPublicationsByTitle(String title) {
        return publicationRepository.findByTitleContainingIgnoreCase(title);
    }

    // UPDATE
    public Publication updatePublication(Long id, Publication publicationDetails) {
        Optional<Publication> publication = publicationRepository.findById(id);
        if (publication.isPresent()) {
            Publication existingPublication = publication.get();
            if (publicationDetails.getTitle() != null) {
                existingPublication.setTitle(publicationDetails.getTitle());
            }
            if (publicationDetails.getAbstractText() != null) {
                existingPublication.setAbstractText(publicationDetails.getAbstractText());
            }
            if (publicationDetails.getKeywords() != null) {
                existingPublication.setKeywords(publicationDetails.getKeywords());
            }
            if (publicationDetails.getDoi() != null) {
                existingPublication.setDoi(publicationDetails.getDoi());
            }
            if (publicationDetails.getPdfUrl() != null) {
                existingPublication.setPdfUrl(publicationDetails.getPdfUrl());
            }
            if (publicationDetails.getJournal() != null) {
                existingPublication.setJournal(publicationDetails.getJournal());
            }
            if (publicationDetails.getPublicationDate() != null) {
                existingPublication.setPublicationDate(publicationDetails.getPublicationDate());
            }
            if (publicationDetails.getStatus() != null) {
                existingPublication.setStatus(publicationDetails.getStatus());
            }
            return publicationRepository.save(existingPublication);
        }
        return null;
    }

    // DELETE
    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    // DELETE all
    public void deleteAllPublications() {
        publicationRepository.deleteAll();
    }
}

