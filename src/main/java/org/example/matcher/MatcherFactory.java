package org.example.matcher;

import org.example.config.Alignment;

public class MatcherFactory {

    public static Matcher getMatcher(Alignment alignment) {

        switch (alignment) {

            case ENDS:
                return new EndsMatcher();
            case MID:
            case BEST:
                return new InMatcher();
            default:
                throw new IllegalStateException("Unexpected value: " + alignment);
        }
    }
}
