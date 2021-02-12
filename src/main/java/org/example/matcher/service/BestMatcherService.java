package org.example.matcher.service;

import org.example.config.Group;
import org.example.matcher.MatchGroup;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BestMatcherService implements MatcherService {
    @Override
    public List<MatchGroup> match(Collection<String> sequences, List<Group> groups) {
        List<MatchGroup> matchGroups = new LinkedList<>();
        List<String> unmatched = new LinkedList<>(sequences);

        for (Group group : groups) {
            MatchGroup matchGroup = new MatchGroup(group.getName());
            String inputPattern = group.getInfix();
            List<String> sequencesContainsTheLongestSubpattern =
                    getSequencesContainsTheLongestSubpattern(sequences, inputPattern);
            matchGroup.addAll(sequencesContainsTheLongestSubpattern);
            unmatched.removeAll(sequencesContainsTheLongestSubpattern);
            matchGroups.add(matchGroup);
        }

        collectUnmatched(matchGroups, unmatched);

        return matchGroups;
    }

    private List<String> getSequencesContainsTheLongestSubpattern(Collection<String> sequences, String pattern) {
        for (int length = pattern.length(); length > 0; length--) {
            List<String> subPatterns = getSubstringsInLength(pattern, length);
            List<String> sequencesContainsPatterns = getSequencesContainsPatterns(sequences, subPatterns);
            if (!sequencesContainsPatterns.isEmpty()) {
                return sequencesContainsPatterns;
            }
        }
        return Collections.emptyList();
    }

    private List<String> getSubstringsInLength(String pattern, int length) {
        List<String> subStrings = new LinkedList<>();
        for (int beginIndex = 0; beginIndex <= pattern.length() - length; beginIndex++) {
            int endIndex = beginIndex + length;
            if ( endIndex <= pattern.length()) {
                subStrings.add(pattern.substring(beginIndex, endIndex));
            }
        }
        return subStrings;
    }

    private List<String> getSequencesContainsPatterns(Collection<String> sequences, List<String> patterns) {
        List<String> sequencesContainsPatterns = new LinkedList<>();
        for (String pattern : patterns) {
            sequencesContainsPatterns.addAll(getSequencesContainsParticularPattern(sequences, pattern));
        }
        return sequencesContainsPatterns;
    }


    private List<String> getSequencesContainsParticularPattern(Collection<String> sequences, String pattern) {
        List<String> matchingSequences = new LinkedList<>();
        for (String sequence : sequences) {
            if (sequence.contains(pattern)) {
                matchingSequences.add(sequence);
            }
        }

        return matchingSequences;
    }

    private void collectUnmatched(List<MatchGroup> matchGroups, List<String> unmatched) {
        MatchGroup unmatchedGroup = new MatchGroup("unmatched");
        unmatchedGroup.addAll(unmatched);
        matchGroups.add(unmatchedGroup);
    }
}
