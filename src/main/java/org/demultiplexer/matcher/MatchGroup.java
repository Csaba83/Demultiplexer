package org.demultiplexer.matcher;

import java.util.LinkedList;
import java.util.List;

public class MatchGroup {

    private String groupName;
    private List<String> sequences;

    public MatchGroup(String groupName) {
        this.groupName = groupName;
        this.sequences = new LinkedList<>();
    }

    public void add(String sequence) {
        sequences.add(sequence);
    }

    public void addAll(List<String> sequences) {
        this.sequences.addAll(sequences);
    }

    public String getGroupName() {
        return groupName;
    }

    public List<String> getSequences() {
        return sequences;
    }
}
