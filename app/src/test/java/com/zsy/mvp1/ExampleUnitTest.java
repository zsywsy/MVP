package com.zsy.mvp1;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Ignore
    @Test
    public void addition_isCorrect2() throws Exception {
        assertEquals(4, 2 + 3);
    }
    @Test
    public void addition_isCorrect3() throws Exception {
        assertEquals(4, 2 + 3);
    }
}