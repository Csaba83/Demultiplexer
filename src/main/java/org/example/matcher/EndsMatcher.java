package org.example.matcher;

import org.example.config.Group;

public class EndsMatcher implements Matcher {

    public boolean match(String sequence, Group group) {
        return sequence.startsWith(group.getPrefix()) && sequence.endsWith(group.getPostfix());
    }
}
