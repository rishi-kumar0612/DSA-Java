import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//citation 1: https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
//citation 2: https://ide.geeksforgeeks.org/v6TaHW2wTD

public class HashMap {
    private static final int hashmap_SIZE = 3000000;
    private List<String>[] table;
    public HashMap() {
        table = new LinkedList[hashmap_SIZE];
        for (int i= 0; i< hashmap_SIZE; i++) {
            table[i]= new LinkedList<>();
        }
    }

    private int hashcode(String key) {
        int hash= 0;
        int prime_num= 31;
        int factor= 1;
        for (char c:key.toCharArray()) {
            hash += factor * c;
            factor *= prime_num;
        }
        return Math.abs(hash)% hashmap_SIZE;
    }


    public void insert(String key) {
        int position= hashcode(key);
        table[position].add(key);
    }

    public boolean search(String key) {
        int index= hashcode(key);
        return table[index].contains(key);
    }

    public void histogram() {
        int[] counts= new int[hashmap_SIZE + 1];
        for (int i= 0; i< hashmap_SIZE; i++) {
            int count= table[i].size();
            counts[count]++;
        }
        System.out.println("insert\tcount");
        for (int i= 0; i< hashmap_SIZE; i++) {
            if (counts[i]> 0) {
                System.out.println(i+ "\t" + counts[i]);
            }
        }
        if (counts[hashmap_SIZE] > 0) {
            System.out.println("9+\t" + counts[hashmap_SIZE]);
        }
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();

        try {BufferedReader reader = new BufferedReader(new FileReader("dict.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                map.insert(line.trim());
            }
            reader.close();

            reader = new BufferedReader(new FileReader("words.txt"));
            while ((line = reader.readLine()) != null) {
                boolean exists = map.search(line.trim());
                System.out.println(exists);
            }
            reader.close();
            map.histogram();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
