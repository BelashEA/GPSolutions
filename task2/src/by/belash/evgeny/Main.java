package by.belash.evgeny;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        // write your code here
        mainLogic();
    }

    private static void mainLogic() {
        int attempt = 0;
        int taskNum = 0;

        String successMessage = "Программа выполнена без ошибок";
        String errorMessage = "Программа выполнена с ошибкой";

        while (attempt == 0) {
            System.out.println("Выберите задание:");
            System.out.println("1 - Консольное сравнение строк");
            System.out.println("2 - Консольный калькулятор");
            try {
                Scanner in = new Scanner(System.in);
                taskNum = in.nextInt();
            } catch (Exception e) {

            }

            if (taskNum == 1) {
                try {
                    stringFilter();
                    attempt++;
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    e.printStackTrace();
                }
            } else if (taskNum == 2) {
                try {
                    calculator();
                    attempt++;
                    System.out.println(successMessage);
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    e.printStackTrace();
                }

            } else {
                System.out.println("Вы ввели неверные данные, повторите ввод");
            }
        }


    }

    private static void stringFilter() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String lineForTest;

        ArrayList<String> stringArrayList = new ArrayList<>();

        while (in.hasNextLine()) {
            lineForTest = in.nextLine();

            if (lineForTest.length() == 0) {
                break;
            } else {
                if (isStringInLine(str, lineForTest.replace(";", ""))) {
                    stringArrayList.add(lineForTest);
                }
            }
        }

        for (String line : stringArrayList) {
            System.out.println(line);
        }

    }

    private static boolean isStringInLine(String str, String line) {
        String[] strParts = str.split(" ");
        String[] lineParts = line.split(" ");
        int count = 0;

        for (int i = 0; i < strParts.length; i++) {
            for (int j = 0; j < lineParts.length; j++) {
                if (strParts[i].matches((lineParts[j]))) {
                    count++;
                }
            }

        }

        if (count > 0) return true;
        else return false;
    }

    private static void calculator() {

        Scanner in = new Scanner(System.in);
        String stringLine = in.nextLine();

        if (validationString(stringLine)) {
            stringLine = stringLine.replaceAll(" ", "");
            stringLine = stringLine.replaceAll(",", ".");

            stringLine = devide(stringLine);
            String result = powWorker(stringLine);

            if (result == "Infinity") {
                System.out.println("Деление на 0");
            } else {
                System.out.println(result);
            }

        } else {
            System.out.println("Невалидные данные");
        }

    }

    private static boolean validationString(String line) {

        Pattern patternChar = Pattern.compile("[a-zA-Z]");
        Pattern patternSpace = Pattern.compile("\\d\\s\\d");

        Matcher matcherChar = patternChar.matcher(line);
        Matcher matcherSpace = patternSpace.matcher(line);

        if (matcherChar.find() | matcherSpace.find()) {
            return false;
        } else {
            return true;
        }
    }

    private static String devide(String line) {
        String substring;
        int first;
        int second;

        while (line.indexOf("(") >= 0 & line.indexOf(")") >= 0) {
            second = line.indexOf(')') + 1;
            substring = line.substring(0, second);

            first = substring.lastIndexOf('(');
            substring = line.substring(first, second);

            Object result = null;
            String powResult;

            while (substring.indexOf("^") >= 0) {
                powResult = powWorker(substring);
                line = line.replace(substring, powResult.toString());
                substring = powResult;
            }

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");

            try {
                result = engine.eval(substring);
            } catch (ScriptException e) {
                e.printStackTrace();
            }

            line = line.replace(substring, result.toString());
            devide(line);
        }

        return line;
    }

    private static String powWorker(String line) {

        while (line.indexOf("^") >= 0) {
            Pattern pattern = Pattern.compile("\\d+[\\^]+[\\-]\\d+|\\d+[\\^]+\\d+|\\d+\\.\\d+[E]\\d+\\^\\d+");
            Matcher matcher = pattern.matcher(line);
            String substring = "";

            if (matcher.find()) {
                substring = matcher.group();
            }

            String[] strParts = substring.split("\\^");
            BigDecimal bigDecimal1 = new BigDecimal(strParts[0]);
            BigDecimal bigDecimal2 = new BigDecimal(strParts[1]);
            String powResult;

            if (bigDecimal2.toString().indexOf("-") >= 0) {
                String stringBigDecimal2 = bigDecimal2.toString().replace("-", "");
                powResult = "1/" + String.valueOf(bigDecimal1.pow(Integer.parseInt(stringBigDecimal2)));
            } else {
                powResult = String.valueOf(bigDecimal1.pow(Integer.parseInt(bigDecimal2.toString())));
            }

            String stringPow = String.valueOf(powResult);
            line = line.replace(substring, stringPow.toString());
        }

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        int symbolPow = line.indexOf("^");
        if (symbolPow < 0) {
            try {
                line = String.valueOf(engine.eval(line));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }

        return line;
    }
}
