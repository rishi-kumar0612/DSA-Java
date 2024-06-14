/*
author : rishi kumar
cwid : 20015656

citation 1 :https://www.geeksforgeeks.org/java-program-to-implement-circular-buffer/?ref=gcse

 */

import java.util.Arrays;

public class CircularBuffer<T> {
    //@SuppressWarnings("unchecked")
    private T[] buffer;
    private int head;
    private int tail;
    private int size;
    private int capacity;


    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }




    public void add(T element_buffer) {
        if (size == capacity) {

            int newCapacity_size = capacity * 2;
            T[] new_Buffer = (T[]) new Object[newCapacity_size];
            int i = 0;
            while (i < size) {
                new_Buffer[i] = buffer[head];
                head = (head + 1) % capacity;
                i++;
            }
            head = 0;
            tail = size;
            capacity = newCapacity_size;
            buffer = new_Buffer;
        }
        buffer[tail] = element_buffer;
        tail = (tail + 1) % capacity;
        size++;
    }


    public T remove() {
        if (size == 0) {
            throw new IllegalStateException("Buffer is empty");
        }
        T element = buffer[head];
        head = (head + 1) % capacity;
        size--;
        return element;
    }


    public int size() {
        return size;
    }


    public T[] getBufferContents() {
        T[] contents_buffer = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            int index = (head + i) % capacity;
            contents_buffer[i] = buffer[index];
        }
        return contents_buffer;
    }

    public static void main(String[] args)
    {

        CircularBuffer<Integer> buffer = new CircularBuffer<>(4);


        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4);


        System.out.println("Buffer contents: " + Arrays.toString(buffer.getBufferContents()));


        buffer.remove();
        buffer.remove();


        buffer.add(5);
        buffer.add(6);


        System.out.println("Buffer contents: " + Arrays.toString(buffer.getBufferContents()));


        for (int i = 7; i <= 10; i++)
        {
            buffer.add(i);
        }


        for (int i = 1; i <= 3; i++)
        {
            buffer.remove();
        }


        System.out.println("Buffer contents: " + Arrays.toString(buffer.getBufferContents()));


        for (int i = 11; i <= 18; i++)
        {
            buffer.add(i);
        }


        System.out.println("Buffer contents: " + Arrays.toString(buffer.getBufferContents()));


        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++)
        {
            buffer.add(i);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time taken to add 1 million elements: " + totalTime + " ms");

        startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++)
        {
            buffer.remove();
        }
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("Time taken to remove 1 million elements: " + totalTime + " ms");


        CircularBuffer<String> buffer2 = new CircularBuffer<>(2);
        buffer2.add("A");
        buffer2.add("B");
        buffer2.remove();
        buffer2.add("C");
        String[] expectedContents = {"B", "C"};
        assert Arrays.equals(expectedContents, buffer2.getBufferContents()) : "Buffer contents are incorrect";

        CircularBuffer<Double> buffer3 = new CircularBuffer<>(5);
        buffer3.add(1.1);
        buffer3.add(2.2);
        buffer3.add(3.3);
        buffer3.remove();
        buffer3.remove();
        buffer3.add(4.4);
        buffer3.add(5.5);
        Double[] expectedContents3 = {3.3, 4.4, 5.5};
        assert Arrays.equals(expectedContents3, buffer3.getBufferContents()) : "Buffer contents are incorrect";

        System.out.println("All tests passed");
    }
}

