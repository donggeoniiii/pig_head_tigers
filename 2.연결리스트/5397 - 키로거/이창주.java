package silver._5397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            LinkedList<Character> linkedList = new LinkedList<>();
            String s = br.readLine();
            ListIterator<Character> listIterator = linkedList.listIterator();

            // <<BP<A>>Cd-
            for (int j = 0; j < s.length(); j++) {

                char c = s.charAt(j);
                if (c == '<') {
                    if (listIterator.hasPrevious()) {
                        listIterator.previous();
                    }
                } else if (c == '>') {
                    if (listIterator.hasNext()) {
                        listIterator.next();
                    }

                } else if (c == '-') {
                    if(listIterator.hasPrevious()) {
                        listIterator.previous();
                        listIterator.remove();
                    }
                } else {
                    listIterator.add(c);
                }
            }
            for (char a : linkedList) {
                sb.append(a);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
