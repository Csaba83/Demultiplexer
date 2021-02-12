package org.demultiplexer.matcher;

import org.demultiplexer.config.Group;

public class MidMatcher implements Matcher {
    @Override
    public boolean match(String sequence, Group group) {
        return sequence.contains(group.getInfix());
        //TODO clarify requirement
        //return sequence.matches("^.+"+group.getInfix()+".+$");
    }
}
