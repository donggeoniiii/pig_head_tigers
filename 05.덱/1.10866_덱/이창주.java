import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MX = 10000;
    static int[] dat = new int[2 * MX + 1];
    static int head = MX;
    static int tail = MX;


    static void push_front(int x) {
        dat[--head] = x;
    }

    static void push_back(int x) {
        dat[tail++] = x;
    }

    static int pop_front() {
        if (tail - head > 0) {
            return dat[head++];
        } else return -1;
    }

    static int pop_back() {
        if (tail - head > 0) {
            return dat[--tail];
        } else return -1;
    }

    static int size() {
        return tail - head;
    }

    static int empty() {
        if(tail == head) return 1;
        return 0;
    }

    static int front() {
        if (tail - head > 0) {
            return dat[head];
        } else return -1;
    }

    static int back() {
        if (tail - head > 0) {
            return dat[tail - 1];
        } else return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push_front":
                    push_front(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back" :
                    push_back(Integer.parseInt(st.nextToken()));
                    break;

                case "pop_front":
                    System.out.println(pop_front());
                    break;

                case "pop_back":
                    System.out.println(pop_back());
                    break;

                case "size":
                    System.out.println(size());
                    break;

                case "empty":
                    System.out.println(empty());
                    break;

                case "front":
                    System.out.println(front());
                    break;

                case "back" :
                    System.out.println(back());
                    break;

                default :
                    break;
            }
        }
    }
}
