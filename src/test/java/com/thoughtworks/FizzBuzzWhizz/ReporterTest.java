package com.thoughtworks.FizzBuzzWhizz;

import org.junit.Test;

/**
 * User: zjzhai
 * Date: 4/29/14
 */
public class ReporterTest {

    @Test
    public void testSound() throws Exception {
        NumberWordMap map = new NumberWordMap().put(3, "Fizz").put(4, "Kuzz").put(5, "Buzz").put(7, "Whizz");

        // 60是3,4,5的倍数
        ReportResult result = Reporter.report(60, map);
        assert "FizzKuzzBuzz".equals(result.getWord());

        assert "Fizz".equals(Reporter.report(13, map).getWord());

    }
}
