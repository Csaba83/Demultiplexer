package org.example.matcher;

import org.example.Config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatcherService {

    private AlignmentMatcher matcher;

    public MatcherService(AlignmentMatcher matcher) {
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

        MatchGroup unmatchedGroup = new MatchGroup("unmatched");
        unmatchedGroup.addAll(unmatched);
        matchGroups.add(unmatchedGroup);

        return matchGroups;
    }

}
