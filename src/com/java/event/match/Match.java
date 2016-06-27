package com.java.event.match;

import com.java.event.team.Team;

/**
 * Created by USER on 13-06-2016.
 */
public class Match {

    Team firstTeam;
    Team secondTeam;
    String matchGroung;
    int matchDay;


    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    public String getMatchGroung() {
        return matchGroung;
    }

    @Override
    public String toString() {
        return "Match{" +
                "firstTeam=" + firstTeam.getName() +
                ", secondTeam=" + secondTeam.getName() +
                ", matchGroung='" + matchGroung + '\'' +
                ", matchDay=" + matchDay +
                '}';
    }

    public void setMatchGroung(String matchGroung) {
        this.matchGroung = matchGroung;
    }

    public int getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(int matchDay) {
        this.matchDay = matchDay;
    }


}
