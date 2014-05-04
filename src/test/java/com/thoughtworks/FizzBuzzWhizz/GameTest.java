package com.thoughtworks.FizzBuzzWhizz;

import com.thoughtworks.FizzBuzzWhizz.internal.ConsoleExporter;
import org.junit.Test;

/**
 * User: zjzhai
 * Date: 4/29/14
 */
public class GameTest {

    @Test
    public void testMain() throws Exception {
        NumberWordMap map = new NumberWordMap().put(3, "Fizz").put(5, "Buzz").put(7, "Whizz");

        Game game = new Game(map, 1, 100);

        game.exportTo(new ConsoleExporter()).start();

    }
}
