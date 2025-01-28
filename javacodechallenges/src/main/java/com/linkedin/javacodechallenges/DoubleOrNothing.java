package com.linkedin.javacodechallenges;

import java.util.Random;
import java.util.Scanner;

public class DoubleOrNothing {

  public void playGame() {
    var points = 10;
    var scanner = new Scanner(System.in);
    var userInput = "yes";
    var random = new Random();
    var isStillPlaying = true;
    while(isStillPlaying) {
      System.out.println("Available points: " + points);
      System.out.println("Do you want to continue (yes/no):");
      userInput = scanner.nextLine();
      if(userInput.equalsIgnoreCase("yes")){
        if(random.nextBoolean()) {
          points = points * 2;
        } else {
          System.out.println("Better luck next time");
          isStillPlaying = false;
        }
      } else{
        System.out.println("Thanks for playing. Points won: " +points);
        isStillPlaying = false;
      }
    }
    scanner.close();
  }
}