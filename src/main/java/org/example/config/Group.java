package org.example.config;

import java.util.Objects;

public class Group {

    private String name;
    private String prefix;
    private String infix;
    private String postfix;

    private Group(String name, String prefix, String infix, String postfix) {
        this.name = name;
        this.prefix = prefix;
        this.infix = infix;
        this.postfix = postfix;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getInfix() {
        return infix;
    }

    public String getPostfix() {
        return postfix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return name.equals(group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    public static class GroupBuilder {
        private String name;
        private String prefix = "";
        private String infix = "";
        private String postfix = "";

        public GroupBuilder(String name) {
            this.name = name;
        }

        public GroupBuilder setPrefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public GroupBuilder setInfix(String infix) {
            this.infix = infix;
            return this;
        }

        public GroupBuilder setPostfix(String postfix) {
            this.postfix = postfix;
            return this;
        }

        public Group createGroup() {
            return new Group(name, prefix, infix, postfix);
        }
    }
}
