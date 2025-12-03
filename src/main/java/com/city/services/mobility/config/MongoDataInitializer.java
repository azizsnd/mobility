package com.city.services.mobility.config;

import com.city.services.mobility.model.*;
import com.city.services.mobility.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class MongoDataInitializer {

    private final TransportRepository transportRepository;
    private final ScheduleRepository scheduleRepository;
    private final TrafficRepository trafficRepository;

    @PostConstruct
    public void initData() {
        if (transportRepository.count() == 0) {
            System.out.println("📦 Initialisation des données Tunisie...");

            transportRepository.deleteAll();
            scheduleRepository.deleteAll();
            trafficRepository.deleteAll();

            initTransportLinesTunisia();
            initSchedulesTunisia();
            initTrafficStatusTunisia();

            System.out.println("✅ Données tunisiennes insérées avec succès !");
            printStatistics();
        }
    }

    private void initTransportLinesTunisia() {
        // ============ MÉTRO LÉGER DE TUNIS (Métro) ============
        TransportLine metroTGM = new TransportLine(
                null, "TGM", "TGM - Tunis-Goulette-Marsa", TransportType.TRAIN,
                Arrays.asList("Tunis Marine", "Le Bac", "Khereddine", "Goulette", "Goulette Casino",
                        "Goulette Neuve", "Gammarth", "Sidi Bou Saïd", "Marsa Plage", "Marsa Corniche"),
                "04:30-23:30", 15, true, "SNCFT", "#0074D9"
        );

        TransportLine metroL1 = new TransportLine(
                null, "ML1", "Métro Léger Ligne 1", TransportType.METRO,
                Arrays.asList("Place de Barcelone", "Bab Alioua", "Mohamed V", "Farhat Hached",
                        "Habib Thameur", "10 Décembre", "Ibn Khaldoun", "13 Août"),
                "05:00-00:00", 6, true, "TRANSTU", "#FF4136"
        );

        TransportLine metroL2 = new TransportLine(
                null, "ML2", "Métro Léger Ligne 2", TransportType.METRO,
                Arrays.asList("Ariana", "7 Novembre", "Khaznadar", "Cité El Khadra",
                        "Jeanne d'Arc", "Place de Barcelone", "Bab Laassal", "El Omrane"),
                "05:15-23:45", 8, true, "TRANSTU", "#2ECC40"
        );

        TransportLine metroL3 = new TransportLine(
                null, "ML3", "Métro Léger Ligne 3", TransportType.METRO,
                Arrays.asList("Ben Arous", "Mohamed Ali", "El Hraïria", "Cité Olympique",
                        "Bab Saadoun", "Bab El Khadra", "Bab Souika", "Place de Barcelone"),
                "05:30-23:30", 10, true, "TRANSTU", "#FF851B"
        );

        TransportLine metroL4 = new TransportLine(
                null, "ML4", "Métro Léger Ligne 4", TransportType.METRO,
                Arrays.asList("Den Den", "Mongi Slim", "Cité Jardins", "El Menzah",
                        "Kram", "Salammbô", "La Goulette"),
                "06:00-22:30", 12, true, "TRANSTU", "#B10DC9"
        );

        // ============ BUS DE TUNIS ============
        TransportLine bus28 = new TransportLine(
                null, "B28", "Bus 28 - Bab Saadoun à La Marsa", TransportType.BUS,
                Arrays.asList("Bab Saadoun", "Bab Bhar", "Bab Souika", "Port de Tunis",
                        "Carthage", "Sidi Bou Saïd", "La Marsa"),
                "05:45-21:30", 20, true, "TRANSTU", "#FFDC00"
        );

        TransportLine bus35 = new TransportLine(
                null, "B35", "Bus 35 - Tunis à Hammam Lif", TransportType.BUS,
                Arrays.asList("Bab El Khadra", "Habib Thameur", "El Mourouj", "Ezzahra",
                        "Rades", "Hammam Lif Centre"),
                "05:30-22:00", 25, true, "TRANSTU", "#39CCCC"
        );

        TransportLine bus50 = new TransportLine(
                null, "B50", "Bus 50 - Ariana à Menzah", TransportType.BUS,
                Arrays.asList("Ariana", "Ennasr", "El Menzah 5", "El Menzah 6",
                        "El Menzah 9", "Cité Olympique"),
                "06:00-22:00", 15, true, "TRANSTU", "#01FF70"
        );

        TransportLine bus63 = new TransportLine(
                null, "B63", "Bus 63 - Tunis à Ben Arous", TransportType.BUS,
                Arrays.asList("Bab Alioua", "El Omrane", "El Hraïria", "Tadhamen",
                        "Ibn Sina", "Ben Arous"),
                "05:45-21:45", 18, true, "TRANSTU", "#F012BE"
        );

        TransportLine bus202 = new TransportLine(
                null, "B202", "Bus 202 - Aéroport à Centre Ville", TransportType.BUS,
                Arrays.asList("Aéroport Tunis-Carthage", "El Aouina", "Le Bardo",
                        "Bab Saadoun", "Habib Bourguiba"),
                "04:00-23:00", 30, true, "Tunisair Bus", "#7FDBFF"
        );

        // ============ TRAINS BANLIEUE ============
        TransportLine trainBanlieue = new TransportLine(
                null, "TB", "Train Banlieue Nord", TransportType.TRAIN,
                Arrays.asList("Tunis", "Le Bardo", "La Manouba", "Mornag", "Mrezga",
                        "Bir El Bey", "Ezzahra", "Rades"),
                "05:00-22:30", 30, true, "SNCFT", "#3D9970"
        );

        // ============ LIGNES LONGUE DISTANCE ============
        TransportLine trainSahloul = new TransportLine(
                null, "TS", "Train Tunis-Sahloul", TransportType.TRAIN,
                Arrays.asList("Tunis", "Mohamedia", "Fouchana", "Mhamdia", "M'Saken", "Sahloul"),
                "06:00-21:00", 60, true, "SNCFT", "#85144b"
        );

        TransportLine trainBizerte = new TransportLine(
                null, "TBIZ", "Train Tunis-Bizerte", TransportType.TRAIN,
                Arrays.asList("Tunis", "Ariana", "Kalâat el-Andalous", "Mateur", "Tinja", "Bizerte"),
                "06:30-20:30", 90, true, "SNCFT", "#FF69B4"
        );

        // ============ NAVETTE UNIVERSITAIRE ============
        TransportLine navetteINSAT = new TransportLine(
                null, "NINSAT", "Navette INSAT", TransportType.BUS,
                Arrays.asList("INSAT", "El Manar 1", "El Manar 2", "Cité Olympique", "El Menzah 6"),
                "07:30-18:00", 45, true, "Universitair", "#001f3f"
        );

        TransportLine navetteENIT = new TransportLine(
                null, "NENIT", "Navette ENIT", TransportType.BUS,
                Arrays.asList("ENIT", "El Manar", "Cité El Khadra", "El Omrane", "Bab Saadoun"),
                "08:00-17:30", 60, true, "Universitair", "#AAAAAA"
        );

        // ============ LIGNE SPÉCIALE TOURISTIQUE ============
        TransportLine busTouristique = new TransportLine(
                null, "BT", "Bus Touristique TGM", TransportType.BUS,
                Arrays.asList("Tunis Medina", "Carthage", "Sidi Bou Saïd", "La Marsa", "Gammarth"),
                "09:00-19:00", 120, true, "Tourisme", "#FFD700"
        );

        // Ligne en maintenance
        TransportLine metroMaintenance = new TransportLine(
                null, "MLM", "Métro Ligne 5 (En travaux)", TransportType.METRO,
                Arrays.asList("Ezzouhour", "Tadhamen", "Intilaka"),
                "00:00-00:00", 0, false, "TRANSTU", "#555555"
        );

        transportRepository.saveAll(Arrays.asList(
                // Métro
                metroTGM, metroL1, metroL2, metroL3, metroL4,
                // Bus
                bus28, bus35, bus50, bus63, bus202,
                // Trains
                trainBanlieue, trainSahloul, trainBizerte,
                // Navettes
                navetteINSAT, navetteENIT,
                // Spéciales
                busTouristique, metroMaintenance
        ));
    }

    private void initSchedulesTunisia() {
        LocalDateTime now = LocalDateTime.now();

        // ============ HORAIRES TGM ============
        // Train TGM - Tunis Marine → Marsa
        scheduleRepository.save(new Schedule(
                null, "TGM", "Tunis Marine",
                now.with(LocalTime.of(8, 0)),
                now.with(LocalTime.of(8, 35)),
                0, Schedule.Status.ON_TIME
        ));

        scheduleRepository.save(new Schedule(
                null, "TGM", "Tunis Marine",
                now.with(LocalTime.of(8, 15)),
                now.with(LocalTime.of(8, 50)),
                5, Schedule.Status.DELAYED
        ));

        scheduleRepository.save(new Schedule(
                null, "TGM", "Goulette",
                now.with(LocalTime.of(8, 20)),
                now.with(LocalTime.of(8, 40)),
                0, Schedule.Status.ON_TIME
        ));

        // ============ HORAIRES MÉTRO L1 ============
        scheduleRepository.save(new Schedule(
                null, "ML1", "Place de Barcelone",
                now.with(LocalTime.of(8, 10)),
                now.with(LocalTime.of(8, 30)),
                2, Schedule.Status.DELAYED
        ));

        scheduleRepository.save(new Schedule(
                null, "ML1", "Bab Alioua",
                now.with(LocalTime.of(8, 25)),
                now.with(LocalTime.of(8, 45)),
                0, Schedule.Status.ON_TIME
        ));

        scheduleRepository.save(new Schedule(
                null, "ML1", "Farhat Hached",
                now.with(LocalTime.of(8, 40)),
                now.plusDays(1).with(LocalTime.of(8, 40)),
                -1, Schedule.Status.CANCELLED  // Annulé pour travaux
        ));

        // ============ HORAIRES BUS 28 ============
        scheduleRepository.save(new Schedule(
                null, "B28", "Bab Saadoun",
                now.with(LocalTime.of(8, 30)),
                now.with(LocalTime.of(9, 15)),
                10, Schedule.Status.DELAYED
        ));

        scheduleRepository.save(new Schedule(
                null, "B28", "Carthage",
                now.with(LocalTime.of(9, 0)),
                now.with(LocalTime.of(9, 20)),
                0, Schedule.Status.ON_TIME
        ));

        // ============ HORAIRES BUS 35 ============
        scheduleRepository.save(new Schedule(
                null, "B35", "Bab El Khadra",
                now.with(LocalTime.of(8, 45)),
                now.with(LocalTime.of(9, 30)),
                0, Schedule.Status.ON_TIME
        ));

        scheduleRepository.save(new Schedule(
                null, "B35", "Hammam Lif Centre",
                now.with(LocalTime.of(9, 15)),
                now.with(LocalTime.of(9, 45)),
                15, Schedule.Status.DELAYED
        ));

        // ============ HORAIRES BUS AÉROPORT 202 ============
        scheduleRepository.save(new Schedule(
                null, "B202", "Aéroport Tunis-Carthage",
                now.with(LocalTime.of(9, 0)),
                now.with(LocalTime.of(9, 40)),
                0, Schedule.Status.ON_TIME
        ));

        scheduleRepository.save(new Schedule(
                null, "B202", "Habib Bourguiba",
                now.with(LocalTime.of(10, 0)),
                now.with(LocalTime.of(10, 40)),
                0, Schedule.Status.ON_TIME
        ));

        // ============ HORAIRES TRAIN BANLIEUE ============
        scheduleRepository.save(new Schedule(
                null, "TB", "Tunis",
                now.with(LocalTime.of(8, 0)),
                now.with(LocalTime.of(8, 45)),
                0, Schedule.Status.ON_TIME
        ));

        scheduleRepository.save(new Schedule(
                null, "TB", "Le Bardo",
                now.with(LocalTime.of(8, 15)),
                now.with(LocalTime.of(8, 30)),
                0, Schedule.Status.ON_TIME
        ));

        scheduleRepository.save(new Schedule(
                null, "TB", "Rades",
                now.with(LocalTime.of(8, 30)),
                now.with(LocalTime.of(8, 50)),
                20, Schedule.Status.DELAYED  // Important retard
        ));

        // ============ NAVETTE UNIVERSITAIRE ============
        scheduleRepository.save(new Schedule(
                null, "NINSAT", "INSAT",
                now.with(LocalTime.of(8, 0)),
                now.with(LocalTime.of(8, 15)),
                0, Schedule.Status.ON_TIME
        ));

        scheduleRepository.save(new Schedule(
                null, "NINSAT", "Cité Olympique",
                now.with(LocalTime.of(8, 30)),
                now.with(LocalTime.of(8, 40)),
                5, Schedule.Status.DELAYED
        ));

        // ============ PLUSIEURS HORAIRES POUR TESTS ============
        // Création de plusieurs horaires pour la même ligne
        for (int i = 0; i < 10; i++) {
            scheduleRepository.save(new Schedule(
                    null, "ML2", "Ariana",
                    now.with(LocalTime.of(8, 0)).plusMinutes(i * 8),
                    now.with(LocalTime.of(8, 20)).plusMinutes(i * 8),
                    i % 3 == 0 ? 5 : 0,  // 1/3 des trains en retard
                    i % 3 == 0 ? Schedule.Status.DELAYED : Schedule.Status.ON_TIME
            ));
        }

        System.out.println("📅 " + scheduleRepository.count() + " horaires créés");
    }

    private void initTrafficStatusTunisia() {
        LocalDateTime now = LocalDateTime.now();

        // ============ ÉTATS DU TRAFIC ============

        // Circulation fluide
        trafficRepository.save(new TrafficStatus(
                null, "TGM", TrafficStatus.TrafficLevel.FLUID,
                "Circulation normale sur toute la ligne",
                now.minusHours(1),
                now.plusHours(3)
        ));

        // Bouchon sur Avenue Habib Bourguiba
        trafficRepository.save(new TrafficStatus(
                null, "B28", TrafficStatus.TrafficLevel.CONGESTED,
                "Embouteillage important Avenue Habib Bourguiba - Manifestation étudiante",
                now.minusMinutes(30),
                now.plusHours(2)
        ));

        // Travaux sur ligne métro
        trafficRepository.save(new TrafficStatus(
                null, "ML1", TrafficStatus.TrafficLevel.BLOCKED,
                "Ligne interrompue entre Bab Alioua et Farhat Hached - Travaux d'entretien",
                now.minusDays(1),
                now.plusDays(2)
        ));

        // Accident route La Marsa
        trafficRepository.save(new TrafficStatus(
                null, "B28", TrafficStatus.TrafficLevel.CONGESTED,
                "Accident route de La Marsa - Circulation alternée",
                now.minusMinutes(45),
                now.plusHours(1)
        ));

        // Grève des bus
        trafficRepository.save(new TrafficStatus(
                null, "B35", TrafficStatus.TrafficLevel.BLOCKED,
                "Ligne suspendue - Grève du personnel TRANSTU",
                now.minusHours(3),
                now.plusHours(24)
        ));

        // Circulation dense heures de pointe
        trafficRepository.save(new TrafficStatus(
                null, "ML2", TrafficStatus.TrafficLevel.DENSE,
                "Affluence élevée heures de pointe - Prévoir 10 minutes supplémentaires",
                now,
                now.plusHours(2)
        ));

        // Problème technique train
        trafficRepository.save(new TrafficStatus(
                null, "TB", TrafficStatus.TrafficLevel.CONGESTED,
                "Ralentissement - Problème technique sur une rame",
                now.minusMinutes(20),
                now.plusMinutes(40)
        ));

        // Événement spécial Carthage
        trafficRepository.save(new TrafficStatus(
                null, "BT", TrafficStatus.TrafficLevel.DENSE,
                "Fort trafic touristique site Carthage - Festival international",
                now,
                now.plusHours(6)
        ));

        // Conditions météo
        trafficRepository.save(new TrafficStatus(
                null, "B202", TrafficStatus.TrafficLevel.DENSE,
                "Trafic ralenti - Pluies importantes sur Tunis",
                now.minusHours(1),
                now.plusHours(3)
        ));

        // Match de football
        trafficRepository.save(new TrafficStatus(
                null, "ML4", TrafficStatus.TrafficLevel.CONGESTED,
                "Affluence exceptionnelle - Match ESS vs CA au stade Olympique",
                now,
                now.plusHours(4)
        ));

        System.out.println("🚦 " + trafficRepository.count() + " états de trafic créés");
    }

    private void printStatistics() {
        System.out.println("\n📊 STATISTIQUES DES DONNÉES :");
        System.out.println("================================");
        System.out.println("Lignes de transport : " + transportRepository.count());
        System.out.println("Horaires : " + scheduleRepository.count());
        System.out.println("États de trafic : " + trafficRepository.count());

        // Statistiques par type
        long metroCount = transportRepository.findByType("METRO").size();
        long busCount = transportRepository.findByType("BUS").size();
        long trainCount = transportRepository.findByType("TRAIN").size();
        long tramCount = transportRepository.findByType("TRAM").size();

        System.out.println("\n📈 Répartition par type :");
        System.out.println("  • Métro : " + metroCount + " lignes");
        System.out.println("  • Bus : " + busCount + " lignes");
        System.out.println("  • Train : " + trainCount + " lignes");
        System.out.println("  • Tram : " + tramCount + " lignes");

        // Statistiques des retards
        long delayedCount = scheduleRepository.findByStatus(Schedule.Status.DELAYED).size();
        long cancelledCount = scheduleRepository.findByStatus(Schedule.Status.CANCELLED).size();
        long totalSchedules = scheduleRepository.count();

        if (totalSchedules > 0) {
            double delayedPercentage = (delayedCount * 100.0) / totalSchedules;
            double cancelledPercentage = (cancelledCount * 100.0) / totalSchedules;

            System.out.println("\n⚠️  État des horaires :");
            System.out.println("  • Retardés : " + delayedCount + " (" + String.format("%.1f", delayedPercentage) + "%)");
            System.out.println("  • Annulés : " + cancelledCount + " (" + String.format("%.1f", cancelledPercentage) + "%)");
            System.out.println("  • À l'heure : " + (totalSchedules - delayedCount - cancelledCount));
        }

        // Lignes actives/inactives
        List<TransportLine> activeLines = transportRepository.findByIsActiveTrue();
        List<TransportLine> inactiveLines = transportRepository.findAll().stream()
                .filter(line -> !line.getIsActive())
                .toList();

        System.out.println("\n✅ État des lignes :");
        System.out.println("  • Actives : " + activeLines.size());
        System.out.println("  • Inactives : " + inactiveLines.size());

        System.out.println("\n🔗 URLs de test :");
        System.out.println("  • Toutes les lignes : GET http://localhost:8081/api/transport/lines");
        System.out.println("  • Métro seulement : GET http://localhost:8081/api/transport/lines/METRO");
        System.out.println("  • Horaires TGM : GET http://localhost:8081/api/transport/schedules/TGM");
        System.out.println("  • Problèmes actuels : GET http://localhost:8081/api/transport/traffic/issues");
        System.out.println("  • Swagger UI : http://localhost:8081/swagger-ui.html");
        System.out.println("================================\n");
    }
}