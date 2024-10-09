import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TravelItineraryPlanner {
    private static ArrayList<Destination> destinations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Travel Itinerary Planner");
            System.out.println("1. Add Destination");
            System.out.println("2. Generate Itinerary");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    addDestination(scanner);
                    break;
                case 2:
                    generateItinerary();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void addDestination(Scanner scanner) {
        System.out.print("Enter destination name: ");
        String name = scanner.next();
        System.out.print("Enter destination date (yyyy-MM-dd): ");
        String dateStr = scanner.next();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        System.out.print("Enter budget for destination: ");
        double budget = scanner.nextDouble();
        System.out.print("Enter preferred activities (comma-separated): ");
        String activities = scanner.next();

        Destination destination = new Destination(name, date, budget, activities);
        destinations.add(destination);
        System.out.println("Destination added successfully!");
    }

    private static void generateItinerary() {
        if (destinations.isEmpty()) {
            System.out.println("No destinations added. Please add destinations first.");
            return;
        }

        System.out.println("Generated Itinerary:");
        for (Destination destination : destinations) {
            System.out.println("Destination: " + destination.getName());
            System.out.println("Date: " + destination.getDate());
            System.out.println("Budget: " + destination.getBudget());
            System.out.println("Preferred Activities: " + destination.getActivities());
            System.out.println("Weather: " + getWeather(destination.getDate())); // TO DO: implement weather API call
            System.out.println("Map: " + getMap(destination.getName())); // TO DO: implement map API call
            System.out.println();
        }
    }

    private static String getWeather(LocalDate date) {
        // TO DO: implement weather API call
        return "Weather information not available";
    }

    private static String getMap(String destinationName) {
        // TO DO: implement map API call
        return "Map not available";
    }
}

class Destination {
    private String name;
    private LocalDate date;
    private double budget;
    private String activities;

    public Destination(String name, LocalDate date, double budget, String activities) {
        this.name = name;
        this.date = date;
        this.budget = budget;
        this.activities = activities;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getBudget() {
        return budget;
    }

    public String getActivities() {
        return activities;
    }
}