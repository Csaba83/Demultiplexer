//package org.example.matcher;
//
//import org.example.Config;
//
//public class MatcherServiceFactory {
//
//    public static MatcherService getMatcherService(Config.Alignment alignment) {
//        switch (alignment) {
//
//            case ENDS:
//            case MID:
//                return new MatcherService(AlignmentMatcherFactory.getMatcher(alignment));
//            case BEST:
//                return new BestMatcherService();
//        }
//    }
//}
