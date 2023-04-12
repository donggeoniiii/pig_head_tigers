import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			char[] str = br.readLine().toCharArray();
			if(str.length == 1 && str[0] == '.')
				return;
			Stack<Character> s = new Stack<>();
			
			boolean flag = false;
			for(int i = 0; i < str.length; i++) {
				if(str[i] == '(' || str[i] == '[') {
					s.push(str[i]);
				} else if(str[i] == ')'){
					if(!s.isEmpty() && s.peek() == '(')
						s.pop();
					else {
						if(s.isEmpty())
							flag = true;
						break;
					}
				} else if(str[i] == ']') {
					if(!s.isEmpty() && s.peek() == '[')
						s.pop();
					else{
						if(s.isEmpty())
							flag = true;
						break;
					}
				}
			}
			
			if(!s.isEmpty() || flag)
				System.out.println("no");
			else
				System.out.println("yes");
		}
	}
}