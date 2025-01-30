package com.linkedin.javacodechallenges;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    private static Map<String, Integer> tickets = new HashMap<>();
    public static void main(String[] args) {
        loadCSVFile();
        Scanner scanner = new Scanner(System.in);
        var doContinue = "yes";
        while(doContinue.equalsIgnoreCase("yes")) {
            System.out.println("Please enter the name: ");
            var name = scanner.nextLine();
            if(tickets.containsKey(name.toUpperCase())) {
                System.out.println("Please enter the number of persons: ");
                var count = scanner.nextLine();
                if(tickets.get(name.toUpperCase()) >= Integer.parseInt(count)){
                    System.out.println("Please enter!");
                } else {
                    System.out.println("Sorry only " + tickets.get(name.toUpperCase()) + " is/are allowed");
                }
            } else {
                System.out.println("Sorry details not found");
            }
            System.out.println("Do you want to check other? (yes/no)");
            doContinue = scanner.nextLine();
        }
        scanner.close();
    }

    private static void loadCSVFile() {
        try{
            var file = Files.readAllLines(Path.of("ticketholders.csv"));
            file.stream()
            .skip(1)
            .forEach(App::updateTickets);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void updateTickets(String line) {
        var details = line.split(",");
        if(details.length == 2) {
            tickets.put(details[0].toUpperCase(), Integer.parseInt(details[1]));
        }
    }
}
