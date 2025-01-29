package com.linkedin.javacodechallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TeamUtils {

  public static void generateTeamsScores(List<Team> teams,
      int numberOfRounds) {
    Random random = new Random();
    teams.forEach(team -> {
      for (int i = 0; i < numberOfRounds; i++) {
        team.getScores().add(random.nextInt(11));
      }
    });
  }

  public static void revealResults(List<Team> teams) {
    if(checkRevealConditions(teams)){
    var sortedTeamsByScores = getSortedTeamsByScores(teams);

    sortedTeamsByScores.values().stream()
    .findFirst()
    .ifPresentOrElse(TeamUtils::printWinner, null);
    sortedTeamsByScores.values().stream()
      .skip(1)
      .forEach(TeamUtils::printOtherPlayers);
    } else {
      System.out.println("The game hasn't started yet.");
    }
  }

  private static boolean checkRevealConditions(List<Team> teams){
    if(!teams.isEmpty()){
      return teams.stream().allMatch(team -> !team.getScores().isEmpty());
    }
    return false;
  }

  private static TreeMap<Integer, List<Team>> getSortedTeamsByScores(List<Team> teams) {
    return teams.stream()
          .collect(Collectors.groupingBy(Team::sumTotalScore,
            () -> new TreeMap<>(Comparator.reverseOrder()),
            Collectors.toList())
          );
  }

  private static void printWinner(List<Team> winners) {
    System.out.println("Now for the results, the WINNER is...");
    if(winners.size() > 1) {
      printTieMessage(winners);
    } else {
      printMessage(winners.get(0));
    }
    System.out.println();
  }

  private static void printOtherPlayers(List<Team> otherTeams) {
    System.out.println("Then we have... ");
    if(otherTeams.size() > 1) {
      printTieMessage(otherTeams);
    } else {
      printMessage(otherTeams.get(0));
    }
    System.out.println();
  }

  private static void printTieMessage(List<Team> teams) {
      System.out.println("It's a TIE!");
      teams.stream().forEach(TeamUtils::printMessage);
  }

  private static void printMessage(Team team) {
    System.out.println("With " + team.sumTotalScore() + " points, it's team " + team.getPlayer1()
        + " and " + team.getPlayer2() + "!");
  }
}