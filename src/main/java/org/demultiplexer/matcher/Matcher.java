package org.demultiplexer.matcher;

import org.demultiplexer.config.Group;

public interface Matcher {

    boolean match(String sequence, Group group);
}
