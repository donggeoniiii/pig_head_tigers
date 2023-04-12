import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    // 톱니바퀴를 담을 덱 리스트
    static Deque<Integer>[] deques = new Deque[4];
    static int[] aa = new int[4];

    // 회전 메소드
    static void rotation(int num, int dir) {


        // true 오른쪽 false 왼쪽
        int temp = -dir;
        boolean isRotation = false;
        //오른쪽으로
        for (int i = num + 1; i < 4; i++) {

            // 3번쨰 값이랑 뒤에서 3번째 값이랑 같으면 break
            isRotation = true;
            // 왼쪽은 앞에서 2번 빼고 뒤로 두번 넣었다가
            // 뒤에서 두번 빼서 앞으로 두번 넣기

            deques[i - 1].offerLast(deques[i - 1].pollFirst());
            deques[i - 1].offerLast(deques[i - 1].pollFirst());

            // 뒤에서 두번 빼서 앞으로 두번 넣었다가
            // 앞에서 두번 빼서 뒤로 넣기

            deques[i].offerFirst(deques[i].pollLast());
            deques[i].offerFirst(deques[i].pollLast());


            if (deques[i - 1].peek() == deques[i].peek()) {
                isRotation = false;
            }

            deques[i - 1].offerFirst(deques[i - 1].pollLast());
            deques[i - 1].offerFirst(deques[i - 1].pollLast());

            deques[i].offerLast(deques[i].pollFirst());
            deques[i].offerLast(deques[i].pollFirst());


            if (!isRotation) break;


            aa[i] = temp;

            temp = -temp;
        }

        temp = -dir;
        // 왼쪽으로
        for (int i = num - 1; i >= 0; i--) {


            isRotation = true;

            // 3번쨰 값이랑 뒤에서 3번째 값이랑 같으면 break
            // 왼쪽은 앞에서 2번 빼고 뒤로 두번 넣었다가
            // 뒤에서 두번 빼서 앞으로 두번 넣기

            deques[i].offerLast(deques[i].pollFirst());
            deques[i].offerLast(deques[i].pollFirst());

            // 뒤에서 두번 빼서 앞으로 두번 넣었다가
            // 앞에서 두번 빼서 뒤로 넣기'

            deques[i + 1].offerFirst(deques[i + 1].pollLast());
            deques[i + 1].offerFirst(deques[i + 1].pollLast());


            if (deques[i].peek() == deques[i + 1].peek()) {
                isRotation = false;
            }

            deques[i].offerFirst(deques[i].pollLast());
            deques[i].offerFirst(deques[i].pollLast());

            deques[i + 1].offerLast(deques[i + 1].pollFirst());
            deques[i + 1].offerLast(deques[i + 1].pollFirst());


            if (!isRotation) break;


            aa[i] = temp;

            temp = -temp;
        }


        for (int i = 0; i < 4; i++) {
            if(aa[i] == 0) continue;
            if (aa[i] == 1) {
                // 뒤에서 빼서 앞으로
                deques[i].offerFirst(deques[i].pollLast());
            } else {
                // 앞에서 빼서 뒤로
                deques[i].offerLast(deques[i].pollFirst());
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            deques[i] = new LinkedList<>();
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                deques[i].offer(s.charAt(j) - '0');
            }
        }

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            aa = new int[4];
            int num = Integer.parseInt(st.nextToken());
            int dir = -1;
            aa[num - 1] = -1;
            if (Integer.parseInt(st.nextToken()) == 1) {
                aa[num - 1] = 1;
                dir = 1;
            }
            rotation(num - 1, dir);
        }

        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (deques[i].peek() == 1) score += Math.pow(2, i);
        }
        System.out.println(score);
    }
}