import java.util.Scanner;

public class Combinations {
    public static void main(String[] args) {
        Scanner scanIn = new Scanner(System.in);
        String str = "";
        int subSize = 0;
        System.out.println("LetterSubsets program by Bui Hoang Anh");
        try {
            System.out.print("Enter a string: ");
            str = scanIn.nextLine();
            System.out.print("Enter a value: ");
            subSize = scanIn.nextInt();
        }   
        catch (Exception e) {
            System.out.println("Error!");
        }
        char[] set = str.toCharArray();
        System.out.println("Subsets of \"" + str + "\" of size " + subSize + ":");
        int totalSubset = Comblen(set, "", set.length, subSize, 0);
        System.out.println(totalSubset + " total subsets");
    } 

    static int Comblen(char[] set, String prefix, int n, int subSize, int noDup) {
        //Base case: k = 0 then print prefix
        if (subSize == 0) {
            System.out.println(prefix);
            return 1; //count the number of times printing the prefix
        }
        int totalSubset = 0;

        // One by one add all characters from set and recursively
        for (int i = noDup; i < n; i++) { //start from noDup to avoid duplicating the frontier character
                // add new character at i position to prefix
                String newPrefix = prefix + set[i]; 
                // subSize will be decrease because of adding a new character
                totalSubset += Comblen(set, newPrefix, n, subSize - 1, i + 1); 
        }
        return totalSubset;
    }
}
