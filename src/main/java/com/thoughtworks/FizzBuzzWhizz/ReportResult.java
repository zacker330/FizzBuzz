package com.thoughtworks.FizzBuzzWhizz;

import java.util.List;

/**
 * 报告结果
 * User: zjzhai
 * Date: 4/29/14
 */
public class ReportResult {
    private int originNumber;

    private String word;

    public ReportResult(int originNumber, String word) {
        this.originNumber = originNumber;
        this.word = word;
    }

    public int getOriginNumber() {
        return originNumber;
    }

    public String getWord() {
        return word;
    }

    public static ReportResult create(NumberWordMap numberWordMap, List<Integer> multiples, int originNumber) {

        StringBuilder builder = new StringBuilder();
        for (Integer specialNumber : multiples) {
            builder.append(numberWordMap.get(specialNumber));
        }

        return new ReportResult(originNumber, builder.toString());

    }

    @Override
    public String toString() {
        return "ReportResult{" +
                "originNumber=" + originNumber +
                ", word='" + word + '\'' +
                '}';
    }

    public static ReportResult create(int originNumber) {
        return new ReportResult(originNumber, "" + originNumber);
    }
}
