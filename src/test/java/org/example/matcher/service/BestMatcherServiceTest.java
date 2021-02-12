package org.example.matcher.service;

import org.example.config.Group;
import org.example.matcher.MatchGroup;
import org.example.matcher.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BestMatcherServiceTest {

    private static final String SEQUENCE_1 = "ABCDE";
    private static final String SEQUENCE_2 = "CDEFG";
    private static final String SEQUENCE_Z = "ZZZZZ";
    private static final String GROUP_NAME_1 = "g1";
    private static final String GROUP_NAME_2 = "g2";
    private static final String UNMATCHED_GROUP = "unmatched";
    private static Group group1;
    private static Group group2;
    private static Matcher matcher;

    @Before
    public void setUp() {
        matcher = Mockito.mock(Matcher.class);
    }

    @Test
    public void noMatches() {
        group1 = new Group.GroupBuilder(GROUP_NAME_1).setInfix("XXXXX").createGroup();
        group2 = new Group.GroupBuilder(GROUP_NAME_2).setInfix("XXXXX").createGroup();
        BestMatcherService bestMatcherService = new BestMatcherService();

        List<MatchGroup> match = bestMatcherService.match(Arrays.asList(SEQUENCE_1, SEQUENCE_2), Arrays.asList(group1, group2));

        assertEquals(3, match.size());
        assertEquals(GROUP_NAME_1, match.get(0).getGroupName());
        assertEquals(0, match.get(0).getSequences().size());
        assertEquals(GROUP_NAME_2, match.get(1).getGroupName());
        assertEquals(0, match.get(1).getSequences().size());
        assertEquals(UNMATCHED_GROUP, match.get(2).getGroupName());
        assertEquals(Arrays.asList(SEQUENCE_1, SEQUENCE_2), match.get(2).getSequences());
    }

    @Test
    public void returnsMatches() {
        group1 = new Group.GroupBuilder(GROUP_NAME_1).setInfix("XXDEF").createGroup();
        group2 = new Group.GroupBuilder(GROUP_NAME_2).setInfix("XXXCD").createGroup();
        when(matcher.match(SEQUENCE_2, group1)).thenReturn(true);
        BestMatcherService bestMatcherService = new BestMatcherService();

        List<MatchGroup> match = bestMatcherService.match(Arrays.asList(SEQUENCE_1, SEQUENCE_2, SEQUENCE_Z), Arrays.asList(group1, group2));

        assertEquals(3, match.size());
        assertEquals(GROUP_NAME_1, match.get(0).getGroupName());
        assertEquals(Collections.singletonList(SEQUENCE_2), match.get(0).getSequences());
        assertEquals(GROUP_NAME_2, match.get(1).getGroupName());
        assertEquals(Arrays.asList(SEQUENCE_1, SEQUENCE_2), match.get(1).getSequences());
        assertEquals(UNMATCHED_GROUP, match.get(2).getGroupName());
        assertEquals(Collections.singletonList(SEQUENCE_Z), match.get(2).getSequences());
    }

}