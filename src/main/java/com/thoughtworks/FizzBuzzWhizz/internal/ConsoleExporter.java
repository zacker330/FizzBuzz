package com.thoughtworks.FizzBuzzWhizz.internal;

import com.thoughtworks.FizzBuzzWhizz.Exporter;
import com.thoughtworks.FizzBuzzWhizz.ReportResult;

/**
 * User: zjzhai
 * Date: 4/29/14
 */
public class ConsoleExporter implements Exporter {
    @Override
    public void export(ReportResult result) {
        System.out.println(result.getWord());
    }
}
