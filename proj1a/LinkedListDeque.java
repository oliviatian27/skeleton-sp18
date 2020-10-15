public class LinkedListDeque<T> {
    private final TNode sentinel;
    private int size;

    public class TNode {
        private final T item;
        private TNode prev;
        private TNode next;

        public TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }


    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        TNode oldFront = sentinel.next;
        sentinel.next = new TNode(item, sentinel, oldFront);
        oldFront.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        TNode oldBack = sentinel.prev;
        sentinel.prev = new TNode(item, oldBack, sentinel);
        oldBack.next = sentinel.prev;
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        TNode cur = sentinel.next;
        int i = 0;
        while (i < size) {
            System.out.print(cur.item);
            System.out.print(" ");
            cur = cur.next;
            i++;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            TNode first = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return first.item;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            TNode last = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return last.item;
        }
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        TNode node = sentinel.next;
        while (index > 0) {
            node = node.next;
            index--;
        }
        return node.item;
    }

    public T getRecursiveHelper(TNode node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
}
