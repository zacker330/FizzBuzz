package com.thoughtworks.FizzBuzzWhizz;

import java.util.ArrayList;
import java.util.List;

/**
 * 报告器：拿到数字后计算应该回应什么声音，是数字，还是拼音
 * User: zjzhai
 * Date: 4/29/14
 */
public class Reporter {

    public static ReportResult report(int number, NumberWordMap numberWordMap) {

        // number 包含第一位特殊数
        if (numberWordMap.isContainsFirstSpecialNumber(number)) {
            return new ReportResult(number, numberWordMap.getFirstWord());
        }

        // 用于为number的倍数的特殊数
        List<Integer> multiples = new ArrayList<Integer>();

        for (Integer eachSpecialNumber : numberWordMap.allNumber()) {
            if (number % eachSpecialNumber == 0) {
                multiples.add(eachSpecialNumber);
            }
        }

        //不是任何特殊数的倍数
        if (multiples.isEmpty()) return ReportResult.create(number);

        return ReportResult.create(numberWordMap, multiples, number);
    }

}
