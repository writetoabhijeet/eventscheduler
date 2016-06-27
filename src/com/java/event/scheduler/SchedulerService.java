package com.java.event.scheduler;

import com.java.event.match.Match;

import java.util.List;
import java.util.Map;

/**
 * Created by USER on 15-06-2016.
 */
public interface SchedulerService {

    Map<Integer, List<Match>> arrangeMatches(List<Match> matchList);
}
