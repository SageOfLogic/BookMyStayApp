import java.util.SortedMap;
import java.util.Scanner;
import java.util.Scanner;

public class PalindromeApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input text: ");
        String text = sc.nextLine().trim().toLowerCase();

        char[] characters = text.toCharArray();

        int start = 0;
        int end = characters.length - 1;

        boolean isPalindrome = true;

        while (start < end) {

            if (characters[start] != characters[end]) {
                isPalindrome = false;
                break;
            }

            start++;
            end--;
        }

        if (isPalindrome) {
            System.out.println("Decision: It is a Palindrome.");
        } else {
            System.out.println("Decision: It is NOT a Palindrome.");
        }
    }
}