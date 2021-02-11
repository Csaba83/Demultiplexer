package org.example.matcher;

import org.example.Config;

public class EndsAlignmentMatcher implements AlignmentMatcher {

    public boolean match(String sequence, Config.AlignmentGroup alignmentGroup) {
        return sequence.startsWith(alignmentGroup.getPrefix()) && sequence.endsWith(alignmentGroup.getPostfix());
    }
}
