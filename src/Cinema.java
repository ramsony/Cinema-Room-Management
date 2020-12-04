import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numberOfRows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = sc.nextInt();
        int totalSeats = numberOfSeats * numberOfRows;
        int frontRows = numberOfRows / 2 ;
        int backRows = numberOfRows - frontRows;
        int totalIncome;
        int ticketPrice = 0;
        int numberOfTicketPurchased = 0;
        int totalTicketPrice = 0;

        if (totalSeats < 60) {
            totalIncome = totalSeats * 10;

        } else {
            totalIncome = (frontRows * numberOfSeats * 10) + (backRows * numberOfSeats * 8);
        }

        char[][] cinema = new char[++numberOfRows][++numberOfSeats];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 1; j < numberOfSeats; j++) {
                cinema[i][j] = 'S';
            }
        }

        while (true) {
            System.out.print("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n");
            double  percent = numberOfTicketPurchased > 0 ? (numberOfTicketPurchased / (double)totalSeats) * 100 : 0;
            int in = sc.nextInt();
            switch (in) {
                case 1:
                    printCinema(cinema,numberOfRows,numberOfSeats);
                    break;
                case 2:
                    System.out.println("Enter a row number:");
                    int numRows = sc.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int numSeats = sc.nextInt();

                    while (checkSeatChoice(cinema, numRows, numSeats,numberOfRows,numberOfSeats)) {
                        System.out.println("Enter a row number:");
                        numRows = sc.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        numSeats = sc.nextInt();
                    }

                    ticketPrice = totalSeats > 60 && numRows > frontRows ? 8 : 10;
                    totalTicketPrice += ticketPrice;
                    cinema[numRows][numSeats] = 'B';
                    System.out.println("Ticket price: $"+ticketPrice);
                    numberOfTicketPurchased++;
                    break;
                case 3:
                    System.out.println("\nNumber of purchased tickets: " + numberOfTicketPurchased);
                    System.out.printf("Percentage:% .2f%s", percent, "%\n");
                    System.out.println("Current income: $" + totalTicketPrice);
                    System.out.println("Total income: $"+ totalIncome);
                    break;
                case 0 :
                    return;
                default:
                    System.out.println("Choice not available!");
            }
        }
    }

    public static boolean checkSeatChoice(char[][] cinema, int numRows, int numSeats,int numberOfRows, int numberOfSeats) {
        if (numRows > numberOfRows - 1 || numSeats > numberOfSeats - 1) {
            System.out.println("Wrong input!");
            return true;
        } else if (cinema[numRows][numSeats] == 'B') {
            System.out.println("That ticket has already been purchased!");
            return true;
        } else {
            return false;
        }
    }
    public static void printCinema(char [][] cinema,int rowNum ,int seatNum) {
        int row = 1;
        int col = 0;
        System.out.printf("%nCinema:%n");
        System.out.print("  ");

        while (col < seatNum - 1){
            col++;
            System.out.printf("%d ",col);
        }

        System.out.println();
        for (int i = 1; i < rowNum; i++) {
            System.out.printf("%d", row++);
            for (int j = 0; j < seatNum; j++) {
                System.out.printf("%c ", cinema[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
