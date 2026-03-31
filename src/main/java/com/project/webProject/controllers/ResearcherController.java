package com.project.webProject.controllers;

import com.project.webProject.entities.Researcher;
import com.project.webProject.services.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/researchers")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResearcherController {

    @Autowired
    private ResearcherService researcherService;

    // CREATE - POST /api/researchers
    @PostMapping
    public ResponseEntity<Researcher> createResearcher(@RequestBody Researcher researcher) {
        Researcher createdResearcher = researcherService.createResearcher(researcher);
        return new ResponseEntity<>(createdResearcher, HttpStatus.CREATED);
    }

    // READ - GET /api/researchers
    @GetMapping
    public ResponseEntity<List<Researcher>> getAllResearchers() {
        List<Researcher> researchers = researcherService.getAllResearchers();
        return new ResponseEntity<>(researchers, HttpStatus.OK);
    }

    // READ - GET /api/researchers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Researcher> getResearcherById(@PathVariable Long id) {
        Optional<Researcher> researcher = researcherService.getResearcherById(id);
        return researcher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // READ - GET /api/researchers/email/{email}
    @GetMapping("/email/{email}")
    public ResponseEntity<Researcher> getResearcherByEmail(@PathVariable String email) {
        Optional<Researcher> researcher = researcherService.getResearcherByEmail(email);
        return researcher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // READ - GET /api/researchers/orcid/{orcidId}
    @GetMapping("/orcid/{orcidId}")
    public ResponseEntity<Researcher> getResearcherByOrcidId(@PathVariable String orcidId) {
        Optional<Researcher> researcher = researcherService.getResearcherByOrcidId(orcidId);
        return researcher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // READ - GET /api/researchers/domain/{domainId}
    @GetMapping("/domain/{domainId}")
    public ResponseEntity<List<Researcher>> getResearchersByDomain(@PathVariable Long domainId) {
        List<Researcher> researchers = researcherService.getResearchersByDomain(domainId);
        return new ResponseEntity<>(researchers, HttpStatus.OK);
    }

    // READ - GET /api/researchers/institution/{institution}
    @GetMapping("/institution/{institution}")
    public ResponseEntity<List<Researcher>> getResearchersByInstitution(@PathVariable String institution) {
        List<Researcher> researchers = researcherService.getResearchersByInstitution(institution);
        return new ResponseEntity<>(researchers, HttpStatus.OK);
    }

    // UPDATE - PUT /api/researchers/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Researcher> updateResearcher(@PathVariable Long id, @RequestBody Researcher researcherDetails) {
        Researcher updatedResearcher = researcherService.updateResearcher(id, researcherDetails);
        if (updatedResearcher != null) {
            return new ResponseEntity<>(updatedResearcher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - DELETE /api/researchers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResearcher(@PathVariable Long id) {
        researcherService.deleteResearcher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE - DELETE /api/researchers
    @DeleteMapping
    public ResponseEntity<Void> deleteAllResearchers() {
        researcherService.deleteAllResearchers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

