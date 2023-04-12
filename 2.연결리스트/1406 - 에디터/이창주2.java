import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static final int MX = 600001;
    public static char[] dat = new char[MX];
    public static int[] pre = new int[MX];
    public static int[] nxt = new int[MX];

    static int cura = 1;
    public static void insert(int addr, char c) {
        dat[cura] = c;
        pre[cura] = addr;
        nxt[cura] = nxt[addr];

        if(nxt[addr] != -1) pre[nxt[addr]] = cura;
        nxt[addr] = cura;
        cura++;
    }

    public static void erase(int addr) {
        nxt[pre[addr]] = nxt[addr];
        if(nxt[addr] != -1) pre[nxt[addr]] = pre[addr];
    }
    public static void traverse() {
        StringBuilder sb = new StringBuilder();
        int cur = nxt[0];
        while (cur != -1) {
            sb.append(dat[cur]);
            cur = nxt[cur];
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arrays.fill(pre, -1);
        Arrays.fill(nxt, -1);

        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            insert(i, s.charAt(i));
        }
        int t = Integer.parseInt(br.readLine());
        int cursor = s.length();

        for (int i = 0; i < t; i++) {
            String a = br.readLine();

            if (a.length() == 1) {

                if (a.equals("L")) {
                    if(pre[cursor] != -1)  cursor = pre[cursor];// cursor 인덱스처럼;
                } else if (a.equals("B")) {
                    if(pre[cursor] != -1) {
                        erase(cursor);
                        cursor = pre[cursor];
                    }
                } else {
                    if(nxt[cursor] != -1)  cursor = nxt[cursor];
                }
            } else {
                insert(cursor,a.charAt(2));
                cursor = nxt[cursor];
            }
        }
        traverse();
    }
}
