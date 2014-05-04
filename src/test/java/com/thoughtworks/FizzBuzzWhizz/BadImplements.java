package com.thoughtworks.FizzBuzzWhizz;

/**
 * User: zjzhai
 * Date: 5/2/14
 */
public class BadImplements {

    public static void main(String[] args) throws Exception {
        report(3, 5, 7);
    }

    public static void report(int s1, int s2, int s3) {
        for (int i = 1; i <= 100; i++) {

            //如果包含了第一个特殊数，则报第一个单词
            if (("" + i).contains(("" + s1))) {
                System.out.println("Fizz");
                continue;
            }

            StringBuilder resultBuilder = new StringBuilder();

            if (i % s1 == 0) {
                resultBuilder.append("Fizz");
            }

            if (i % s2 == 0) {
                resultBuilder.append("Buzz");
            }

            if (i % s3 == 0) {
                resultBuilder.append("Whizz");
            }

            //不是任何特殊的倍数
            if ("".equals(resultBuilder.toString().trim())) {
                System.out.println(i);
                continue;
            }

            System.out.println(resultBuilder.toString());
        }
    }
}
