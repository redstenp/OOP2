package com.festival;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        var schedule = new FestivalSchedule();

        // Add Performances
        schedule.addPerformances(
                new Performance("The Kinks", "Main Stage", LocalTime.of(18, 0)),
                new Performance("Fleetwood Mac", "Main Stage", LocalTime.of(20, 0)),
                new Performance("Neil Young", "Main Stage", LocalTime.of(22, 0)),
                new Performance("The Grateful Dead", "The Kashmir Stage", LocalTime.of(18, 0)),
                new Performance("Pink Floyd", "The Kashmir Stage", LocalTime.of(20, 0)),
                new Performance("Led Zeppelin", "The Kashmir Stage", LocalTime.of(22, 0)),
                new Performance("Nick Cave", "Acoustic Stage", LocalTime.of(18, 0)),
                new Performance("Joni Mitchell", "Acoustic Stage", LocalTime.of(20, 0)),
                new Performance("Bob Dylan", "Acoustic Stage", LocalTime.of(22, 0))
        );

        // Group by stage
        System.out.println("\nPerformances grouped by stage:");
        schedule.getGroupedByStage().forEach((stage, performances) -> {
            System.out.println("Stage: " + stage);
            performances.forEach(System.out::println);
        });

        // Tickets
        Ticket regularTicket = new RegularTicket(101, "Alice", "A1");
        Ticket vipTicket = new VIPTicket(102, "Bob", "Platinum Lounge");

        System.out.println("\nTicket Information:");
        List.of(regularTicket, vipTicket).forEach(Main::printTicketPrice);

        // Vendors
        var vendors = List.of(
                new Vendor("Burger Shack", Vendor.VendorType.FOOD),
                new Vendor("Rock Tees", Vendor.VendorType.MERCHANDISE),
                new Vendor("Cool Brews", Vendor.VendorType.DRINKS)
        );

        System.out.println("\nVendor Information:");
        vendors.forEach(Vendor::displayDetails);

        // Sorted performances
        System.out.println("\nSorted Performances by Time:");
        schedule.getAllPerformances().stream()
                .sorted(Comparator.comparing(Performance::timeSlot))
                .forEach(System.out::println);

        // Concurrency
        System.out.println("\nProcessing concurrent ticket validation:");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Callable<String>> tasks = List.of(
                () -> "Validating " + regularTicket.attendeeName,
                () -> "Validating " + vipTicket.attendeeName
        );
        executor.invokeAll(tasks).forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error during ticket validation: " + e.getMessage());
            }
        });
        executor.shutdown();

        // NIO2 File Write
        Path filePath = Paths.get("performances.txt");
        try {
            Files.write(filePath, schedule.getAllPerformances().stream()
                    .map(Performance::toString)
                    .collect(Collectors.toList()));
            System.out.println("\nPerformance data saved to " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to write performance file.");
        }

        // Localisation
        ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.FRANCE);
        System.out.println("\nFor our French guests: " + bundle.getString("greeting"));
    }

    public static void printTicketPrice(Ticket ticket) {
        String ticketType = switch (ticket) {
            case RegularTicket rt -> "Regular Ticket";
            case VIPTicket vt -> "VIP Ticket";
        };

        double price = switch (ticket) {
            case RegularTicket rt -> 100.00;
            case VIPTicket vt -> 250.00;
        };

        System.out.printf("%s price: €%.2f%n", ticketType, price);
    }
}