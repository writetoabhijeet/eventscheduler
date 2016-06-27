package com.java.event;

import com.java.event.match.Match;
import com.java.event.team.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 13-06-2016.
 */
public class EventApplication {

    public static void main(String args[]) {

        List<Team> teamList = new ArrayList<>();
        List<Match> matchList = new ArrayList<>();
        Team firstTeam;
        Team secondTeam;
        Match match;

        for (int i = 1; i < 6; i++) {

            firstTeam = new Team();
            firstTeam.setName("TEAM-" + i);
            firstTeam.setHomeGround("HomeGround-" + i);

            teamList.add(firstTeam);

            for (int j = 1; j < 6; j++) {

                if (i != j) {
                    secondTeam = new Team();
                    secondTeam.setName("TEAM-" + j);
                    secondTeam.setHomeGround("HomeGround-" + j);

                    match = new Match();
                    match.setFirstTeam(firstTeam);
                    match.setSecondTeam(secondTeam);

                    match.setMatchGroung(firstTeam.getHomeGround());

                    matchList.add(match);
                }

            }

        }


        Map<Integer, List<Match>> scheduleMatches = arrangeMatches(matchList);
        System.out.println("No of teams- " + teamList.size());
        System.out.println("No of Matches - " + matchList.size());
        Map<Integer, List<Match>> finalSchedule = arrangeMatches(matchList);

        for(Integer i : finalSchedule.keySet()){
            System.out.println("Day - "+i.toString() );

            for(Match matchobj : finalSchedule.get(i)){
                System.out.println(matchobj);
            }
        }

    }

    private static Map<Integer, List<Match>> arrangeMatches(List<Match> matchList) {
        Map<Integer, List<Match>> result = new HashMap<>();
        int day = 1;
        List<Match> matchListForDay;
        List<Match> remainingMatchesToSchedule = new ArrayList<>(matchList);
       while(!remainingMatchesToSchedule.isEmpty()) {
           oneRoundRobin(remainingMatchesToSchedule, matchList, result, day);
       }

        return result;
    }

    private static void oneRoundRobin(List<Match> remainingMatchesToSchedule, List<Match> matchList, Map<Integer, List<Match>> result, int day) {
        int rrCounter =1;
        List<Match> matchListForDay;
        for (Match match : matchList) {

            //Initial Loop for match
            if (result.isEmpty()) {
                matchListForDay = new ArrayList<>();
                remainingMatchesToSchedule.remove(match);
                matchListForDay.add(match);
                match.setMatchDay(day);
                result.put(day, matchListForDay);


            } else if (result.containsKey(day)) {

                matchListForDay = result.get(day);
                if (matchListForDay.size() == 2) {
                    day++;
                    continue;
                }

                if (isAnyTeamPlayedYesterday(result, day, match)) {
                    continue;
                }else{
                    remainingMatchesToSchedule.remove(match);
                    matchListForDay.add(match);
                    match.setMatchDay(day);
                    result.put(day,matchListForDay);
                    day++;
                    continue;
                }

            } else {

                if (isAnyTeamPlayedYesterday(result, day, match)) {
                    continue;
                }else{
                    matchListForDay = new ArrayList<>();
                    remainingMatchesToSchedule.remove(match);
                    matchListForDay.add(match);
                    match.setMatchDay(day);
                    result.put(day,matchListForDay);
                    continue;
                }

            }
             if(rrCounter == matchList.size()){
                 break;
             }else{
                 rrCounter++;
             }
        }
    }

    private static boolean isAnyTeamPlayedYesterday(Map<Integer, List<Match>> result, int day, Match match) {
        boolean isplayed = false;
        if (day - 1 < 1) {
            return isplayed;
        }

        String team1 = match.getFirstTeam().getName();
        String team2 = match.getSecondTeam().getName();

        if (result.containsKey(day - 1)) {
            List<Match> yesterdayMatchList = result.get(day - 1);
            for (Match yesterdayMatch : yesterdayMatchList) {
                if (yesterdayMatch.getFirstTeam().getName().equals(team1) || yesterdayMatch.getSecondTeam().getName().equals(team1) ||
                        yesterdayMatch.getFirstTeam().getName().equals(team2) || yesterdayMatch.getSecondTeam().getName().equals(team2)) {
                    isplayed = true;
                }
            }
        }

        return isplayed;
    }

}
