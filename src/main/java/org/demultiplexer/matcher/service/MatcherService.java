package org.demultiplexer.matcher.service;

import org.demultiplexer.config.Group;
import org.demultiplexer.matcher.MatchGroup;

import java.util.Collection;
import java.util.List;

public interface MatcherService {

    List<MatchGroup> match(Collection<String> sequences, List<Group> groups);
}
