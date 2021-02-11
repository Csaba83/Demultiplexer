package org.example.matcher.service;

import org.example.config.Group;
import org.example.matcher.MatchGroup;

import java.util.Collection;
import java.util.List;

public interface MatcherService {

    List<MatchGroup> match(Collection<String> sequences, List<Group> groups);
}
