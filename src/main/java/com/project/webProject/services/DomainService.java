package com.project.webProject.services;

import com.project.webProject.entities.Domain;
import com.project.webProject.repositories.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DomainService {

    @Autowired
    private DomainRepository domainRepository;

    // CREATE
    public Domain createDomain(Domain domain) {
        return domainRepository.save(domain);
    }

    // READ - Get all domains
    public List<Domain> getAllDomains() {
        return domainRepository.findAll();
    }

    // READ - Get domain by ID
    public Optional<Domain> getDomainById(Long id) {
        return domainRepository.findById(id);
    }

    // READ - Get domain by name
    public Optional<Domain> getDomainByName(String name) {
        return domainRepository.findByName(name);
    }

    // UPDATE
    public Domain updateDomain(Long id, Domain domainDetails) {
        Optional<Domain> domain = domainRepository.findById(id);
        if (domain.isPresent()) {
            Domain existingDomain = domain.get();
            if (domainDetails.getName() != null) {
                existingDomain.setName(domainDetails.getName());
            }
            if (domainDetails.getDescription() != null) {
                existingDomain.setDescription(domainDetails.getDescription());
            }
            return domainRepository.save(existingDomain);
        }
        return null;
    }

    // DELETE
    public void deleteDomain(Long id) {
        domainRepository.deleteById(id);
    }

    // DELETE all
    public void deleteAllDomains() {
        domainRepository.deleteAll();
    }
}

