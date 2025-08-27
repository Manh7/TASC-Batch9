package set;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        Set<Character> charSet = new LinkedHashSet<>();

        charSet.add('a');
        charSet.add('b');
        charSet.add('c');
        charSet.add('d');
        charSet.add('d');
        charSet.add('e');
        charSet.add('f');


        charSet.remove('a');

        for(char c: charSet){
            System.out.println(c);
        }
    }
}
