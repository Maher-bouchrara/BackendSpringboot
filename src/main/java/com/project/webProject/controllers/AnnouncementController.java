package com.project.webProject.controllers;

import com.project.webProject.entities.Announcement;
import com.project.webProject.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/announcements")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    // CREATE - POST /api/announcements
    @PostMapping
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        Announcement createdAnnouncement = announcementService.createAnnouncement(announcement);
        return new ResponseEntity<>(createdAnnouncement, HttpStatus.CREATED);
    }

    // READ - GET /api/announcements
    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    // READ - GET /api/announcements/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable Long id) {
        Optional<Announcement> announcement = announcementService.getAnnouncementById(id);
        return announcement.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // READ - GET /api/announcements/latest
    @GetMapping("/latest")
    public ResponseEntity<List<Announcement>> getAnnouncementsOrderedByDate() {
        List<Announcement> announcements = announcementService.getAnnouncementsOrderedByDate();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    // UPDATE - PUT /api/announcements/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable Long id, @RequestBody Announcement announcementDetails) {
        Announcement updatedAnnouncement = announcementService.updateAnnouncement(id, announcementDetails);
        if (updatedAnnouncement != null) {
            return new ResponseEntity<>(updatedAnnouncement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - DELETE /api/announcements/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE - DELETE /api/announcements
    @DeleteMapping
    public ResponseEntity<Void> deleteAllAnnouncements() {
        announcementService.deleteAllAnnouncements();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

