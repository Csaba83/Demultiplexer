package org.demultiplexer.matcher;

import org.demultiplexer.config.Group;

public class EndsMatcher implements Matcher {

    public boolean match(String sequence, Group group) {
        return sequence.startsWith(group.getPrefix()) && sequence.endsWith(group.getPostfix());
    }
}
