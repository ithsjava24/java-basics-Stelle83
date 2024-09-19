package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static final int HOURS = 24;
    public static int[] perHour = new int[HOURS];
    public static final String[] times = {"00-01", "01-02", "02-03", "03-04",
            "04-05", "05-06", "06-07", "07-08",
            "08-09", "09-10", "10-11", "11-12",
            "12-13", "13-14", "14-15", "15-16",
            "16-17", "17-18", "18-19", "19-20",
            "20-21", "21-22", "22-23", "23-24"};


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String menuChoice;


        do {
            printMenu(scanner);
            menuChoice = scanner.nextLine().trim().toLowerCase();

            switch (menuChoice) {
                case "1":
                    inputPrices(scanner);
                    break;
                case "2":
                    calcMinMaxAverage();
                    break;
                case "3":
                    sorted();
                    break;
                case "4":
                    best4hours();;
                    break;
                case "e":
                    System.out.print("closing program!\n");
                    return;
                default:
                    System.out.print("invalid choice\n");
            }
        } while (menuChoice != "e");

        scanner.close();
    }

    private static void printMenu(Scanner scanner) {
        String t = """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """;

        System.out.print(t);
    }

    //        1. INMATNING ELPRISERNA
    private static void inputPrices(Scanner scanner) {
//        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < HOURS; i++) {
            System.out.print("Pris för timmen " + times[i] + ": ");
            perHour[i] = Integer.parseInt(scanner.nextLine());
        }
//        System.out.printf("\n");
    }


    //        2. MIN MAX MEDEL PRIS
    private static void calcMinMaxAverage() {

        int max_price = perHour[0];
        int min_price = perHour[0];
        String max_index = times[0];
        String min_index = times[0];

        for (int i = 1; i < perHour.length; i++) {
            if (perHour[i] > max_price) {
                max_price = perHour[i];

                max_index = times[i];
            }
            if (perHour[i] < min_price) {
                min_price = perHour[i];
                min_index = times[i];
            }
        }

        System.out.print("Lägsta pris: " + min_index + ", " + min_price + " öre/kWh");
        System.out.printf("\nHögsta pris: " + max_index + ", " + max_price + " öre/kWh");

        // calculate sum
        double sum = 0;

        for (int i = 0; i < perHour.length; i++) {
            sum += perHour[i];
        }

        //Calculate avarage, change to SE format
        double average = sum / (perHour.length);

        System.out.print("\nMedelpris: ");
        System.out.printf(Locale.of("sv", "SE"), "%.2f", average);
        System.out.print(" öre/kWh\n");
//        System.out.printf("\n");
    }

//SORTED
private static void sorted() {

        String[][] priceHourPair = new String[HOURS][2];

        for (int i = 0; i < HOURS; i++) {
            priceHourPair[i][0] = String.valueOf(perHour[i]);
            priceHourPair[i][1] = times[i];
        }

Arrays.sort(priceHourPair, new Comparator<String[]>() {
    @Override
    public int compare(String[] o1, String[] o2) {
        return Integer.compare(Integer.parseInt(o2[0]), Integer.parseInt(o1[0]));
    }
});

        for (int i = 0; i < HOURS; i++) {
            perHour[i] = Integer.parseInt(priceHourPair[i][0]);
            times[i] = priceHourPair[i][1];
        }

        for (int i = 0; i < HOURS; i++) {
            System.out.printf(times[i] + " " + perHour[i] + " öre\n");
        }


}

// 4. 4 tunni keskmine!!! liidu kokku 4 numbrit -- for loop, mida tegime paaride puhul
    private static void best4hours() {

        String[] fourHours = {"00-04", "01-05", "02-06", "03-07",
                            "04-08", "05-09", "06-10", "07-11",
                            "08-12", "09-13", "10-14", "11-15",
                            "12-16", "13-17", "14-18", "15-19",
                            "16-20", "17-21", "18-22", "19-23", "20-00"};
        int[] bestTimes = new int[fourHours.length];


        for (int i = 0; i < fourHours.length; i++) {
            bestTimes[i] = (perHour[i] + perHour[i + 1] + perHour[i + 2] + perHour[i + 3]);
        }

        int bestTiming = bestTimes[0];
        String bestHours = fourHours[0];


        for (int i = 1; i < fourHours.length; i++) {
            if (bestTimes[i] < bestTiming) {
                bestTiming = bestTimes[i];
                bestHours = fourHours[i];
            }
        }
        double averageFour = (double) bestTiming / 4;
        System.out.print("Påbörja laddning klockan " + bestHours.substring(0,2) + "\n");
        System.out.printf("Medelpris 4h: %.1f öre/kWh\n", averageFour) ;
    }
}
