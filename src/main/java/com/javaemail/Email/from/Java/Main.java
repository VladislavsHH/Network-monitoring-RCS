package com.javaemail.Email.from.Java;

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main (String[]args) throws InterruptedException {

        Scanner s = new Scanner(System.in);

        System.out.println("INTERNETA ĀTRUMA MĒRĪTĀJS");

        System.out.println("1. Ievadi savu vārdu:");
        String name = s.nextLine();

        System.out.println("2. Ievadi savu e-pasta adresi:");
        String mail = s.nextLine();

        System.out.println("3. Ievadi mājas lapu, kuru vēlies mērīt:");
        String url = s.nextLine();

        System.out.println("Mājas lapas  " + url + "interneta ātrums:");




        while (true) {
            TimeUnit.SECONDS.sleep(5);
            try {
                testSpeed();
                System.out.println("------------------------");
            } catch (IOException e) {
                //INSERT SEND EMAIL FUNCTION HERE
                break;
            }
        }
    }

    public static void testSpeed () throws IOException {

        long totalDownload = 0; // total bytes downloaded
        final int BUFFER_SIZE = 1024; // size of the buffer
        byte[] data = new byte[BUFFER_SIZE]; // buffer
        int dataRead = 0; // data read in each try
        long startTime = System.nanoTime(); // starting time of download

        BufferedInputStream in = new BufferedInputStream(
                new URL("https://www.ss.com/")
                        .openStream());

        while ((dataRead = in.read(data, 0, 1024)) > 0) {
            totalDownload += dataRead; // adding data downloaded to total data

            if (System.nanoTime() - startTime > new Long("60000000000")) {
                throw new IOException("Internet connection has been lost");
            }
        }

        double downloadTime = (System.nanoTime() - startTime);
        /* download rate in bytes per second */
        double bytesPerSec = totalDownload
                / ((downloadTime) / 1000000000);
        System.out.println(bytesPerSec + " Bps");
        /* download rate in kilobytes per second */

        double kbPerSec = bytesPerSec / (1024);
        System.out.println(kbPerSec + " KBps ");

        /* download rate in megabytes per second */
        double mbPerSec = kbPerSec / (1024);
        System.out.println(mbPerSec + " MBps ");
    }
}
