package org.demultiplexer.matcher.service;

import org.demultiplexer.config.Alignment;
import org.demultiplexer.matcher.EndsMatcher;
import org.demultiplexer.matcher.MidMatcher;

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
