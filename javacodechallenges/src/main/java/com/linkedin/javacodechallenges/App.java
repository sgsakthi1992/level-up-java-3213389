package com.linkedin.javacodechallenges;

import java.util.Scanner;

public class App {

    public static double calculateWaterBill(double gallonsUsage) {
        var totalBill = 18.84;
        if(gallonsUsage > 1496) {
            Double usedCCFs = (gallonsUsage - 1496)/749;
            System.out.println(usedCCFs.intValue());
            System.out.println(usedCCFs);
            totalBill += 3.90 + (3.90 * usedCCFs.intValue());
        }
        return totalBill;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many gallons of water did you " +
                "use this month?");
        double usage = scanner.nextDouble();
        System.out.println("Your water bill is " +
                calculateWaterBill(usage));
        scanner.close();
    }
}
