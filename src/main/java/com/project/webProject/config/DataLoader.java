package com.project.webProject.config;

import com.project.webProject.entities.*;
import com.project.webProject.enums.PublicationStatus;
import com.project.webProject.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            DomainRepository domainRepository,
            ResearcherRepository researcherRepository,
            PublicationRepository publicationRepository,
            AnnouncementRepository announcementRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            // Vérifier si les données sont déjà chargées
            if (roleRepository.count() > 0) {
                System.out.println("Les données mock sont déjà chargées, ignorant la chargement.");
                return;
            }

            System.out.println("=== Chargement des données mock ===");

            // 1. Créer les Roles
            Role adminRole = Role.builder()
                    .name(Role.RoleName.ROLE_ADMIN)
                    .build();
            Role moderatorRole = Role.builder()
                    .name(Role.RoleName.ROLE_MODERATOR)
                    .build();
            Role userRole = Role.builder()
                    .name(Role.RoleName.ROLE_USER)
                    .build();

            roleRepository.save(adminRole);
            roleRepository.save(moderatorRole);
            roleRepository.save(userRole);
            System.out.println("✓ 3 Roles créés");

            // 2. Créer les Users
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            User adminUser = User.builder()
                    .firstName("Admin")
                    .lastName("System")
                    .email("admin@webproject.com")
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .enabled(true)
                    .roles(adminRoles)
                    .build();

            User user1 = User.builder()
                    .firstName("Jean")
                    .lastName("Dupont")
                    .email("jean.dupont@example.com")
                    .username("jeandupont")
                    .password(passwordEncoder.encode("password123"))
                    .enabled(true)
                    .roles(userRoles)
                    .build();

            User user2 = User.builder()
                    .firstName("Marie")
                    .lastName("Martin")
                    .email("marie.martin@example.com")
                    .username("mariemartin")
                    .password(passwordEncoder.encode("password123"))
                    .enabled(true)
                    .roles(userRoles)
                    .build();

            User user3 = User.builder()
                    .firstName("Pierre")
                    .lastName("Bernard")
                    .email("pierre.bernard@example.com")
                    .username("pierrebernard")
                    .password(passwordEncoder.encode("password123"))
                    .enabled(true)
                    .roles(userRoles)
                    .build();

            userRepository.save(adminUser);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            System.out.println("✓ 4 Users créés");

            // 3. Créer les Domains
            Domain computerScience = Domain.builder()
                    .name("Informatique")
                    .description("Tous les domaines de l'informatique: AI, blockchain, développement logiciel, etc.")
                    .build();

            Domain biology = Domain.builder()
                    .name("Biologie")
                    .description("Recherche en biologie moléculaire, génétique et biotechnologie")
                    .build();

            Domain physics = Domain.builder()
                    .name("Physique")
                    .description("Physique théorique, physique appliquée et astrophysique")
                    .build();

            domainRepository.save(computerScience);
            domainRepository.save(biology);
            domainRepository.save(physics);
            System.out.println("✓ 3 Domains créés");

            // 4. Créer les Researchers
            Researcher researcher1 = Researcher.builder()
                    .firstName("Dr. Ahmed")
                    .lastName("Hassan")
                    .email("ahmed.hassan@university.edu")
                    .institution("Université de Tunis")
                    .position("Professeur en Informatique")
                    .bio("Spécialisé dans l'intelligence artificielle et l'apprentissage automatique")
                    .orcidId("0000-0001-2345-6789")
                    .domain(computerScience)
                    .user(user1)
                    .build();

            Researcher researcher2 = Researcher.builder()
                    .firstName("Dr. Fatima")
                    .lastName("Zahra")
                    .email("fatima.zahra@university.edu")
                    .institution("Université de Sfax")
                    .position("Chercheur Postdoctoral")
                    .bio("Recherche en biologie moléculaire et génétique")
                    .orcidId("0000-0002-3456-7890")
                    .domain(biology)
                    .user(user2)
                    .build();

            Researcher researcher3 = Researcher.builder()
                    .firstName("Dr. Mohamed")
                    .lastName("Slimani")
                    .email("mohamed.slimani@university.edu")
                    .institution("Université de Sousse")
                    .position("Maître de Conférences")
                    .bio("Chercheur en physique quantique")
                    .orcidId("0000-0003-4567-8901")
                    .domain(physics)
                    .user(user3)
                    .build();

            researcherRepository.save(researcher1);
            researcherRepository.save(researcher2);
            researcherRepository.save(researcher3);
            System.out.println("✓ 3 Researchers créés");

            // 5. Créer les Publications
            Set<Researcher> researchers1 = new HashSet<>();
            researchers1.add(researcher1);

            Set<Researcher> researchers2 = new HashSet<>();
            researchers2.add(researcher2);
            researchers2.add(researcher3);

            Publication pub1 = Publication.builder()
                    .title("Deep Learning Applications in Healthcare")
                    .abstractText("Ce document explore les applications du deep learning dans le domaine médical...")
                    .keywords("deep learning, machine learning, healthcare, neural networks")
                    .doi("10.1234/example.2024.001")
                    .journal("International Journal of AI Research")
                    .publicationDate(LocalDate.of(2024, 1, 15))
                    .status(PublicationStatus.PUBLISHED)
                    .domain(computerScience)
                    .researchers(researchers1)
                    .build();

            Publication pub2 = Publication.builder()
                    .title("Genetic Engineering and CRISPR Technology")
                    .abstractText("Une revue complète des dernières avancées en édition génétique...")
                    .keywords("CRISPR, genetic engineering, biotechnology")
                    .doi("10.1234/example.2024.002")
                    .journal("Nature Biotechnology")
                    .publicationDate(LocalDate.of(2024, 2, 20))
                    .status(PublicationStatus.PUBLISHED)
                    .domain(biology)
                    .researchers(researchers2)
                    .build();

            Publication pub3 = Publication.builder()
                    .title("Quantum Computing Fundamentals")
                    .abstractText("Introduction aux principes fondamentaux de l'informatique quantique...")
                    .keywords("quantum computing, quantum mechanics, algorithms")
                    .journal("IEEE Quantum Engineering")
                    .publicationDate(LocalDate.of(2024, 3, 10))
                    .status(PublicationStatus.DRAFT)
                    .domain(computerScience)
                    .researchers(researchers1)
                    .build();

            Publication pub4 = Publication.builder()
                    .title("Renewable Energy Sources Analysis")
                    .abstractText("Étude comparative de différentes sources d'énergie renouvelables...")
                    .keywords("renewable energy, solar, wind, sustainability")
                    .status(PublicationStatus.DRAFT)
                    .domain(physics)
                    .researchers(researchers2)
                    .build();

            publicationRepository.save(pub1);
            publicationRepository.save(pub2);
            publicationRepository.save(pub3);
            publicationRepository.save(pub4);
            System.out.println("✓ 4 Publications créées");

            // 6. Créer les Announcements
            Announcement ann1 = Announcement.builder()
                    .title("Conférence de Recherche 2024")
                    .content("Nous avons le plaisir d'annoncer la 5ème conférence internationale de recherche qui se tiendra en juin 2024.")
                    .pinned(true)
                    .createdBy(adminUser)
                    .build();

            Announcement ann2 = Announcement.builder()
                    .title("Nouvelles directives de publication")
                    .content("Les directives de publication ont été mises à jour. Veuillez consulter le nouveau format requis.")
                    .pinned(true)
                    .createdBy(adminUser)
                    .build();

            Announcement ann3 = Announcement.builder()
                    .title("Maintenance du système")
                    .content("Maintenance programmée le 15 avril de 22h à 23h. Le site sera temporairement indisponible.")
                    .pinned(false)
                    .createdBy(adminUser)
                    .build();

            announcementRepository.save(ann1);
            announcementRepository.save(ann2);
            announcementRepository.save(ann3);
            System.out.println("✓ 3 Announcements créés");

            System.out.println("=== Chargement des données mock terminé avec succès ===");
            System.out.println("Identifiants de connexion:");
            System.out.println("  Admin: admin / admin123");
            System.out.println("  User: jeandupont / password123");
            System.out.println("=".repeat(50));
        };
    }
}


