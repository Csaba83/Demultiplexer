package org.example.matcher;

import org.example.Config;

public interface AlignmentMatcher {

    boolean match(String sequence, Config.AlignmentGroup alignmentGroup);
}
