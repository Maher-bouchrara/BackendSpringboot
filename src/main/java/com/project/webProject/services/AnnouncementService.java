package com.project.webProject.services;

import com.project.webProject.entities.Announcement;
import com.project.webProject.repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    // CREATE
    public Announcement createAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    // READ - Get all announcements
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    // READ - Get announcement by ID
    public Optional<Announcement> getAnnouncementById(Long id) {
        return announcementRepository.findById(id);
    }

    // READ - Get announcements ordered by creation date
    public List<Announcement> getAnnouncementsOrderedByDate() {
        return announcementRepository.findByOrderByCreatedAtDesc();
    }

    // UPDATE
    public Announcement updateAnnouncement(Long id, Announcement announcementDetails) {
        Optional<Announcement> announcement = announcementRepository.findById(id);
        if (announcement.isPresent()) {
            Announcement existingAnnouncement = announcement.get();
            if (announcementDetails.getTitle() != null) {
                existingAnnouncement.setTitle(announcementDetails.getTitle());
            }
            if (announcementDetails.getContent() != null) {
                existingAnnouncement.setContent(announcementDetails.getContent());
            }
            return announcementRepository.save(existingAnnouncement);
        }
        return null;
    }

    // DELETE
    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }

    // DELETE all
    public void deleteAllAnnouncements() {
        announcementRepository.deleteAll();
    }
}

