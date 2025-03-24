package by.it.group410972.loziuk.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        quickSort(segments, 0, n - 1);

        for (int i = 0; i < m; i++) {
            result[i] = getSegmentsWithPointCount(segments, points[i]);
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private void quickSort(Segment[] array, int low, int high) {
        while (low < high) {
            if (high - low < 10) {
                insertionSort(array, low, high);

                return;
            }

            int lt = low, gt = high;
            Segment pivot = array[low];
            int i = low + 1;

            while (i <= gt) {
                if (array[i].compareTo(pivot) < 0) {
                    swapElements(array, lt++, i++);
                } else if (array[i].compareTo(pivot) > 0) {
                    swapElements(array, i, gt--);
                } else {
                    i++;
                }
            }

            if (lt - low < high - gt) {
                quickSort(array, low, lt - 1);

                low = gt + 1;
            } else {
                quickSort(array, gt + 1, high);

                high = lt - 1;
            }
        }
    }

    private void insertionSort(Segment[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Segment key = array[i];

            int j = i - 1;

            while (j >= low && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];

                j--;
            }

            array[j + 1] = key;
        }
    }

    private int binarySearch(Segment[] segments, int point) {
        int left = 0;
        int right = segments.length - 1;
        int middle;

        int result = -1;

        while (left <= right) {
            middle = left + (right - left) / 2;

            if (segments[middle].start <= point) {
                result = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return result;
    }

    private int getSegmentsWithPointCount(Segment[] segments, int point) {
        int index = binarySearch(segments, point);

        if (index == -1) {
            return 0;
        }

        int count = 0;

        for (int i = index; i >= 0 && segments[i].start <= point; i--) {
            if (segments[i].stop >= point) {
                count++;
            }
        }

        return count;
    }

    private void swapElements(Segment[] array, int i, int j) {
        Segment temp = array[i];

        array[i] = array[j];
        array[j] = temp;
    }

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков

            if (this.start != o.start) {
                return Integer.compare(this.start, o.start);
            } else {
                return Integer.compare(this.stop, o.stop);
            }
        }
    }
}
