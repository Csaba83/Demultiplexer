package org.example.matcher.service;

import org.example.Config;
import org.example.matcher.MatchGroup;

import java.util.List;
import java.util.Set;

public interface MatcherService {

    List<MatchGroup> match(Set<String> sequences, List<Config.AlignmentGroup> groups);
}
