package com.project.webProject.controllers;

import com.project.webProject.entities.Publication;
import com.project.webProject.enums.PublicationStatus;
import com.project.webProject.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publications")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    // CREATE - POST /api/publications
    @PostMapping
    public ResponseEntity<Publication> createPublication(@RequestBody Publication publication) {
        Publication createdPublication = publicationService.createPublication(publication);
        return new ResponseEntity<>(createdPublication, HttpStatus.CREATED);
    }

    // READ - GET /api/publications
    @GetMapping
    public ResponseEntity<List<Publication>> getAllPublications() {
        List<Publication> publications = publicationService.getAllPublications();
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    // READ - GET /api/publications/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublicationById(@PathVariable Long id) {
        Optional<Publication> publication = publicationService.getPublicationById(id);
        return publication.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // READ - GET /api/publications/status/{status}
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Publication>> getPublicationsByStatus(@PathVariable PublicationStatus status) {
        List<Publication> publications = publicationService.getPublicationsByStatus(status);
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    // READ - GET /api/publications/researcher/{researcherId}
    @GetMapping("/researcher/{researcherId}")
    public ResponseEntity<List<Publication>> getPublicationsByResearcher(@PathVariable Long researcherId) {
        List<Publication> publications = publicationService.getPublicationsByResearcher(researcherId);
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    // READ - GET /api/publications/doi/{doi}
    @GetMapping("/doi/{doi}")
    public ResponseEntity<Publication> getPublicationByDoi(@PathVariable String doi) {
        Optional<Publication> publication = publicationService.getPublicationByDoi(doi);
        return publication.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // READ - GET /api/publications/search?title=keyword
    @GetMapping("/search")
    public ResponseEntity<List<Publication>> searchPublicationsByTitle(@RequestParam String title) {
        List<Publication> publications = publicationService.searchPublicationsByTitle(title);
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    // UPDATE - PUT /api/publications/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable Long id, @RequestBody Publication publicationDetails) {
        Publication updatedPublication = publicationService.updatePublication(id, publicationDetails);
        if (updatedPublication != null) {
            return new ResponseEntity<>(updatedPublication, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - DELETE /api/publications/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        publicationService.deletePublication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE - DELETE /api/publications
    @DeleteMapping
    public ResponseEntity<Void> deleteAllPublications() {
        publicationService.deleteAllPublications();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

