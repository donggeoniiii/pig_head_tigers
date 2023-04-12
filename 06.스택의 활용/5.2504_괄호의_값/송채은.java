package _2504;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		input = input.replace("()", "2");
		input = input.replace("[]", "3");
		
		Stack<Character> s = new Stack<>();
		Stack<Integer> cal = new Stack<>();
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(' || input.charAt(i) == '[') {
				s.push(input.charAt(i));
				//System.out.println("( [ push");
			}
			else if(input.charAt(i) == ')') {
				//System.out.println(")");
				if(s.peek() == '2' || s.peek() == '3') {
					//System.out.println(") 숫자");
					int num = s.pop() - '0';
					if(s.pop() == '(') {
						//System.out.println(") 숫자 (");
						cal.push(num * 2);
					}
				}
				else if(s.peek() == '(' || s.peek() == '*') {
					//System.out.println(") (");
					if(s.peek() == '*') s.pop();
					s.pop();
					int temp = 0;
					while(!cal.isEmpty()) temp += cal.pop();
					System.out.println("() temp " + temp * 2);
					cal.push(temp * 2);
					s.push('*');
				}
				else {
					System.out.println("0");
					return;
				}
			}
			else if(input.charAt(i) == ']') {
				//System.out.println("]");
				if(s.peek() == '2' || s.peek() == '3') {
					//System.out.println("] 숫자");
					int num = s.pop() - '0';
					if(s.pop() == '[') {
						//System.out.println("] 숫자 [");
						cal.push(num * 3);
					}
				}
				else if(s.peek() == '[' || s.peek() == '*') {
					//System.out.println("] [");
					if(s.peek() == '*') s.pop();
					s.pop();
					int temp = 0;
					while(!cal.isEmpty()) temp += cal.pop();
					System.out.println("() temp " + temp * 3);
					cal.push(temp * 3);
					s.push('*');
				}
				else {
					//System.out.println("0");
					return;
				}
			}
			else {
				//System.out.println("그 외");
				s.push(input.charAt(i));
				//cal.push(input.charAt(i) - '0');
			}
		}
		
		int sum = 0;
		while(!cal.isEmpty()) {
			int temp = cal.pop();
			System.out.println(temp);
			sum += temp;
		}
		System.out.println(sum);
	}
}
