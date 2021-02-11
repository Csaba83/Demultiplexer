package org.example.matcher.service;

import org.example.config.Alignment;
import org.example.matcher.MatcherFactory;

public class MatcherServiceFactory {

    public static MatcherService getMatcherService(Alignment alignment) {
        switch (alignment) {

            case ENDS:
            case MID:
                return new SimpleMatcherService(MatcherFactory.getMatcher(alignment));
            case BEST:
                return new BestMatcherService();
            default:
                throw new IllegalStateException("Unexpected value: " + alignment);
        }
    }
}
