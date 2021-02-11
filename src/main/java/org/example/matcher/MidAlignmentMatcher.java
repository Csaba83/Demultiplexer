package org.example.matcher;

import org.example.Config;

public class MidAlignmentMatcher implements AlignmentMatcher {
    @Override
    public boolean match(String sequence, Config.AlignmentGroup alignmentGroup) {
        return sequence.contains(alignmentGroup.getInfix());
    }
}
