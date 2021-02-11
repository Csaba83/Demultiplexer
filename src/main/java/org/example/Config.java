package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

    Map<Alignment, List<AlignmentGroup>> groups = new HashMap<>();

    public void addGroups(Alignment alignment, List<AlignmentGroup> groups) {
        this.groups.put(alignment, groups);
    }

    public List<AlignmentGroup> getGroups(Alignment alignment) {
        return groups.get(alignment);
    }

    public enum Alignment {
        ENDS("endsAlignment"),
        MID("midAlignment"),
        BEST("bestAlignment");

        private String name;

        Alignment(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Alignment getValueByName(String name) {
            for (Alignment value: values()) {
                if (value.name.equals(name)) {
                    return value;
                }
            }
            throw new IllegalStateException("Unexpected name: " + name);
        }
    }

    public static class AlignmentGroup {

        private String name;
        private String prefix;
        private String infix;
        private String postfix;

        public AlignmentGroup(String name, String prefix, String infix, String postfix) {
            this.name = name;
            this.prefix = prefix;
            this.infix = infix;
            this.postfix = postfix;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getInfix() {
            return infix;
        }

        public void setInfix(String infix) {
            this.infix = infix;
        }

        public String getPostfix() {
            return postfix;
        }

        public void setPostfix(String postfix) {
            this.postfix = postfix;
        }
    }
}
