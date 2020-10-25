import java.text.Format;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        char[][] array = makeGrid(rows,seats);

        Menu.start(sc,array,rows,seats);


    }
    public static class Menu {
        static boolean menuStopper = true;
        static int numberOfPurchasedTickets = 0;
        static int currentIncome = 0;
        public static void start(Scanner sc, char[][] array, int rows, int seats){
            while (menuStopper) {
                System.out.println("1. Show the seats");
                System.out.println("2. Buy a ticket");
                System.out.println("3. Statistics");
                System.out.println("0. Exit");
                int chosenMenuNumber = sc.nextInt();
                switch (chosenMenuNumber) {
                    case 1:
                        printGrid(array);
                        break;
                    case 2:
                        Menu.buyTicket(sc,array,rows,seats);
                        break;
                    case 3:

                        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
                        double percentage = (100f / (rows  * seats) * numberOfPurchasedTickets);
                        System.out.printf("Percentage: %.2f%s", percentage, "%");
                        System.out.println();
                        System.out.println("Current income: $" + currentIncome);
                        System.out.println("Total income: $" + totalIncome(rows, seats));
                        break;
                    case 0:
                        menuStopper = false;
                        break;

                }
            }
        }
        public static void buyTicket(Scanner sc, char[][] array, int rows, int seats){
            boolean bought = true;
            while (bought) {
                System.out.println("Enter a row number:");
                int row = sc.nextInt();
                System.out.println("Enter a seat number in that row:");
                int seat = sc.nextInt();

                if(row > rows || seat > seats){
                    System.out.println("Wrong input!");
                    buyTicket(sc,array,rows,seats);
                    break;
                } else if (array[row][seat] == 'B'){
                    System.out.println("That ticket has already been purchased!");
                    buyTicket(sc,array,rows,seats);
                    break;
                } else {
                    currentIncome += checkTicketPrice(rows, seats, row);
                    makeGrid(array, rows, seats, row, seat);
                    numberOfPurchasedTickets++;
                    bought = false;
                }
            }
        }
    }
    public static int totalIncome (int rows, int seats) {
        if (rows * seats <= 60) {
            return rows * seats * 10;
        } else if (rows % 2 == 0) {
            rows = rows / 2;
            return (rows * 8 + rows * 10) * seats;
        } else {
            rows = rows / 2;
            return ((rows + 1) * 8 + rows * 10) * seats;
        }
    }
    public static int checkTicketPrice(int rows, int seats, int row){
        if (rows * seats <= 60) {
            System.out.println("Ticket price: $10");
            return 10;
        } else{
            if (row <= rows / 2){
                System.out.println("Ticket price: $10");
                return 10;
            } else {
                System.out.println("Ticket price: $8");
                return 8;
            }
        }
    }
    public static void printGrid(char[][] array){
        System.out.println("Cinema:");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[1].length; j++) {
                if (j == 0 && i == 0) {
                    System.out.print(array[i][j] + " ");
                } else if (i == 0) {
                    System.out.print(array[i][j] + " ");
                } else if (j == 0) {
                    System.out.print(array[i][j] + " ");
                } else {
                    System.out.print(array[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    public static char[][] makeGrid(int rows, int seats){
        char[][] array = new char[rows + 1][seats + 1];

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (j == 0 && i == 0) {
                    array[i][j] = ' ';
                } else if (i == 0) {
                    array[i][j] = (char)('0' + j);
                } else if (j == 0) {
                    array[i][j] = (char)('0' + i);
                } else {
                    array[i][j] = 'S';
                }
            }
        }
        return array;
    }

    public static char[][] makeGrid(char[][] array, int rows, int seats, int row, int seat){

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (array[i][j] == 'B'){
                    continue;
                }
                if (j == 0 && i == 0) {
                    array[i][j] = ' ';
                } else if (i == 0) {
                    array[i][j] = (char)('0' + j);
                } else if (j == 0) {
                    array[i][j] = (char)('0' + i);
                } else if(row == i && seat == j) {
                    array[i][j] = 'B';
                } else {
                    array[i][j] = 'S';
                }
            }
        }
        return array;
    }
}