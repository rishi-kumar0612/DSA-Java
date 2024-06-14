public class HWDoubleLinkedList {
    private static class Node {
        Node next;
        Node prev;
        int val;
    }

    private Node head;
    private Node tail;
    private int size;

    public HWDoubleLinkedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addStart(int v) {
        Node newNode = new Node();
        newNode.val = v;
        newNode.next = head.next;
        newNode.prev = head;
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    public void addEnd(int v) {
        Node newNode = new Node();
        newNode.val = v;
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
    }

    public void removeStart() {
        if (size == 0) {
            return;
        }
        head.next = head.next.next;
        head.next.prev = head;
        size--;
    }

    public void removeEnd() {
        if (size == 0) {
            return;
        }
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        size--;
    }

    public void insert(int pos, int v) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head.next;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        Node newNode = new Node();
        newNode.val = v;
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = head.next;
        while (current != tail) {
            sb.append(current.val);
            if (current.next != tail) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            HWDoubleLinkedList a = new HWDoubleLinkedList();

            /* Dear TA , I have changed the below line to meet the underflow limiting condition
             as mentioned on canvas assignment page, by default it will be 10 otherwise  */

            int n = (Integer.parseInt(args[0]) > 10) ? Integer.parseInt(args[0]) : 10;
            //int n = Integer.parseInt(args[0]);
            for (int i = 0; i < n; i++) {
                a.addStart(i);
            }

            for (int i = 0; i < n; i++) {
                a.addEnd(i);
            }

            for (int i = 0; i < 3 * n / 2; i++) {
                a.removeStart();
            }
            for (int i = 0; i < n / 2 - 5; i++) {
                a.removeEnd();
            }
            System.out.println(a);
            for (int i = 0; i < 10; i++) {
                a.insert(1, i);
            }
            System.out.println(a);
        } catch (NumberFormatException nfe) {
            System.out.println("Argument must be an integer value");
        }
    }
}
