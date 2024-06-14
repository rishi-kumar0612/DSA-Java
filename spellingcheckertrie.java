/*
Author: RISHI KUMAR

citation : https://www.geeksforgeeks.org/trie-insert-and-search/
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class spellingcheckertrie {
    private static class trienode
    {
        private static  final int count=26;
        trienode[] children;
        boolean iseow;
        trienode()
        {children= new trienode[count];
        iseow= false;
        }


    }
    private static class trie{
        private trienode root;
        trie()
        {
            root = new trienode();
        }
        public void insert(String word)
        {trienode current =root;
        for (int i=0; i<word.length();i++)
        {char ch= word.charAt(i);
        int index = ch - 'a';
        if (current.children[index]==null)
        {
            current.children[index]=new trienode();
        }
        current = current.children[index];
        }
        current.iseow=true;
        }

        public boolean search(String word)
        {trienode current = root;
        for (int i=0; i<word.length();i++)
        {char ch = word.charAt(i);
            int index= ch - 'a';
            if(index <0|| index >=trienode.count)
            {
                return false;

            }
            if (current.children[index]==null)
            {
                return false;
            }
            current=current.children[index];

        }return current.iseow;
        }

    }

    public static void main(String[] args){
        trie t = new trie();
        try(BufferedReader B=new BufferedReader(new FileReader("dict.txt")))
        {
            String line;
            while((line=B.readLine())!=null)
            {
                t.insert(line);
            }
        }catch (IOException e)
        {
            System.err.println("error reading:"+e.getMessage());
            return;
        }
        try (BufferedReader B=new BufferedReader(new FileReader("words.txt"))){
            String line;
            while ((line=B.readLine())!=null){
                boolean result= t.search(line);
                System.out.println(line+" "+(result?"is ":"is not ")+"found in this dictionary");
            }
        }catch (IOException e){
            System.err.println("error reading from file:"+e.getMessage());
            return;
        }
    }
}

