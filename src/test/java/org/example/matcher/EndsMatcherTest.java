package org.example.matcher;

import org.example.config.Group;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndsMatcherTest {

    private static final String SEQUENCE_1 = "ABCDE";
    private static final String SEQUENCE_2 = "CDEFG";
    private static final String GROUP_NAME_1 = "g1";
    private static final String GROUP_NAME_2 = "g2";
    private static final String SEQUENCE_X = "XXXX";
    private static Group group1;
    private static Group group2;
    private static EndsMatcher matcher;

    @Before
    public void setUp() {
        matcher = new EndsMatcher();
        group1 = new Group.GroupBuilder(GROUP_NAME_1).setPrefix("AB").setPostfix("DE").createGroup();
        group2 = new Group.GroupBuilder(GROUP_NAME_2).setPrefix("AB").setPostfix("FG").createGroup();
    }

    @Test
    public void matchBothEnd() {
        assertTrue(matcher.match(SEQUENCE_1, group1));
    }

    @Test
    public void matchStart() {
        assertFalse(matcher.match(SEQUENCE_1, group2));
    }

    @Test
    public void matchEnd() {
        assertFalse(matcher.match(SEQUENCE_2, group2));
    }

    @Test
    public void noMatchAtAll() {
        assertFalse(matcher.match(SEQUENCE_X, group2));
    }
}