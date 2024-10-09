import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize rooms
        Room room1 = new Room("Single", 100.0, true);
        Room room2 = new Room("Double", 150.0, true);
        Room room3 = new Room("Suite", 200.0, true);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        while (true) {
            System.out.println("Hotel Reservation System");
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    searchForAvailableRooms(scanner);
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void searchForAvailableRooms(Scanner scanner) {
        System.out.print("Enter check-in date (yyyy-MM-dd): ");
        String checkInDateStr = scanner.nextLine();
        LocalDate checkInDate = LocalDate.parse(checkInDateStr, DateTimeFormatter.ISO_DATE);
        System.out.print("Enter check-out date (yyyy-MM-dd): ");
        String checkOutDateStr = scanner.nextLine();
        LocalDate checkOutDate = LocalDate.parse(checkOutDateStr, DateTimeFormatter.ISO_DATE);

        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }

        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for the specified dates.");
        } else {
            System.out.println("Available rooms:");
            for (Room room : availableRooms) {
                System.out.println("Room type: " + room.getType());
                System.out.println("Rate: " + room.getRate());
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter room type: ");
        String roomType = scanner.nextLine();
        System.out.print("Enter check-in date (yyyy-MM-dd): ");
        String checkInDateStr = scanner.nextLine();
        LocalDate checkInDate = LocalDate.parse(checkInDateStr, DateTimeFormatter.ISO_DATE);
        System.out.print("Enter check-out date (yyyy-MM-dd): ");
        String checkOutDateStr = scanner.nextLine();
        LocalDate checkOutDate = LocalDate.parse(checkOutDateStr, DateTimeFormatter.ISO_DATE);
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        Room room = findRoom(roomType);
        if (room == null) {
            System.out.println("Room type not found.");
            return;
        }

        if (!room.isAvailable(checkInDate, checkOutDate)) {
            System.out.println("Room not available for the specified dates.");
            return;
        }

        Reservation reservation = new Reservation(room, checkInDate, checkOutDate, guestName);
        reservations.add(reservation);
        System.out.println("Reservation made successfully!");
    }

    private static void viewBookingDetails(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        Reservation reservation = findReservation(reservationId);
        if (reservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        System.out.println("Reservation details:");
        System.out.println("Room type: " + reservation.getRoom().getType());
        System.out.println("Check-in date: " + reservation.getCheckInDate());
        System.out.println("Check-out date: " + reservation.getCheckOutDate());
        System.out.println("Guest name: " + reservation.getGuestName());
    }

    private static Room findRoom(String roomType) {
        for (Room room : rooms) {
            if (room.getType().equals(roomType)) {
                return room;
            }
        }
        return null;
    }

    private static Reservation findReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }
}

class Room {
    private String type;
    private double rate;
    private boolean available;

    public Room(String type, double rate, boolean available) {
        this.type = type;
        this.rate = rate;
        this.available = available;
    }

    public String getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }

    public boolean isAvailable(LocalDate checkInDate, LocalDate checkOutDate) {
        // TO DO: implement logic to check room availability
        return true;
    }
}

class Reservation {
    private int id;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String guestName;

    public Reservation(Room room, LocalDate checkInDate, LocalDate checkOutDate, String guestName) {
        this.id = ReservationSystem.getReservationId();
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestName = guestName;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public String getGuestName() {
        return guestName;
    }
}

class ReservationSystem {
    private static int reservationId = 1;

    public static int getReservationId() {
        return reservationId++;
    }
}