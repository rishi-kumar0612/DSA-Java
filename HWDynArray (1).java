public class HWDynArray {
    private int[] p;
    private int size; // the number of elements used
    private int capacity; // the amount of memory

    public HWDynArray(int initialCapacity) {

       int capacity = initialCapacity;


    }
    public void addEnd(int v) {
        int capacity = p !=null ? p.length : 0;
        int[] temp = new int[capacity+1];
        for(int i = 0; i<capacity; i++)
            temp[i] = p[i];
        temp[capacity] = v;
        p = temp;

    }

    public void addStart(int v) {
        int capacity = p !=null ? p.length:0;
        int[] temp = new int[capacity+1];
        temp[0] = v;
        for(int i = 0; i<capacity; i++)
        {temp[i] = p[i-1]; }
        p = temp;


    }
    public void removeStart() {
        int capacity = p != null ? p.length : 0;
        int[] temp = new int[capacity-1];
        for (int i = 1; i < capacity; i++) {
            temp[i-1] = p[i];
        }
        p = temp;



    }

    public void removeEnd() {
        int capacity = p != null ? p.length : 0;
        int[] temp = new int[capacity-1];
        for (int i = 0; i < capacity-1; i++) {
            temp[i] = p[i];
        }
        p = temp;
    }

    public void removeEvens(){
        int capacity = p != null ? p.length : 0;
        int count = 0;
        for (int i = 0; i < capacity; i++) {
            if (p[i] % 2 != 0) {
                count++;
            }
        }
        int[] temp = new int[count];
        int j = 0;
        for (int i = 0; i < capacity; i++) {
            if (p[i] % 2 != 0) {
                temp[j++] = p[i];
            }
        }
        p = temp;

    }


    public static void main(String[] args) throws NumberFormatException {
        HWDynArray a = new HWDynArray(500);   // empty list with 500 elements
        int n = Integer.parseInt(args[0]);
        for (int i = 0; i < 500; i++)
            a.addEnd(i); // really fast!

        for (int i = 0; i < n; i++)
            a.addEnd(i); // every time you need to grow, double

        a.addStart(5);

        for (int i = 0; i < n/2; i++)
            a.removeEnd();

        for (int i = 0; i < n/2; i++)
            a.removeStart();


        a.removeEvens();
        System.out.println(a);

    }


}
