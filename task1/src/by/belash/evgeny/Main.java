package by.belash.evgeny;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//1 этап: решение тестовых задач (579, 670, 278, 557) http://acmp.ru/index.asp?main=tasks
public class Main {

    public static void main(String[] args) {
        int attempt = 0;
        int taskNum = 0;

        String successMessage = "Программа выполнена без ошибок";
        String errorMessage = "Программа выполнена с ошибкой";

        while (attempt == 0) {
            System.out.println("Выберите задание: 278, 557, 5572, 579, 670");
            try {
                Scanner in = new Scanner(System.in);
                taskNum = in.nextInt();
            } catch (Exception e) {

            }

            if (taskNum == 278) {
                try {
                    task278();
                    attempt++;
                    System.out.println(successMessage);
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    e.printStackTrace();
                }
            } else if (taskNum == 557) {
                try {
                    task557();
                    attempt++;
                    System.out.println(successMessage);
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    e.printStackTrace();
                }
            } else if (taskNum == 5572) {
                try {
                    task557b();
                    attempt++;
                    System.out.println(successMessage);
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    e.printStackTrace();
                }
            } else if (taskNum == 579) {
                try {
                    task579();
                    attempt++;
                    System.out.println(successMessage);
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    e.printStackTrace();
                }
            } else if (taskNum == 670) {
                try {
                    task670();
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

    //Второй вариант решения, через одномерный массив
    private static void task557b() throws FileNotFoundException {
        String input = "src\\files\\task557\\INPUT.TXT";
        String output = "src\\files\\task557\\OUTPUT.TXT";

        Scanner sc = new Scanner(new File(input));

        final int m = sc.nextInt();  // количество матриц
        final int n = sc.nextInt();  // размер матриц
        final int line = sc.nextInt() - 1;
        final int column = sc.nextInt() - 1;
        final int p = sc.nextInt();

        int[] c;
        int[][] allMatrix = new int[m][n * n];

        for (int j = 0; j < m; j++) {
            for (int k = 0; k < n * n; k++) {
                allMatrix[j][k] = sc.nextInt();
            }
        }

        sc.close();

        c = allMatrix[0];
        for (int i = 1; i < m; i++) {
            c = mult(c, allMatrix[i], p, n * n, n);
        }
        PrintWriter writer = new PrintWriter(output);
        writer.print(c[line + column]);
        writer.close();
    }

    private static int[] mult(int[] a, int[] b, int p, int n, int N) {
        int result;
        int[] c = new int[n];

        for (int i = 0; i < n; ) {
            int e = 0;
            int d = 0;
            for (int j = 0; j < n; j++) {
                result = 0;
                int countA = 0;
                int countB = 0;
                for (int k = 0; k < N; k++) {
                    result += a[e + countA] * b[d + countB];
                    countA++;
                    countB += N;
                }
                if (result >= p) {
                    result %= p;
                }
                c[i] = result;
                i++;
                d++;
                if (i == N) {
                    e += N;
                    d = 0;
                }
            }
        }
        return c;
    }

    //Обычное перемножение матриц
    private static void task557() throws FileNotFoundException {
        String input = "src\\files\\task557\\INPUT.TXT";
        String output = "src\\files\\task557\\OUTPUT.TXT";

        Scanner sc = new Scanner(new File(input));

        final int m = sc.nextInt();  // количество матриц
        final int n = sc.nextInt();  // размер матриц
        final int line = sc.nextInt() - 1;
        final int column = sc.nextInt() - 1;
        final int p = sc.nextInt();

        int[][] c;
        int[][][] allMatrix = new int[m][n][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    allMatrix[i][j][k] = sc.nextInt();
                }
            }
        }
        sc.close();

        c = allMatrix[0];

        for (int i = 1; i < m; i++) {
            c = umnMatr557(c, allMatrix[i], p, n);
        }
        PrintWriter writer = new PrintWriter(output);
        writer.print(c[line][column]);
        writer.close();
    }

    public static int[][] umnMatr557(int[][] a, int[][] b, int p, int n) {
        int result;
        int[][] c = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = 0;
                for (int k = 0; k < n; k++) {
                    result += a[i][k] * b[k][j];
                }
                if (result >= p) {
                    result %= p;
                }
                c[i][j] = result;
            }
        }
        return c;
    }


    private static void task278() throws FileNotFoundException {
        String input = "src\\files\\task278\\INPUT.TXT";
        String output = "src\\files\\task278\\OUTPUT.TXT";

        String s = null;
        String t = null;

        char[] arrS = null;
        char[] arrT = null;
        PrintWriter writer = new PrintWriter(new File((output)));
        try {
            Scanner sc = new Scanner(new File(input));
            if (sc.hasNext()) {
                s = sc.next().toUpperCase();
                arrS = s.toCharArray();
            }
            if (sc.hasNext()) {
                t = sc.next().toUpperCase();
                arrT = t.toCharArray();
            }
            sc.close();
        } catch (IOException p) {
            writer.print("NO");
            writer.close();
        }


        try {
            if (isIn(arrS, arrT, s, t) & t.length() > 0 & s.length() > 0) {
                writer.print("YES");
                writer.close();
            } else {
                writer.print("NO");
                writer.close();
            }
        } catch (Exception e) {
            writer.print("NO");
            writer.close();
        }
    }

    public static boolean isIn(char[] arrS, char[] arrT, String s, String t) {
        int x = -1;
        if (t.length() > 0 & s.length() > 0 & arrS.length > 0 & arrT.length > 0) {
            x = t.indexOf(arrS[0]);
        }

        if (x >= 0) {
            int z = 1;
            for (int j = x + 1; j < arrT.length; j++) {
                if (arrS[z] == arrT[j]) {
                    z++;
                }
                if (z == arrS.length) {
                    return true;
                }
            }
        }
        return false;
    }


    private static void task579() {
        String input = "src\\files\\task579\\INPUT.TXT";
        String output = "src\\files\\task579\\OUTPUT.TXT";

        int n = 0;
        int[] array;

        Scanner reader = null;
        try {
            reader = new Scanner(new File(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        n = reader.nextInt();
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = reader.nextInt();
        }
        reader.close();

        int sumPol = 0;
        int sumOtr = 0;
        int countPol = 0;
        int countOrt = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 0) {
                sumPol = sumPol + array[i];
                countPol++;
            } else if (array[i] < 0) {
                sumOtr = sumOtr + array[i];
                countOrt++;
            }
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(output));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> arrayList = new ArrayList();

        if (sumPol >= Math.abs(sumOtr)) {
            writer.print(countPol);
            writer.print(System.lineSeparator());
            for (int i = 0; i < array.length; i++) {
                if (array[i] >= 0) {
                    arrayList.add(i + 1);
                }
            }
        } else {
            writer.print(countOrt);
            writer.write(System.lineSeparator());
            for (int i = 0; i < array.length; i++) {
                if (array[i] < 0) {
                    arrayList.add(i + 1);
                }
            }
        }

        for (int i = 0; i < arrayList.size() - 1; i++) {
            writer.print(arrayList.get(i));
            writer.print(" ");
        }
        writer.print(arrayList.get(arrayList.size() - 1));
        writer.close();
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

