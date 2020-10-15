public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int front;
    private int back;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        back = 0;
    }

    public void addFirst(T item) {
        items[items.length - 1 - back] = item;
        size++;
        back++;
        this.resize();
    }

    public void addLast(T item) {
        items[front] = item;
        size++;
        front++;
        System.out.println(size);
        this.resize();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int backIdx = back;
        while (backIdx > 0) {
            System.out.print(items[items.length - backIdx]);
            System.out.print(" ");
            backIdx--;
        }
        int frontIdx = 0;
        while (frontIdx < front) {
            System.out.print(items[frontIdx]);
            System.out.print(" ");
            frontIdx++;
        }
    }

    public T removeFirst() {
        back--;
        size--;
        this.resize();
        return items[items.length - back - 1];
    }

    public T removeLast() {
        front--;
        size--;
        this.resize();
        return items[front + 1];
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[index - back];
    }

    public void reduceSize() {
        T[] a = (T[]) new Object[items.length / 2];
        for (int i = back; i >= 0; i--) {
            a[a.length - i] = items[items.length - i];
        }
        for (int i = 0; i < front; i++) {
            a[i] = items[i];
        }
        items = a;
    }

    public void increaseSize() {
        T[] a = (T[]) new Object[items.length * 2];
        for (int i = back; i > 0; i--) {
            a[a.length - i] = items[items.length - i];
        }
        for (int i = 0; i < front; i++) {
            a[i] = items[i];
        }
        items = a;
    }

    public void resize() {
        if (items.length >= 16 && size < items.length / 4) {
            this.reduceSize();
        }
        if (size == items.length / 2) {
            this.increaseSize();
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arr1 = new ArrayDeque<Integer>();
        arr1.addFirst(1);
        arr1.addLast(19);
        arr1.addFirst(3);
        arr1.addLast(23);
        arr1.addFirst(1);
        arr1.addLast(19);
        arr1.addFirst(3);
        arr1.addLast(23);
        arr1.addFirst(1);
        arr1.addLast(19);
        arr1.addFirst(3);
        arr1.addLast(23);
        arr1.addFirst(1);
        arr1.addLast(19);
        arr1.addFirst(3);
        arr1.addLast(23);
        arr1.addFirst(1);
        arr1.addLast(19);
        arr1.addFirst(3);
        arr1.addLast(23);
        arr1.addFirst(1);
        arr1.addLast(19);
        arr1.addFirst(3);
        arr1.addLast(23);
        arr1.printDeque();
    }
}
