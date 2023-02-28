import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int soobin = Integer.parseInt(st.nextToken());
		int sister = Integer.parseInt(st.nextToken());
		int time = 0;
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		Node sb = new Node(soobin, time);
		q.add(sb);
		visited[soobin] = true;
		
		Loop : while(!q.isEmpty()) {
			Node check = new Node(q.peek().x, q.peek().sec);
			q.poll();
			if(check.x == sister)
				break Loop;
			if(check.x - 1 == sister || check.x + 1 == sister || check.x * 2 == sister) {
				time = check.sec + 1;
				break Loop;
			}
			
			if(check.x - 1 >= 0) {
				if(!visited[check.x - 1]) {
					visited[check.x - 1] = true;
					Node next1 = new Node(check.x - 1, check.sec + 1);
					q.add(next1);
				}	
			}
			if(check.x + 1 <= 100000) {
				if(!visited[check.x + 1]) {
					visited[check.x + 1] = true;
					Node next2 = new Node(check.x + 1, check.sec + 1);
					q.add(next2);
				}	
			}
			if(check.x * 2 <= 100000) {
				if(!visited[check.x * 2]) {
					visited[check.x * 2] = true;
					Node next3 = new Node(check.x * 2, check.sec + 1);
					q.add(next3);
				}
			}
		}
		
		System.out.println(time);
	}
}

class Node{
	int x, sec;
	Node(int x, int sec){
		this.x = x;
		this.sec = sec;
	}
}