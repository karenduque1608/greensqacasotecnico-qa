package com.latam.generator;

import com.latam.generator.model.User;
import com.latam.generator.repository.SQLiteRepository;
import com.latam.generator.repository.UserRepository;
import com.latam.generator.service.CsvService;
import com.latam.generator.service.EmailService;
import com.latam.generator.service.UserService;
import com.latam.generator.util.Constants;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserRepository repository = new SQLiteRepository();
        UserService userService = new UserService(repository);
        CsvService csvService = new CsvService();

        System.out.println("=========================================");
        System.out.println("     LATAM - Test Data Generator");
        System.out.println("=========================================");

        System.out.print("How many records do you want to generate? ");

        int quantity = scanner.nextInt();

        System.out.print("Delete previous records? (Y/N): ");

        String answer = scanner.next().trim().toUpperCase();

        if (answer.equals("Y")) {
            repository.deleteAll();
            System.out.println("Previous records deleted successfully.");
        }

        System.out.print("Generate in parallel? (Y/N): ");

        boolean parallel = scanner.next().trim().equalsIgnoreCase("Y");

        System.out.println();
        System.out.println("Generating users...");
        System.out.println();

        List<User> users = parallel
                ? userService.generateUsersParallel(quantity, Runtime.getRuntime().availableProcessors())
                : userService.generateUsers(quantity);

        csvService.exportUsers(users);

        System.out.println("-----------------------------------------");
        System.out.println("Generation completed successfully.");
        System.out.println("Users generated : " + users.size());
        System.out.println("Database        : latam_users.db");
        System.out.println("CSV             : output/users.csv");
        System.out.println("-----------------------------------------");

        sendCsvByEmailIfRequested(scanner);

        scanner.close();
    }

    private static void sendCsvByEmailIfRequested(Scanner scanner) {

        System.out.print("Send the generated CSV file by email? (Y/N): ");

        boolean sendEmail = scanner.next().trim().equalsIgnoreCase("Y");

        if (!sendEmail) {
            return;
        }

        EmailService emailService = new EmailService();

        if (!emailService.isConfigured()) {
            System.out.println("SMTP is not configured (set SMTP_HOST, SMTP_USER and SMTP_PASSWORD). Email skipped.");
            return;
        }

        System.out.print("Recipient email: ");

        String recipient = scanner.next().trim();

        emailService.sendCsvFile(new File(Constants.CSV_FILE), recipient);

        System.out.println("Email sent successfully to " + recipient);

    }

}