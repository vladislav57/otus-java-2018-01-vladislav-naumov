package ru.otus.hw3;

import java.util.*;
import java.util.function.Consumer;

public class MyArrayList<T> implements List<T> {
    private final static int defaultSize = 10;
    private int maxSize;
    private int currentSize;
    private Object[] array;

    public MyArrayList() {
        init();
    }

    private void init() {
        this.maxSize = defaultSize;
        this.currentSize = 0;
        this.array = new Object[maxSize];
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    @Override
    public boolean contains(Object o) {
        return (find(o) >= 0);
    }

    private int find(Object o) {
        if(o == null) {
            for(int i=0; i<currentSize; i++) {
                if(array[i] == null)
                    return i;
            }
            return -1;
        } else {
            for(int i=0; i<currentSize; i++) {
                if(o.equals(array[i]))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator(this);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, currentSize);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size())
            return (T1[]) Arrays.copyOf(array, size(), a.getClass());
        System.arraycopy(array, 0, a, 0, size());
        if (a.length > size())
            a[size()] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        if(currentSize < maxSize) {
            array[currentSize] = t;
            currentSize++;
        } else {
            maxSize *= 2;
            Object[] newArray = new Object[maxSize];
            System.arraycopy(array, 0, newArray, 0, currentSize);
            array[currentSize] = t;
            currentSize++;
            array = newArray;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(isEmpty())
            return false;
        int place = find(o);
        if(place < 0)
            return false;
        if(place < currentSize-1) {
            System.arraycopy(array, place + 1, array, place, currentSize - place - 1);
        }
        array[currentSize] = null;
        currentSize--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o: c) {
            if( !contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(Object o: c) {
            add((T) o);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] temp = new Object[c.size()];
        Iterator iter = c.iterator();
        for(int i=0; i<c.size() && iter.hasNext(); i++) {
            temp[i] = iter.next();
        }
        if(currentSize + c.size() > maxSize) {
            maxSize += c.size();
        }
        Object[] newArray = new Object[maxSize];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(temp, 0, newArray, index, c.size());
        System.arraycopy(array, index, newArray, index + c.size(), currentSize - index);
        currentSize += c.size();
        array = newArray;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object o: c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] newArray = new Object[maxSize];
        int counter = 0;
        for(Object o: c) {
            if(this.contains(o)) {
                newArray[counter] = o;
                counter++;
            }
        }
        array = newArray;
        currentSize = counter;
        return true;
    }

    @Override
    public void clear() {
        init();
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= currentSize)
            throw new RuntimeException("index out of bound exception");
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        if(index < 0 || index > currentSize-1)
            throw new RuntimeException("index out of bound exception");
        array[index] = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        this.add(null);
        System.arraycopy(array, index, array, index+1, currentSize-index-1);
        array[index] = element;
    }

    @Override
    public T remove(int index) {
        T element = get(index);
        System.arraycopy(array, index+1, array, index, currentSize-index-1);
        currentSize--;
        array[currentSize] = null;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        return find(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        int counter = -1;
        if(o == null) {
            for(int i=0; i<currentSize; i++) {
                if(array[i] == null)
                    counter = i;
            }
            return counter;
        } else {
            for(int i=0; i<currentSize; i++) {
                if(o.equals(array[i]))
                    counter = i;
            }
        }
        return counter;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyArrayListListIterator(this);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new MyArrayListListIterator(this, index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new RuntimeException("should implement this method as well");
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int index;
        private MyArrayList<T> myArrayList;

        public MyArrayListIterator(MyArrayList<T> myArrayList) {
            index = 0;
            this.myArrayList = myArrayList;
        }

        @Override
        public boolean hasNext() {
            return (index < myArrayList.size());
        }

        @Override
        public T next() {
            if(index < myArrayList.size()) {
                T result = myArrayList.get(index);
                index++;
                return result;
            } else {
                throw new RuntimeException("Concurrent modification exception");
            }
        }

        @Override
        public void remove() {
            if(index < myArrayList.size() && index > -1) {
                myArrayList.remove(index);
                index--;
            } else {
                throw new RuntimeException("Illegal state exception");
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            for(;index < myArrayList.size(); index++) {
                action.accept(myArrayList.get(index));
            }
        }
    }

    private class MyArrayListListIterator implements ListIterator<T> {
        int index;
        int fromIndex;
        int toIndex;
        MyArrayList<T> list;

        public void Construct(MyArrayList<T> list, int fromIndex, int toIndex) {
            this.list = list;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            index = fromIndex;
        }

        public MyArrayListListIterator(MyArrayList<T> list, int fromIndex, int toIndex) {
            Construct(list, fromIndex, toIndex);
        }

        public MyArrayListListIterator(MyArrayList<T> list, int fromIndex) {
            Construct(list, fromIndex, list.size());
        }

        public MyArrayListListIterator(MyArrayList<T> list) {
            Construct(list, 0, list.size());
        }

        @Override
        public boolean hasNext() {
            return (index < toIndex);
        }

        @Override
        public T next() {
            if(index < toIndex || index >= fromIndex) {
                T result = list.get(index);
                index++;
                return result;
            } else {
                throw new RuntimeException("Concurrent modification exception");
            }
        }

        @Override
        public boolean hasPrevious() {
            return (index > fromIndex);
        }

        @Override
        public T previous() {
            if(index > fromIndex && index < toIndex) {
                return list.get(index - 1);
            } else {
                throw new RuntimeException("inconsistent state exception");
            }
        }

        @Override
        public int nextIndex() {
            if(index < toIndex) {
                return index + 1;
            } else {
                throw new RuntimeException("no next index exception");
            }
        }

        @Override
        public int previousIndex() {
            if(index > fromIndex && index < toIndex) {
                return index - 1;
            } else {
                throw new RuntimeException("no previous index exception");
            }
        }

        @Override
        public void remove() {
            list.remove(index);
            index--;
            toIndex--;
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            for(index = fromIndex; index < toIndex; index++) {
                action.accept(list.get(index));
            }
        }

        @Override
        public void set(T t) {
            list.set(index-1, t);
        }

        @Override
        public void add(T t) {
            list.add(index, t);
            toIndex++;
            index++;
        }
    }

}
