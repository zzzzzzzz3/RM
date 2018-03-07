package com.quseit.payapp;

import com.quseit.payapp.util.AmountInputUtil;

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

    @Test
    public void checkAmount() throws Exception {
        String initAmount = "0.00";
        assertEquals("0.01", initAmount = AmountInputUtil.input("1",initAmount));
        assertEquals("0.12", initAmount = AmountInputUtil.input("2",initAmount));
        assertEquals("0.01", initAmount = AmountInputUtil.deleteNum(initAmount,true));
        assertEquals("0.00", initAmount = AmountInputUtil.deleteNum(initAmount,true));

        for (int i = 0; i < 9; i++) {
            initAmount = AmountInputUtil.input(""+i,initAmount);
        }

        for (int i = 0; i < 9; i++) {
            initAmount = AmountInputUtil.deleteNum(initAmount,true);
        }

        assertEquals("0.00", initAmount);

    }
}