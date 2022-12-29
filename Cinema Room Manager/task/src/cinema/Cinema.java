package cinema;

import java.util.Scanner;
public class Cinema {
    public static char[][] seats;
    public static int rowLength;
    public static int seatLength;
    public static int row;
    public static int seat;
    public static int ticketPrice;
    public static int current_income;
    public static int total_income;
    public static int totalSeats;
    public static int firstHalf;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        numberOfSeats();
    }

    static void numberOfSeats() {

        System.out.println("Enter the number of rows:");
        rowLength = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatLength = scanner.nextInt();
        seats = new char[rowLength + 1][seatLength + 1];
        totalSeats = rowLength * seatLength;
        if (totalSeats <= 60) {
            ticketPrice = 10;
        } else {
            if (row % 2 == 0) {
                firstHalf = ((rowLength) / 2);
            } else {
                firstHalf = ((rowLength - 1) / 2);
            }
            if (row > firstHalf) {
                ticketPrice = 8;
            } else {
                ticketPrice = 10;
            }
        }
        menu();
    }

    static void menu() {
        System.out.println("""
                1. Show the seats
                2. Buy a tickets
                3. Statistics
                0. Exits""");
        int action = scanner.nextInt();
        switch (action) {
            case 1:
                seats();
                menu();
            case 2:
                price();
                menu();
            case 3:
                statistics();
                menu();
            case 0:
                return;
        }

    }


    static void price() {
        System.out.println("Enter a row number: ");
        row = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        seat = scanner.nextInt();
        check();
        buy ();
        if (totalSeats <= 60) {
            ticketPrice = 10;
            System.out.println("Ticket price: " + "$" + ticketPrice);
            current_income = current_income + 10;
        } else {
            if (row % 2 == 0) {
                firstHalf = ((rowLength) / 2) ;
            } else {
                firstHalf = ((rowLength - 1) / 2);
            }
            if (row > firstHalf) {
                ticketPrice = 8;
                current_income = current_income + 8;
            } else {
                ticketPrice = 10;
                current_income = current_income + 10;
            }
            System.out.println("Ticket price: " + "$" + ticketPrice);
            System.out.println();
        }
    }

    static void buy () {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (i == 0 && j == 0) {
                    seats[i][j] = ' ';
                } else if (i == 0) {
                    seats[i][j] = (char) ('0' + j);
                } else if (i == row && j == seat){
                    seats[i][j] = 'B';
                    seats[row][seat] = 'B';
                } else if (seats[i][j]!= 'B'){
                    seats[i][j] = 'S';
                }
            }
        }
        for (int i = 1; i < seats.length; i++) {
            seats[i][0] = (char)('0' + i);
        }
    }
    static void check() {
        if (row > rowLength) {
            System.out.println("Wrong input!");
            System.out.println();
            price();
        } else if (seat > seatLength) {
            System.out.println("Wrong input!");
            System.out.println();
            price();
        } else if (seats[row][seat] == 'B') {
                System.out.println("That ticket has already been purchased");
                System.out.println();
                price();
        }
    }

    static void seats() {
        System.out.println("Cinema:");
        buy();
        for (char[] seat : seats) {
            for (char c : seat) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void statistics() {
        int count = 0;
        int first_income = 0;
        int second_income = 0;
        int ticketPrice_statistics = 0;
        int total = rowLength * seatLength;
        for (char[] chars : seats) {
            for (int j = 0; j < seats[0].length; j++) {
                if (chars[j] == 'B') {
                    count++;
                }
            }
        }

        float percent = (count * 100) / (float) total;
        if (total <= 60) {
            ticketPrice_statistics = 10;
            first_income = total * ticketPrice;
        } else {
            first_income = ((rowLength - firstHalf) * seatLength) * 8;
            second_income = ((firstHalf) * seatLength) * 10;
        }

        total_income = first_income + second_income;
        System.out.println("Number of purchased tickets: " + count);
        System.out.println("Percentage: " + String.format("%.2f%%",percent));
        System.out.printf("Current income: $%d", current_income);
        System.out.println();
        System.out.printf("Total income: $%d", total_income);
        System.out.println();
        System.out.println();
    }
}


