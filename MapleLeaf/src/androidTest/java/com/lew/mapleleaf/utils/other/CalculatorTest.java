package com.lew.mapleleaf.utils.other;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Richie on 2017/6/6
 */
public class CalculatorTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void add() throws Exception {
        int result = Calculator.add(3, 7);
        Assert.assertEquals(result, 19);
    }

}