package org.example.matcher;

import org.example.Config;

import java.util.HashSet;
import java.util.Set;

public class BestAlignmentMatcher implements AlignmentMatcher {

    private int minimumMatchLength;

    @Override
    public boolean match(String sequence, Config.AlignmentGroup alignmentGroup) {
        String inputPattern = alignmentGroup.getInfix();
        int length = minimumMatchLength > 0 ? minimumMatchLength : inputPattern.length();
        for (; length > 0; length--) {
            Set<String> patterns = getSubstringsInLength(inputPattern, length);
            if (sequenceContainsPatterns(sequence, patterns)) {
                minimumMatchLength = length;
                return true;
            }
        }
        return false;
    }

    private boolean sequenceContainsPatterns(String sequence, Set<String> patterns) {
        for (String pattern : patterns) {
            if (sequence.contains(pattern)) {
                return true;
            }
        }
        return false;
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
}
