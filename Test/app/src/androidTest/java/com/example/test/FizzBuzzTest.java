package com.example.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FizzBuzzTest {
    FizzBuzz fizzBuzz ;

    @Before
    public void setUp() throws Exception {
        fizzBuzz = new FizzBuzz();
    }
    @Test
    public void TestMultiple3(){
        String msm = fizzBuzz.execute(6);
        assertThat(msm,is(FizzBuzz.FIZZ));
    }

    @Test
    public void TestMultiple5(){
        String msm = fizzBuzz.execute(10);
        assertThat(msm,is(FizzBuzz.BUZZ));
    }
    @Test
    public void TestMultiple3and5(){
        String msm = fizzBuzz.execute(15);
        assertThat(msm,is(fizzBuzz.FIZZ+FizzBuzz.BUZZ));
    }
    @Test
    public void TestNotMultiple3or5(){
        String msm = fizzBuzz.execute(4);
        assertThat(msm, is(""+4));
    }


    @After
    public void tearDown() throws Exception {
        fizzBuzz = null;
    }
}