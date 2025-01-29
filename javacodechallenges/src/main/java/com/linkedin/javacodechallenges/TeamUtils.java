package com.linkedin.javacodechallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
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

    sortedTeamsByScores.values().stream().findFirst().ifPresentOrElse(TeamUtils::printWinner, null);
    sortedTeamsByScores.values().stream()
      .skip(1)
      .forEach(TeamUtils::printOtherPlayers);
    } else {
      System.out.println("The game hasn't started yet.");
    }
  }

  private static boolean checkRevealConditions(List<Team> teams){
    if(teams.size() > 0){
      return teams.stream().allMatch(team -> team.getScores().size() > 0);
    }
    return false;
  }

  private static TreeMap<Integer, List<Team>> getSortedTeamsByScores(List<Team> teams) {
    return teams.stream()
        .collect(Collectors.toMap(
            Team::sumTotalScore,
            value -> new ArrayList<>(Arrays.asList(value)),
            (o1, o2) -> {
              o1.addAll(o2);
              return o1;
            },
            () -> new TreeMap<>(Comparator.reverseOrder())));
  }

  private static void printWinner(List<Team> winners) {
    System.out.println("Now for the results, the WINNER is...");
    if(winners.size() > 1) {
      System.out.println("It's a TIE!");
      winners.stream().forEach(TeamUtils::printWinnerMessage);
    } else {
      printWinnerMessage(winners.get(0));
    }
    System.out.println();
  }

  private static void printWinnerMessage(Team winner) {
    System.out.println("With " + winner.sumTotalScore() + " points, it's team " + winner.getPlayer1()
        + " and " + winner.getPlayer2() + "!");
  }

  private static void printOtherPlayers(List<Team> otherTeam) {
    System.out.println("Then we have... ");
    if(otherTeam.size() > 1) {
      System.out.println("It's a TIE!");
      otherTeam.stream().forEach(TeamUtils::printOtherPlayersMessage);
    } else {
      printOtherPlayersMessage(otherTeam.get(0));
    }
    System.out.println();
  }

  private static void printOtherPlayersMessage(Team team) {
    System.out.println("With " + team.sumTotalScore() + " points, it's team " + team.getPlayer1()
        + " and " + team.getPlayer2() + "!");
  }
}