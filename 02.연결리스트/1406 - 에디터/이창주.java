package silver._1406;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException {

        LinkedList<Character> linkedList = new LinkedList<>();


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            char a =s.charAt(i);
            linkedList.add(a);

        }
        ListIterator<Character> iterator = linkedList.listIterator(linkedList.size());

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String ss = br.readLine();
            char c = ss.charAt(0);
            if (c == 'P') {
                iterator.add(ss.charAt(2));
            } else if (c == 'L') {
                if(iterator.hasPrevious()) iterator.previous();
            } else if (c == 'D') {
                if(iterator.hasNext()) iterator.next();
            } else {
                if (iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : linkedList) {
            sb.append(c);
        }

        System.out.println(sb);
    }
}
