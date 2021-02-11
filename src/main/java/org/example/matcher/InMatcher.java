package org.example.matcher;

import org.example.config.Group;

public class InMatcher implements Matcher {
    @Override
    public boolean match(String sequence, Group group) {
        return sequence.contains(group.getInfix());
    }
}
