package org.example;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String t = """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """;

        System.out.println(t);

//        String i = readFromKeyboard(scanner);
//
//
//
//    public static int readFromKeyboard(Scanner scanner) {
//        if (i == "e")
//            return scanner.nextInt();

//        INMATNING ELPRISERNA
        String[] times = {"00-01", "01-02", "02-03", "03-04", "04-05", "05-06", "06-07", "07-08", "08-09", "09-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16", "16-17", "17-18", "18-19", "19-20", "20-21", "21-22", "22-23", "23-00"};
        int[] perHour = new int[times.length];

        for (int i = 0; i < times.length; i++) {
            System.out.print("Pris för timmen " + times[i] + ": ");
            perHour[i] = scanner.nextInt();
        }



//        MIN MAX MEDEL PRIS

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

        System.out.println("        Lägsta pris: " + min_index + ", " + min_price + " öre/kWh");
        System.out.println("        Högsta pris: " + max_index + ", " + max_price + " öre/kWh");

// calculate sum
        double sum = 0;

        for (int i = 0; i < perHour.length; i++) {
            sum += perHour[i];
        }

//Calculate avarage, change to SE format
        double average = sum / (perHour.length);

        System.out.print("        Medelpris: ");
        System.out.printf(Locale.of("sv", "SE"), "%.2f", average);
        System.out.println(" öre/kWh");


// 4 tunni keskmine!!!
// liidu kokku 4 numbrit -- for loop, mida tegime paaride puhul
/// otsi miinimum nendest
//        UUS ARRAY????

        String[] fourHours = {"00-04", "01-05", "02-06", "03-07", "04-08", "05-09", "06-10", "07-11", "08-12", "09-13", "10-14", "11-15", "12-16", "13-17", "14-18", "15-19", "16-20", "17-21", "18-22", "19-23", "20-00"};
        int[] bestTimes = new int[fourHours.length];


        for (int i = 0; i < fourHours.length; i++) {
            bestTimes[i] = (perHour[i] + perHour[i + 1] + perHour[i + 2] + perHour[i + 3]);
        }
//        System.out.print("\ntulemused: " + Arrays.toString(bestTimes));


        int bestTiming = bestTimes[0];
        String bestHours = fourHours[0];


        for (int i = 1; i < fourHours.length; i++) {
            if (bestTimes[i] < bestTiming) {
                bestTiming = bestTimes[i];
                bestHours = fourHours[i];
            }
        }
        double averageFour = (double) bestTiming / 4;
        System.out.print("\n        Påbörja laddning klockan " + bestHours);
        System.out.print("\n        Medelpris 4h: ");
        System.out.printf(Locale.of("sv", "SE"), "%.2f", averageFour);
        System.out.println(" öre/kWh");
//        System.out.printf("Medelpris 4h: %.2f öre" + averageFour) ;

    }
}