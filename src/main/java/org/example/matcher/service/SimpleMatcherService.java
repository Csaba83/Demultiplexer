package org.example.matcher.service;

import org.example.config.Group;
import org.example.matcher.MatchGroup;
import org.example.matcher.Matcher;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SimpleMatcherService implements MatcherService {

    private Matcher matcher;

    public SimpleMatcherService(Matcher matcher) {
        this.matcher = matcher;
    }

    public List<MatchGroup> match(Collection<String> sequences, List<Group> groups) {
        List<MatchGroup> matchGroups = new LinkedList<>();
        List<String> unmatched = new LinkedList<>(sequences);

        for (Group group : groups) {
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

    private void collectUnmatched(List<MatchGroup> matchGroups, List<String> unmatched) {
        MatchGroup unmatchedGroup = new MatchGroup("unmatched");
        unmatchedGroup.addAll(unmatched);
        matchGroups.add(unmatchedGroup);
    }

}
