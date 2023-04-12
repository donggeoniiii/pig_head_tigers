import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		// 탑의 높이가 Stack에 저장
		// 탑의 높이와 탑의 번호(index)가 Map에 저장 (탑의 높이는 서로 다르기 때문에 가능)
		Stack<Integer> s = new Stack<>();
		Map<Integer, Integer> map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		int[] ans = new int[N];
		
		for(int i = 0; i < N; i++) {
			int tower = Integer.parseInt(st.nextToken());
			map.put(tower, i+1);
			
			// 가능한 경우
			// 1. stack이 비어있는 경우
			// 즉, 왼쪽 방향으로 탑이 없는  경우 ... 0
			if(s.isEmpty()) {
				ans[i] = 0;
				s.push(tower);
			}
			// 2. stack이 비어있지 않은 경우
			else {
				// 2-1. stack의 최상단 요소가 현재 들어온 요소의 값보다 작은 경우
				// stack의 최상단 요소가 현재 들어온 요소보다 작을 때 까지 pop
				if(s.peek() < tower) {
					while(s.peek() < tower) {
						s.pop();
						if(s.isEmpty()) {
							ans[i] = 0;
							break;
						}else {
							ans[i] = s.peek();
						}
					}
					s.push(tower);
				}
				// 2-2. stack의 최상단 요소가 현재 들어온 값보다 큰 경우
				// 현재 들어온 탑의 신호를 수신하는 것은 stack 최상단 요소를 key로 갖는 value값
				else {
					ans[i] = s.peek();
					s.push(tower);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(ans[i] == 0)
				bw.write("0 ");
			else
				bw.write(map.get(ans[i]) + " ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
