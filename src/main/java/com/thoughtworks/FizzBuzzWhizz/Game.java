package com.thoughtworks.FizzBuzzWhizz;

import com.thoughtworks.FizzBuzzWhizz.internal.ConsoleExporter;

/**
 * 游戏主程
 * User: zjzhai
 * Date: 4/29/14
 */
public class Game {

    private NumberWordMap numberWordMap;

    /**
     * 结果导出器
     */
    private Exporter exporter = new ConsoleExporter();

    private int startNumber;

    private int endNumber;

    public Game(final NumberWordMap numberWordMap, int startNumber, int endNumber) {
        assert startNumber < endNumber && startNumber > 0 && endNumber > 0
                && numberWordMap != null && !numberWordMap.isEmpty();

        this.numberWordMap = numberWordMap;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    public Game exportTo(Exporter exporter) {
        assert exporter != null;
        this.exporter = exporter;
        return this;
    }


    public void start() {
        for (int i = startNumber; i <= endNumber; i++) {
            exporter.export(Reporter.report(i, numberWordMap));
        }
    }

}
