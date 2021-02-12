package org.demultiplexer.config;

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
