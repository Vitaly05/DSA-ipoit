package by.it.group410972.loziuk.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result = 0;

        int[] maxSubsequenceLengths  = new int[n];
        int[] prev = new int[n];

        Arrays.fill(prev, -1);

        List<Integer> lastElements = new ArrayList<>();
        List<Integer> elementIndices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int pos = binarySearch(lastElements, m[i]);
            pos = (pos < 0) ? -pos - 1 : pos;

            if (pos < lastElements.size()) {
                lastElements.set(pos, m[i]);
                elementIndices.set(pos, i);
            } else {
                lastElements.add(m[i]);
                elementIndices.add(i);
            }

            maxSubsequenceLengths [i] = pos + 1;

            if (pos > 0) {
                prev[i] = elementIndices.get(pos - 1);
            }
        }

        result = lastElements.size();

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size();

        while (left < right) {
            int middle = (left + right) / 2;

            if (list.get(middle) >= target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }
}