package BOJ1406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*

	1. P
	- 커서 == size일때  => add(word) 후 cur = size()로 위치 갱신
	- 아닐 때 => add(n, word) 후 cur++로 위치 갱신
	
	2. L
	- 커서가 0일 때 => 더 갈 수 없으므로 무시
	- 아닐 때 => 갈 수 있으므로 cur--
	
	3. D	
	- 커서 == size일때  => 더 갈 수 없으므로 무시
	- 아닐 때 => 갈 수 있으므로 cur++
	
	4. B
	- 커서가 7(==size)일때 => 그대로 remove하면 indexError, cur--후 remove(cur)하고 제자리;
	- 커서가 n(0<n<size)일 때 => remove(cur-1) 후 cur--로 위치 갱신
	- 커서가 0일 때 => 무시

 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split("");
		
		List<String> s = new LinkedList<>();
		for (int i = 0; i < input.length; i++) {
			s.add(input[i]);
		}
		
		int m = Integer.parseInt(br.readLine());
		
		int cur = s.size();
		for (int test_case = 1; test_case <= m; test_case++) {
			// 명령어와 입력값 구분
			StringTokenizer editor = new StringTokenizer(br.readLine());
			
			// 입력값이 있는 경우 word에 저장
			String cmd = editor.nextToken();
			String word = "";
			if (editor.hasMoreTokens()) {
				word = editor.nextToken();
			}
			
			// P -> 커서 왼쪽에 입력, 입력하면 커서는 오른쪽으로 한칸 이동한것과 같으므로 위치 갱신
			if (cmd.equals("P")) {
				// 커서가 7(==size)일때  => add(word) 후 cur = size()로 위치 갱신
				if (cur == s.size()) {
					s.add(word);
					cur = s.size();
					//System.out.println("cur:" + cur);
				// 커서가 7이 아닐 때 => add(n, word) 후 cur++로 위치 갱신
				} else {
					s.add(cur, word);
					cur++;
					//System.out.println("cur:" + cur);
				}
				//System.out.println(s.toString());
				
			} else if (cmd.equals("L")) {
				if (cur == 0) {
					//System.out.println("cur:" + cur);
					continue;
				} else {
					cur--;
					//System.out.println("cur:" + cur);
				}
				//System.out.println(s.toString());
				
			} else if (cmd.equals("D")) {
				if (cur == s.size()) {
					//System.out.println("cur:" + cur);
					continue;
				} else {
					cur++;
					//System.out.println("cur:" + cur);
				}
				//System.out.println(s.toString());
				
			} else if (cmd.equals("B")) {
				if (cur == s.size()) {
					s.remove(cur-1);
					cur--;
					//System.out.println("cur:" + cur);
				} else if (cur == 0) {
					//System.out.println("cur:" + cur);
					continue;
				} else {
					s.remove(cur-1);
					cur--;
					//System.out.println("cur:" + cur);
				}
				//System.out.println(s.toString());
			} else {
				//System.out.println("The keyword is wrong!");
			}
			
		}
		
		// StringBuilder를 이용해 출력
		StringBuilder sb = new StringBuilder();
		for (String c : s) {
			sb.append(c);
		}
		System.out.println(sb);
	}
}
