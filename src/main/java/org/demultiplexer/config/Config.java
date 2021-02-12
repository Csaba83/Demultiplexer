package org.demultiplexer.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

    Map<Alignment, List<Group>> groups = new HashMap<>();

    public void addGroups(Alignment alignment, List<Group> groups) {
        this.groups.put(alignment, groups);
    }

    public List<Group> getGroups(Alignment alignment) {
        return Collections.unmodifiableList(groups.get(alignment));
    }

}
