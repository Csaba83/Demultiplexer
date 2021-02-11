package org.example.matcher;

import org.example.Config;

public class AlignmentMatcherFactory {

    public static AlignmentMatcher getMatcher(Config.Alignment alignment) {

        switch (alignment) {

            case ENDS:
                return new EndsAlignmentMatcher();
            case MID:
                return new MidAlignmentMatcher();
            case BEST:
                return new BestAlignmentMatcher();
            default:
                throw new IllegalStateException("Unexpected value: " + alignment);
        }
    }
}
