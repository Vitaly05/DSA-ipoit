package by.it.group410972.loziuk.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int firstStringLength = one.length();
        int secondStringLength = two.length();

        Integer[][] memo = new Integer[firstStringLength + 1][secondStringLength + 1];

        int result = recursiveLevenshtein(one, two, firstStringLength, secondStringLength, memo);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int recursiveLevenshtein(String one, String two, int i, int j, Integer[][] memo) {
        if (i == 0) {
            return j;
        }

        if (j == 0) {
            return i;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int cost = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1;

        int deletion = recursiveLevenshtein(one, two, i - 1, j, memo) + 1;
        int insertion = recursiveLevenshtein(one, two, i, j - 1, memo) + 1;
        int substitution = recursiveLevenshtein(one, two, i - 1, j - 1, memo) + cost;

        memo[i][j] = Math.min(deletion, Math.min(insertion, substitution));

        return memo[i][j];
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_EditDist.class.getResourceAsStream("dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
