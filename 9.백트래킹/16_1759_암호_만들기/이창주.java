import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int L;
    static int C;
    static ArrayList<Character> list = new ArrayList<>();

    static char[] choice;
    static StringBuilder sb = new StringBuilder();

    static void recur(int n, int start, int vowels, int consonant) {

        if (n + (list.size() - start) < L) return;
        if (n == L) {
            if (vowels > 0 && consonant > 1) {
                for (int i = 0; i < L; i++) {
                    sb.append(choice[i]);
                }
                sb.append("\n");
            }
            return;
        }
        for (int i = start; i < list.size(); i++) {
            Character c = list.get(i);
            choice[n] = c;

            switch (c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    recur(n + 1, i + 1, vowels+1,consonant);
                    break;

                default:
                    recur(n + 1, i + 1, vowels,consonant+1);
                    break;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        choice = new char[L];
        String s = br.readLine();
        for (int i = 0; i < C * 2 - 1; i += 2) {
            list.add(s.charAt(i));
        }

        Collections.sort(list);
        recur(0,0,0,0);
        System.out.println(sb);
    }
}