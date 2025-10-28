package by.it.group410972.loziuk.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    private int size;
    private Object[] elements;

    public ListB() {
        int defaultSize = 5;

        this.elements = new Object[defaultSize];
        this.size = 0;
    }

    @Override
    public String toString() {
        if (this.size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < this.size; i++) {
            sb.append(this.elements[i]);

            if (i < this.size - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        this.ensureSize(this.size + 1);

        this.elements[this.size] = e;
        this.size++;

        return true;
    }

    @Override
    public E remove(int index) {
        this.checkIndex(index);

        E removedElement = (E)this.elements[index];

        for (int i = index; i < this.size - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.size--;

        this.elements[this.size] = null;

        return removedElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        this.ensureSize(this.size + 1);

        for (int i = this.size; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }

        this.elements[index] = element;
        this.size++;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (o == null ? this.elements[i] == null : o.equals(this.elements[i])) {
                remove(i);

                return true;
            }
        }

        return false;
    }

    @Override
    public E set(int index, E element) {
        this.checkIndex(index);

        E oleElement = (E)this.elements[index];
        this.elements[index] = element;

        return oleElement;
    }


    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }

        this.size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (o == null ? this.elements[i] == null : o.equals(this.elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public E get(int index) {
        this.checkIndex(index);

        return (E)this.elements[index];
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (o == null ? this.elements[i] == null : o.equals(this.elements[i])) {
                return i;
            }
        }

        return -1;
    }

    private void ensureSize(int minSize) {
        if (minSize > this.elements.length) {
            int newSize = this.elements.length * 2;

            Object[] newElements = new Object[newSize];
            System.arraycopy(this.elements, 0, newElements, 0, this.size);

            this.elements = newElements;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }


    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }


    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
