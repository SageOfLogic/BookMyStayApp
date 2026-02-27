import java.util.SortedMap;
import java.util.Scanner;
import java.util.Stack;

public class PalindromeApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input text: ");
        String text = sc.nextLine().trim().toLowerCase();

        Stack<Character> stack = new Stack<>();

        // Push characters into stack
        for (int i = 0; i < text.length(); i++) {
            stack.push(text.charAt(i));
        }

        boolean isPalindrome = true;

        // Pop and compare
        for (int i = 0; i < text.length(); i++) {
            char popped = stack.pop();

            if (text.charAt(i) != popped) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println("Decision: It is a Palindrome.");
        } else {
            System.out.println("Decision: It is NOT a Palindrome.");
        }
    }
}