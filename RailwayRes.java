import java.util.ArrayList;
import java.util.Scanner;

public class RailwayRes {
    static final int TOTAL_SEATS = 10;
    static ArrayList<Ticket> bookedTickets = new ArrayList<>();
    static boolean[] seats = new boolean[TOTAL_SEATS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n Railway Reservation System ");
            System.out.println("1. Book Ticket");
            System.out.println("2. View Booked Tickets");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    bookTicket(scanner);
                    break;
                case 2:
                    viewTickets();
                    break;
                case 3:
                    cancelTicket(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the Railway Reservation System!");
                    scanner.close();
                    return;
                default:
                    System.out.println(" Invalid choice! Please select a valid option.");
            }
        }
    }

    static void bookTicket(Scanner scanner) {
        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine();

        int availableSeat = -1;
        for (int i = 0; i < TOTAL_SEATS; i++) {
            if (!seats[i]) {
                availableSeat = i + 1;
                break;
            }
        }

        if (availableSeat == -1) {
            System.out.println("Sorry, all seats are booked!");
        } else {
            seats[availableSeat - 1] = true;
            bookedTickets.add(new Ticket(name, availableSeat));
            System.out.println("Ticket booked! Seat No: " + availableSeat);
        }
    }

    static void viewTickets() {
        if (bookedTickets.isEmpty()) {
            System.out.println("📭 No tickets booked yet.");
        } else {
            System.out.println("\n📋 Booked Tickets:");
            for (Ticket ticket : bookedTickets) {
                System.out.println(ticket);
            }
        }
    }

    static void cancelTicket(Scanner scanner) {
        System.out.print("Enter seat number to cancel: ");
        int seatNumber = scanner.nextInt();

        Ticket ticketToRemove = null;
        for (Ticket ticket : bookedTickets) {
            if (ticket.seatNumber == seatNumber) {
                ticketToRemove = ticket;
                break;
            }
        }

        if (ticketToRemove != null) {
            bookedTickets.remove(ticketToRemove);
            seats[seatNumber - 1] = false;
            System.out.println("Ticket cancelled for Seat No: " + seatNumber);
        } else {
            System.out.println(" No booking found for Seat No: " + seatNumber);
        }
    }
}
    class Ticket {
        String passengerName;
        int seatNumber;
    
        Ticket(String passengerName, int seatNumber) {
            this.passengerName = passengerName;
            this.seatNumber = seatNumber;
        }
    
        public String toString() {
            return "Seat No: " + seatNumber + ", Passenger: " + passengerName;
        }
    }


