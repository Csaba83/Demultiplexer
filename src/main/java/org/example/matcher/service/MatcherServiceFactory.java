package org.example.matcher.service;

import org.example.config.Alignment;
import org.example.matcher.EndsMatcher;
import org.example.matcher.MidMatcher;

public class MatcherServiceFactory {

    public static MatcherService getMatcherService(Alignment alignment) {
        switch (alignment) {

            case ENDS:
                return new SimpleMatcherService(new EndsMatcher());
            case MID:
                return new SimpleMatcherService(new MidMatcher());
            case BEST:
                return new BestMatcherService();
            default:
                throw new IllegalStateException("Unexpected value: " + alignment);
        }
    }
}
