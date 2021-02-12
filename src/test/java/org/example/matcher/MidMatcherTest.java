package org.example.matcher;

import org.example.config.Group;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MidMatcherTest {

    private static final String SEQUENCE_1 = "ABCDE";
    private static final String SEQUENCE_2 = "BCDEF";
    private static final String GROUP_NAME_1 = "g1";
    private static final String GROUP_NAME_2 = "g2";
    private static final String SEQUENCE_X = "XXXX";
    private static Group group1;
    private static Group group2;
    private static MidMatcher matcher;

    @Before
    public void setUp() {
        matcher = new MidMatcher();
        group1 = new Group.GroupBuilder(GROUP_NAME_1).setInfix("BCD").createGroup();
        group2 = new Group.GroupBuilder(GROUP_NAME_2).setInfix("DEF").createGroup();
    }

    @Test
    public void noMatch() {
        assertFalse(matcher.match(SEQUENCE_X, group1));
    }

    @Test
    public void matchIn() {
        assertTrue(matcher.match(SEQUENCE_1, group1));
    }

    @Test
    public void matchStart() { //TODO clarify requirement
        //assertFalse(matcher.match(SEQUENCE_2, group1));
        assertTrue(matcher.match(SEQUENCE_2, group1));
    }

    @Test
    public void matchEnd() { //TODO clarify requirement
        //assertFalse(matcher.match(SEQUENCE_2, group2));
        assertTrue(matcher.match(SEQUENCE_2, group2));
    }


}