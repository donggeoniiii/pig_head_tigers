import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception{
		Queue<Integer> q = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= N; i++)
			q.add(i);
		
		// 1.카드를 버림 -> 2.카드를 제일 밑으로 옮김 -> 1.카드를 버림 -> .. 반복
		// 가장 마지막에 남는 카드는 1 과정 직후의 카드이므로
		// while 문 실행 시 2 -> 1 -> 2 -> .. 순서로 반복
		// N == 2인 경우 따로 작성
		int ans = q.poll();
		if(N==2) ans = q.poll();
		
		while(q.size() > 1) {
			int temp = q.poll();
			q.add(temp);
			q.poll();
			ans = q.peek();
		}
		System.out.println(ans);
	}
}
