package BOJ1158;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> list = new LinkedList<>();
		StringBuilder answer = new StringBuilder();
		answer.append("<");
		// LinkedList에 1~N까지 추가(초기화)
		for (int i = 1; i <= N; i++) list.offer(i);
		/*
		 * 
		 * 
		 *
		 */
		int cnt = 0;
		while (list.size() != 1) {
			if (cnt % K == K - 1) {
				answer.append(list.poll() + ", ");
			} else {
				list.offer(list.poll());
			}
			cnt++;
		}
		
		answer.append(list.poll() + ">");
		bw.write(answer.toString());
		bw.flush();
		bw.close();
	}
}
