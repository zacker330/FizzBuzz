package com.thoughtworks.FizzBuzzWhizz;

import org.junit.Test;

/**
 * User: zjzhai
 * Date: 4/29/14
 */
public class NumberWordMapTest {
    @Test
    public void testMain() throws Exception {

        NumberWordMap numberWordMap = new NumberWordMap().put(3, "Fizz").put(5, "Buzz").put(7, "Whizz");

        assert !numberWordMap.isEmpty();

        assert numberWordMap.allNumber().get(0) == 3;
        assert numberWordMap.allNumber().get(1) == 5;
        assert numberWordMap.allNumber().get(2) == 7;

        assert "Fizz".equals(numberWordMap.get(3));
        assert "Buzz".equals(numberWordMap.get(5));
        assert "Whizz".equals(numberWordMap.get(7));

        assert numberWordMap.getFirstNumber() == 3;


        assert numberWordMap.isContainsFirstSpecialNumber(13);
        assert !numberWordMap.isContainsFirstSpecialNumber(22);

        assert "Fizz".equals(numberWordMap.getFirstWord());
    }


    @Test(expected = SpecialNumberIllegalException.class)
    public void testAddGe10Number() {
        NumberWordMap numberWordMap = new NumberWordMap();
        numberWordMap.put(10, "exception");

    }
}
