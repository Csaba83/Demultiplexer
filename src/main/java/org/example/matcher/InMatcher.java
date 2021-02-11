package org.example.matcher;

import org.example.Config;

public class InMatcher implements Matcher {
    @Override
    public boolean match(String sequence, Config.AlignmentGroup alignmentGroup) {
        return sequence.contains(alignmentGroup.getInfix());
    }
}
