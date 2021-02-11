package org.example.matcher;

import org.example.Config;

public interface Matcher {

    boolean match(String sequence, Config.AlignmentGroup alignmentGroup);
}
