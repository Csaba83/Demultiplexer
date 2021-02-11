package org.example.matcher;

import org.example.Config;

public class MatcherFactory {

    public static Matcher getMatcher(Config.Alignment alignment) {

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
