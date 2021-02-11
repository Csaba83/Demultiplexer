package org.example.matcher;

import java.util.HashSet;
import java.util.Set;

public class MatchGroup {

    private String groupName;
    private Set<String> sequences;

    public MatchGroup(String groupName) {
        this.groupName = groupName;
        this.sequences = new HashSet<>();
    }

    public void add(String sequence) {
        sequences.add(sequence);
    }

    public void addAll(Set<String> sequences) {
        this.sequences.addAll(sequences);
    }

    public String getGroupName() {
        return groupName;
    }

    public Set<String> getSequences() {
        return sequences;
    }
}
