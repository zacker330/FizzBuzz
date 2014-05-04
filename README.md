FizzBuzzWhizz
---

### 题记

ThoughtWorks这次招人似乎有些狠。除了在微博上下大功夫，还和拉勾网、OSC合作。招人的方式比较特别，先交代码，才有机会得到面试电话。我想他们的嗅觉应该很灵敏。哈。代码臭味过不了他们的鼻子。

我比较喜欢参加比赛，所以，怎么会落下这次机会。以下博客是我尝试阐述我的分析与设计。

使用文字将分析设计的过程说明清楚是一件很困难的事情。因为大脑运转的速度实在太快，一边动脑，一边如实地将大脑所想记录下来，我觉得是非常非常困难的。所以，我不理解意识流的文学作品到底是怎么写出来的。

所以，我采用另一种方式来说明我的分析设计过程：对比法。就是拿一个`较差`的实现与我觉得`还可以`的实现进行对比。

我假设大家都已经看过题目，如果没有看到题目，请点[这里](https://www.jinshuju.net/f/EGQL3D)。

### 第一种实现

这是我自己特意写的比较差的实现，如有雷同属巧合：


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


因为如果符合第五条规则，第三、四条规则都会失效，所以，符合第五条规则后，本次循环直接返回。第三、四条规则使用3个`if`也实现了。
咋一看，没问题，运行起来，也没问题，而且，也没有任何多余的代码，看似也有些小优雅。

唯一不足可能就是没有提供用户接口（命令行输入或界面等），实事上，我觉得这部分相对游戏的核心业务逻辑不重要。

但是，我觉得是有问题的，问题出现在哪呢？

当报数的学生从**100**变成**500**呢？

这个问题倒还容易解决：

          public static void report(int s1, int s2, int s3, int startNumber, int endNumber) {
                             for (int i = startNumber; i <= endNumber; i++) {

这样写，同样是可以运行的，但带来了新的问题。当然，你也可以认为没有问题。

我认为的新问题是：`report`参数过多。当你站在一个代码维护者的角度来看：`report(2,6,9,1,700);`，我相信你是这种表情：

![猫玩魔方](http://news.lifeyoyo.com/attachments/2011/08/1447_201108041724311vAR3.jpg)

这个问题，还算是小问题，当客户提出，他们想允许用户输入9个特殊数，特殊数对应的单词也都变了，你可能这种表情：

![看别人的代码](http://ww4.sinaimg.cn/bmiddle/7cff6573jw1efla4xsjvag205v04j7wi.gif)

你好不容易为`report`加上多出的特殊数参数，调用的时候就变成了：`report(1,2,3,4,5,6,7,8,1,999);`。这下，我很难想像代码维护者的表情了。

某天，客户又提出，希望报告结果能输出到数据库中时，你可能就要崩溃了。


以上是有夸张的虚构的因素在里面，毕竟这是一个小小的测试题，没那么严重。你可以把`report`设计成：`report(int startNumber,int endNumber, int... specialNumber)`。
这样就可以适应客户提出特殊数方面的所有需求了，如特殊数的个数变了等。

但是在大型的业务项目里，这一点不夸张。客户的需求总是不那么清晰，看看下面这图，你就明白了：

![](https://www.google.com.hk/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&docid=_Mo4YbQrwhizmM&tbnid=y5wzomPL9RRv4M:&ved=0CAUQjRw&url=%68%74%74%70%3a%2f%2f%77%77%77%2e%63%6e%62%6c%6f%67%73%2e%63%6f%6d%2f%76%6c%6e%6b%2f%61%72%63%68%69%76%65%2f%32%30%31%32%2f%31%31%2f%31%34%2f%32%37%37%30%31%33%39%2e%68%74%6d%6c&ei=ZJNlU4SXF42C8gXFl4Fg&psig=AFQjCNEiw-GSB2TiWKxAAmKa2n8C6W75JA&ust=1399252189681218)

`温伯格的《探索需求》的书的插图`

如果你经历过大型项目，不用看图，你就理解我的意思了。

### 第二种设计

ThoughtWorks的打擂题，当然不只是想看到一个**刚刚能运行**的程序，我想他们希望能看到你在分析、设计、写代码上的功底。但是，猜我都猜到一定有
人会卖弄各种**设计模式**，我觉得这样的代码更恐怖！

解决一个问题的时候，我习惯性地会去分析它的本质，也就是核心问题在哪里？

经分析，它核心问题在于当你得到一个数字后，你需要根据一定的规则来决定，你是应该是报数字，还是单词。因为，报告的导出方式（是打印在命令行，还是持久化到数据库中）、
特殊数（除了3,5,7还可以是其它个位数，如2,4）、特殊数的个数（现在是3个，但客户可能要求是可变的）等这些是可变的。只有规则本身是不变的。如果连规则都变了，那就是另一个游戏了。

打个比方，麻将分为四川麻将和贵州麻将，它们主要规则是不变的，只是某些小规则不同，但是它们还都是麻将！如果主要规则都变了，那么，我们就可以确定地说，这不是麻将。

这个游戏的主要规则就是得到数字，根据3条规则来决定是报单词还是数字。

我把这个核心放在Reporter类：

        public class Reporter {

            // number为接收到的数字，在本题中则是1到100之间的整数
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

`NumberWordMap`是用来定义特殊数字与单词之间的映射，如题目中，“3”对应“Fizz”，“5”对应“Buzz”。为什么我不直接使用LinkedHashMap呢？
而是另外自己再建立一个抽象数据结构呢？很明显，Java的纯Map类的表达力在这个问题上表达力不够。


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
            .
            .
            .
            .

        }



你会看到，我返回的是一个`ReportResult`类，而不是一个`String`，这是考虑到用户（扩展者）可能不只是用到"1"，"Fizz"，“Buzz”，这样字符串。所以ReportResult保存的是
原始数字及相应的报告，如本题中ReportResult会同时保存21和FizzWhizz。

这样，用户通过实现`导出接口Exporter`的方法`export(ReportResult result)`来实现自定义导出逻辑，如我默认提供了命令行导出器：

        package com.thoughtworks.FizzBuzzWhizz.internal;

        public class ConsoleExporter implements Exporter {
            @Override
            public void export(ReportResult result) {
                System.out.println(result.getWord());
            }
        }


相信读者有些头晕了，现在我们来最终主程吧：

        NumberWordMap map = new NumberWordMap().put(3, "Fizz").put(5, "Buzz").put(7, "Whizz");

        Game game = new Game(map, 1, 100);

        game.exportTo(new ConsoleExporter()).start();


一看这代码，大概就明白我的意思了吧。我们再看看Game类的`start`方法：

        public void start() {
            for (int i = startNumber; i <= endNumber; i++) {
                exporter.export(Reporter.report(i, numberWordMap));
            }
        }


这下，大家应该明白我的设计了。这样的设计比起第一种实现，还有更大的优点：易于测试！

这就是我的最终设计。需要说明的是设计的时候，常常要权衡的是复杂性和灵活性。过大的追求灵活性，常常会带来更多的复杂性，所以，我们要在
保证不增加复杂性的同时增加灵活性。

对比第一种设计，第二种设计为了灵活性，增加了一些复杂性。事实上，如果只是这么一个简单的游戏，不是用于真正商业的，完全没有必要，甚至有些过度设计。
既然是打擂，当然要写得好一些。虽然这只是玩具程序。

你也注意到了，全文下来，我没有对我的代码提设计模式，因为，我不喜欢谈设计模式。设计模式是解决某类问题的套路。
当你把问题的本质看清楚了，设计模式就在那里了。


### 最后

今天已经是截止交作业的时间，你交了没有？我也希望看看大家的代码，共同学习!

> 以上的图片源自网络，如果有版权问题，请联系我。


  [1]: http://static.oschina.net/uploads/space/2014/0503/234707_Rt6p_181141.jpg
  [2]: http://ww3.sinaimg.cn/mw690/8cdebe44gw1efmgas3wd3g205v04j4qp.gif
  [3]: http://static.oschina.net/uploads/space/2014/0504/000138_sbHS_181141.jpg