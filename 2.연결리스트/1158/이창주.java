package silver._1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] s1 = s.split(" ");
        int n = Integer.parseInt(s1[0]);
        int k = Integer.parseInt(s1[1]);

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            linkedList.add(i+1);
        }

        ListIterator<Integer> iterator = linkedList.listIterator();

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        while (!linkedList.isEmpty()) {
            for (int i = 0; i < k; i++) {
                if (iterator.hasNext()) {
                    iterator.next();
                } else {
                    while (iterator.hasPrevious()) {
                        iterator.previous();
                    }
                    iterator.next();
                }
            }
            if (iterator.hasPrevious()){
                if(linkedList.size() != 1) {
                    sb.append(iterator.previous()).append(", ");
                } else {
                    sb.append(iterator.previous());
                }
                iterator.remove();
            } else {
                while (iterator.hasPrevious()) {
                    iterator.previous();
                }
            }
        }
        sb.append(">");
        System.out.println(sb);
    }
}
