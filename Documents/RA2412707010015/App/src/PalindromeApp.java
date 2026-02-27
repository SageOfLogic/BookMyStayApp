import java.util.SortedMap;
import java.util.Scanner;

public class PalindromeApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input text: ");
        String word = sc.nextLine();

        String reversed = "";

        for (int i = word.length() - 1; i >= 0; i--) {
            reversed = reversed + word.charAt(i);
        }

        if (word.equals(reversed)) {
            System.out.println("Decision: It is  a Palindrome.");
        } else {
            System.out.println("Decision: It is NOT a Palindrome.");
        }

    }
}