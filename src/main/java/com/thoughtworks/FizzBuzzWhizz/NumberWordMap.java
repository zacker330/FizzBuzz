package com.thoughtworks.FizzBuzzWhizz;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 特殊数字与单词之间的映射
 * User: zjzhai
 * Date: 4/29/14
 */
public class NumberWordMap {

    public static int MAX_NUMBER = 9;

    public static int MIN_NUMBER = 1;

    private LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();

    /**
     * @param specialNumber
     * @param word
     * @return
     * @throws SpecialNumberIllegalException
     */
    public NumberWordMap put(int specialNumber, String word) {
        if (specialNumber < MIN_NUMBER || specialNumber > MAX_NUMBER)
            throw new SpecialNumberIllegalException("number will be one of " + MIN_NUMBER + "..." + MAX_NUMBER + " integer");
        map.put(specialNumber, word);
        return this;
    }

    public boolean isContainsFirstSpecialNumber(int number) {
        return (number + "").contains(getFirstNumber() + "");
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public List<Integer> allNumber() {
        List<Integer> result = new ArrayList<Integer>();
        result.addAll(map.keySet());
        return result;
    }


    public String get(int number) {
        return map.get(number);
    }

    public int getFirstNumber() {
        return map.keySet().iterator().next();
    }

    public String getFirstWord() {
        return map.get(getFirstNumber());
    }
}
