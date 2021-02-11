package org.example.matcher.service;

import org.example.Config;
import org.example.matcher.MatchGroup;
import org.example.matcher.Matcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleMatcherService implements MatcherService {

    private Matcher matcher;

    public SimpleMatcherService(Matcher matcher) {
        this.matcher = matcher;
    }

    public List<MatchGroup> match(Set<String> sequences, List<Config.AlignmentGroup> groups) {
        List<MatchGroup> matchGroups = new ArrayList<>();
        Set<String> unmatched = new HashSet<>(sequences);

        for (Config.AlignmentGroup group : groups) {
            MatchGroup matchGroup = new MatchGroup(group.getName());
            for (String sequence: sequences) {
                if (matcher.match(sequence, group)) {
                    matchGroup.add(sequence);
                    unmatched.remove(sequence);
                }
            }
            matchGroups.add(matchGroup);
        }

        collectUnmatched(matchGroups, unmatched);

        return matchGroups;
    }

    private void collectUnmatched(List<MatchGroup> matchGroups, Set<String> unmatched) {
        MatchGroup unmatchedGroup = new MatchGroup("unmatched");
        unmatchedGroup.addAll(unmatched);
        matchGroups.add(unmatchedGroup);
    }

}
