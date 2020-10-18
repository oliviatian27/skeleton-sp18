public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    private int minusOne(int nextFirst){
        if(nextFirst==0){
            return items.length-1;
        }else{
            return nextFirst-1;
        }
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        nextFirst=minusOne(nextFirst);
        this.resize();
    }

    private int plusOne(int nextLast){
        return (nextLast+1)% items.length;
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size++;
        nextLast=plusOne(nextLast);
        this.resize();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
       int i=0;
       while(i<size){
           System.out.print(get(i));
           System.out.print(" ");
           i++;
       }
    }

    public T removeFirst() {
        nextFirst=plusOne(nextFirst);
        T f =items[nextFirst];
        items[nextFirst]=null;
        size--;
        this.resize();
        return f;
    }

    public T removeLast() {
        nextLast=plusOne(nextLast);
        T l=items[nextLast];
        items[nextLast]=null;
        size--;
        this.resize();
        return l;
    }

    public T get(int index) {
        if (size==0) {
            return null;
        }
        return items[(index+plusOne(nextFirst))% items.length];
    }


    private void resizeHelper(int newLen) {
        T[] a = (T[]) new Object[newLen];
        for (int i = 0; i <size ; i++) {
            a[i] = get(i);
        }
        items = a;
        nextFirst= items.length-1;
        nextLast=size;
    }

    private void resize() {
        if (items.length >= 16 && size < (items.length / 4)) {
            this.resizeHelper(items.length/2);
        }
        if (size == items.length ) {
            this.resizeHelper(items.length*2);
        }
    }
    
}
