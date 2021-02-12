package org.demultiplexer.matcher.service;

import org.demultiplexer.config.Group;
import org.demultiplexer.matcher.MatchGroup;
import org.demultiplexer.matcher.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class SimpleMatcherServiceTest {

    private static final String SEQUENCE_1 = "ABCDE";
    private static final String SEQUENCE_2 = "CDEFG";
    private static final String GROUP_NAME_1 = "g1";
    private static final String GROUP_NAME_2 = "g2";
    private static final String UNMATCHED_GROUP = "unmatched";
    private static Group group1;
    private static Group group2;
    private static Matcher matcher;

    @Before
    public void setUp() {
        matcher = Mockito.mock(Matcher.class);
        group1 = new Group.GroupBuilder(GROUP_NAME_1).setInfix("DEF").createGroup();
        group2 = new Group.GroupBuilder(GROUP_NAME_2).setInfix("BCD").createGroup();
    }

    @Test
    public void noMatches() {
        SimpleMatcherService simpleMatcherService = new SimpleMatcherService(matcher);

        List<MatchGroup> match = simpleMatcherService.match(Arrays.asList(SEQUENCE_1, SEQUENCE_2), Arrays.asList(group1, group2));

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
        when(matcher.match(SEQUENCE_2, group1)).thenReturn(true);
        SimpleMatcherService simpleMatcherService = new SimpleMatcherService(matcher);

        List<MatchGroup> match = simpleMatcherService.match(Arrays.asList(SEQUENCE_1, SEQUENCE_2), Arrays.asList(group1, group2));

        assertEquals(3, match.size());
        assertEquals(GROUP_NAME_1, match.get(0).getGroupName());
        assertEquals(Collections.singletonList(SEQUENCE_2), match.get(0).getSequences());
        assertEquals(GROUP_NAME_2, match.get(1).getGroupName());
        assertEquals(0, match.get(1).getSequences().size());
        assertEquals(UNMATCHED_GROUP, match.get(2).getGroupName());
        assertEquals(Collections.singletonList(SEQUENCE_1), match.get(2).getSequences());
    }

}