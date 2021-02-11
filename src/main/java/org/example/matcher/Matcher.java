package org.example.matcher;

import org.example.config.Group;

public interface Matcher {

    boolean match(String sequence, Group group);
}
