package org.example.matcher.service;

import org.example.Config;
import org.example.matcher.MatchGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BestMatcherService implements MatcherService {
    @Override
    public List<MatchGroup> match(Set<String> sequences, List<Config.AlignmentGroup> groups) {
        List<MatchGroup> matchGroups = new ArrayList<>();
        Set<String> unmatched = new HashSet<>(sequences);

        for (Config.AlignmentGroup group : groups) {
            MatchGroup matchGroup = new MatchGroup(group.getName());
            String inputPattern = group.getInfix();
            for (int length = inputPattern.length(); length > 0; length--) {
                Set<String> patterns = getSubstringsInLength(inputPattern, length);
                Set<String> sequencesContainsPatterns = getSequencesContainsPatterns(sequences, patterns);
                if (!sequencesContainsPatterns.isEmpty()) {
                    matchGroup.addAll(sequencesContainsPatterns);
                    matchGroups.add(matchGroup);
                    unmatched.removeAll(sequencesContainsPatterns);
                    break;
                }
            }
        }

        collectUnmatched(matchGroups, unmatched);

        return matchGroups;
    }

    private Set<String> getSubstringsInLength(String pattern, int length) {
        Set<String> subStrings = new HashSet<>();
        for (int beginIndex = 0; beginIndex < pattern.length() - length; beginIndex++) {
            int endIndex = beginIndex + length;
            if ( endIndex < pattern.length()) {
                subStrings.add(pattern.substring(beginIndex, endIndex));
            }
        }
        return subStrings;
    }

    private Set<String> getSequencesContainsPatterns(Set<String> sequences, Set<String> patterns) {
        Set<String> sequencesContainsPatterns = new HashSet<>();
        for (String pattern : patterns) {
            sequencesContainsPatterns.addAll(getSequencesContainsParticularPattern(sequences, pattern));
        }
        return sequencesContainsPatterns;
    }


    private Set<String> getSequencesContainsParticularPattern(Set<String> sequences, String pattern) {
        Set<String> matchingSequences = new HashSet<>();
        for (String sequence : sequences) {
            if (sequence.contains(pattern)) {
                matchingSequences.add(sequence);
            }
        }

        return matchingSequences;
    }

    private void collectUnmatched(List<MatchGroup> matchGroups, Set<String> unmatched) {
        MatchGroup unmatchedGroup = new MatchGroup("unmatched");
        unmatchedGroup.addAll(unmatched);
        matchGroups.add(unmatchedGroup);
    }
}
