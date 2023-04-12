package BOJ1406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

/*

ListIterator : 직관적으로 커서의 역할을 함
previous(hasPrevious): 왼쪽으로 한칸(있는지 없는지)
next(hasNext): 오른쪽으로 한칸(있는지 없는지)
remove, add: 현재 위치에서 원소 추가, 제거

*/

public class MainSolutionOnly {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split("");
		
		List<String> s = new LinkedList<>();
		ListIterator<String> iter = s.listIterator();
		for (int i = 0; i < input.length; i++) {
			iter.add(input[i]);
		}
		
		int m = Integer.parseInt(br.readLine());
		
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
				iter.add(word);
				System.out.println(s.toString());
				
			} else if (cmd.equals("L")) {
				if (iter.hasPrevious()) iter.previous();
				System.out.println(s.toString());
				
			} else if (cmd.equals("D")) {
				if (iter.hasNext()) iter.next();
				System.out.println(s.toString());
				
			} else if (cmd.equals("B")) {
				if (iter.hasPrevious()) {
					iter.previous();
					iter.remove();
				}
				System.out.println(s.toString());
			} else {
				System.out.println("The keyword is wrong!");
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

