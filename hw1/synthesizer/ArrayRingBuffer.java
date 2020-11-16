package synthesizer;

import java.lang.reflect.Array;
import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb= (T[]) new Object[capacity];
        first=0;
        last=0;
        fillCount=0;
        this.capacity=capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last]=x;
        last=(last+1)%this.capacity();
        fillCount++;
    }



    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T removed=rb[first];
        fillCount--;
        first=(first+1)%this.capacity();
        return removed;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (first == last) {

        }
        T t = rb[first];

        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new ARBIterator();
    }

    private class ARBIterator implements Iterator<T>{
        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public T next() {
            T item =dequeue();
            return item;
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
//    public int capacity() {
//        return this.capacity;
//    }
//
//    public int fillCount(){
//        return this.fillCount;
//    }
//    public boolean isEmpty() {
//        return this.isEmpty();
//    }
//    public boolean isFull() {
//        return true;
//    }
}
