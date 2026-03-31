package com.project.webProject.controllers;

import com.project.webProject.entities.Domain;
import com.project.webProject.services.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/domains")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DomainController {

    @Autowired
    private DomainService domainService;

    // CREATE - POST /api/domains
    @PostMapping
    public ResponseEntity<Domain> createDomain(@RequestBody Domain domain) {
        Domain createdDomain = domainService.createDomain(domain);
        return new ResponseEntity<>(createdDomain, HttpStatus.CREATED);
    }

    // READ - GET /api/domains
    @GetMapping
    public ResponseEntity<List<Domain>> getAllDomains() {
        List<Domain> domains = domainService.getAllDomains();
        return new ResponseEntity<>(domains, HttpStatus.OK);
    }

    // READ - GET /api/domains/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Domain> getDomainById(@PathVariable Long id) {
        Optional<Domain> domain = domainService.getDomainById(id);
        return domain.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // READ - GET /api/domains/name/{name}
    @GetMapping("/name/{name}")
    public ResponseEntity<Domain> getDomainByName(@PathVariable String name) {
        Optional<Domain> domain = domainService.getDomainByName(name);
        return domain.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE - PUT /api/domains/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Domain> updateDomain(@PathVariable Long id, @RequestBody Domain domainDetails) {
        Domain updatedDomain = domainService.updateDomain(id, domainDetails);
        if (updatedDomain != null) {
            return new ResponseEntity<>(updatedDomain, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - DELETE /api/domains/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomain(@PathVariable Long id) {
        domainService.deleteDomain(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE - DELETE /api/domains
    @DeleteMapping
    public ResponseEntity<Void> deleteAllDomains() {
        domainService.deleteAllDomains();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

