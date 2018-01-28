package by.belash.evgeny;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

//1 этап: решение тестовых задач (579, 670, 278, 557) http://acmp.ru/index.asp?main=tasks
public class Main {

    public static void main(String[] args) {
        int attempt = 0;
        int taskNum  = 0;

        String successMessage = "Программа выполнена без ошибок";
        String errorMessage  = "Программа выполнена с ошибкой";

        while (attempt == 0) {
            System.out.println("Выберите задание: 278, 557, 579, 670");
            try {
                Scanner in = new Scanner(System.in);
                taskNum = in.nextInt();
            }catch (Exception e){

            }

            if (taskNum == 278) {
                try {
                    task278();
                    attempt++;
                    System.out.println(successMessage);
                }catch (Exception e){
                    System.out.println(errorMessage);
                    e.printStackTrace();
                }
            } else if (taskNum == 557) {
                try {
                    task557();
                    attempt++;
                    System.out.println(successMessage);
                }catch (Exception e){
                    System.out.println(errorMessage);
                    e.printStackTrace();
                }
            } else if (taskNum == 579) {
                try {
                    task579();
                    attempt++;
                    System.out.println(successMessage);
                }catch (Exception e){
                    System.out.println(errorMessage);
                    e.printStackTrace();
                }
            } else if (taskNum == 670) {
                try {
                    task670();
                    attempt++;
                    System.out.println(successMessage);
                }catch (Exception e){
                    System.out.println(errorMessage);
                    e.printStackTrace();
                }
            } else {
                System.out.println("Вы ввели неверные данные, повторите ввод");
            }
        }

    }

    private static void task557() {
        String input = "src\\files\\task557\\INPUT.TXT";
        String output = "src\\files\\task557\\OUTPUT.TXT";

        short m = 0; //кол-во матриц
        short n = 0; //размер матриц

        int row = 0;
        int col = 0;
        int p = 0;

        String line = null;
        ArrayList<Short> arrayOfNumeric = new ArrayList();

        try {
            BufferedReader readFromFile = new BufferedReader(new FileReader(input));
            Object[] st = readFromFile.lines().toArray();

            for (int i = 0; i < st.length; i++) {
                line = String.valueOf(st[i]);
                Scanner scanner = new Scanner(line);
                try {
                    if (line.length() > 0) {
                        while (scanner.hasNext()) {
                            arrayOfNumeric.add(scanner.nextShort());
                        }
                        scanner.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            m = arrayOfNumeric.get(0);
            arrayOfNumeric.remove(0);
            n = arrayOfNumeric.get(0);
            arrayOfNumeric.remove(0);
            row = arrayOfNumeric.get(0) - 1;
            arrayOfNumeric.remove(0);
            col = arrayOfNumeric.get(0) - 1;
            arrayOfNumeric.remove(0);
            p = arrayOfNumeric.get(0);
            arrayOfNumeric.remove(0);

            readFromFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<short[][]> matrix = new ArrayList();

        byte k = 0;
        while (k < m) {
            short[][] array = new short[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    array[i][j] = arrayOfNumeric.get(0);
                    arrayOfNumeric.remove(0);
                }
            }
            matrix.add(array);
            k++;
        }

        short[][] res = umnMatr557(matrix.get(0), matrix.get(1));

        if (m > 2) {
            matrix.remove(0);
            matrix.remove(0);
            for (short count = 2; count < m; count++) {
                res = umnMatr557(res, matrix.get(0));
                matrix.remove(0);
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            writer.write(String.valueOf(res[row][col]));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static short[][] umnMatr557(short[][] mA, short[][] mB){

        short m = (short) mA.length;
        short n = (short) mB[0].length;
        short o = (short) mB.length;
        short[][] result = new short[m][n];

        for (short i = 0; i < m; i++) {
            for (short j = 0; j < n; j++) {
                for (short k = 0; k < o; k++) {
                    result[i][j] += mA[i][k] * mB[k][j];
                }
            }
        }
        return result;
    }


    private static void task278(){
        String input = "src\\files\\task278\\INPUT.TXT";
        String output = "src\\files\\task278\\OUTPUT.TXT";

        String s = null;
        String t = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8))){
            s = reader.readLine();
            t = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        char[] arrS = s.toCharArray();
        char[] arrT = t.toCharArray();
        int x = t.indexOf(arrS[0]);
        if (x < 0){
            System.out.println("NO");
        }else {
            int i = 1;
            for (int j = x + 1; j < arrT.length; j++) {
                if (arrS[i] == arrT[j]) {
                    i++;
                }
                if (i == arrS.length) {
                    break;
                }
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(output));
                if (i == arrS.length) {
                    writer.write("YES");
                } else writer.write("NO");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void task579() {
        String input = "src\\files\\task579\\INPUT.TXT";
        String output = "src\\files\\task579\\OUTPUT.TXT";

        short n = 0;
        short[] array;
        String str = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            n = Short.parseShort(reader.readLine());
            str = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        array = new short[n];
        Scanner scanner = new Scanner(str);
        for (short i = 0; i < n; i++) {
            array[i] = scanner.nextShort();
        }
        scanner.close();

        int sumPol = 0;
        int sumOtr = 0;
        int countPol = 0;
        int countOrt = 0;

        for (short i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                sumPol = sumPol + array[i];
                countPol++;
            } else {
                sumOtr = sumOtr + array[i];
                countOrt++;
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            if (sumPol >= Math.abs(sumOtr)) {
                writer.write(String.valueOf(countPol));
                writer.write(System.lineSeparator());
                for (short i = 0; i < array.length; i++) {
                    if (array[i] >= 0) {
                        writer.write(i + 1 + " ");
                    }
                }
            } else {
                writer.write(String.valueOf(countOrt));
                writer.write(System.lineSeparator());
                for (short i = 0; i < array.length; i++) {
                    if (array[i] < 0) {
                        writer.write(i + 1 + " ");
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void task670() {
        String input = "src\\files\\task670\\INPUT.TXT";
        String output = "src\\files\\task670\\OUTPUT.TXT";

        int N = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            N = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int arr[] = new int[N];
        int p = 1;

        for (int i = 0; i < N; i++) {
            if (check670(p)) {
                arr[i] = p;
                p++;
            } else {
                while (!check670(p)) {
                    p++;
                }
                arr[i] = p;
                p++;
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            writer.write(String.valueOf(arr[N - 1]));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean check670(int p) {
        short count = 0;
        char[] charArray = String.valueOf(p).toCharArray();
        short capacity = (short) String.valueOf(p).toCharArray().length;

        for (short i = 0; i < charArray.length; i++) {
            for (short j = 0; j < charArray.length; j++) {
                if (charArray[i] == charArray[j]) {
                    count++;
                }
            }
        }

        if (count == capacity)
            return true;
        else return false;
    }
}

