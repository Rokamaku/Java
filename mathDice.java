import java.util.Scanner;
import java.util.StringTokenizer;

public class mathDice
{
	public static void main(String[] args)
	{
		Scanner scanIn = new Scanner(System.in);
		String setNum = "";
		int target = 0;
		System.out.println("Math dice program by Bui Hoang Anh");
		try {
			System.out.print("Enter a set of numbers: ");
			setNum = scanIn.nextLine();
			System.out.print("Enter a target value: ");
			target = scanIn.nextInt();
		} 
		catch (Exception e)
		{
			System.out.println("Error!");
		}
		StringTokenizer str = new StringTokenizer(setNum," ");
		float[] operand = new float[str.countTokens()];
		System.out.println(str.countTokens());
		int numOperand = 0;
		while (str.hasMoreTokens())
		{
			operand[numOperand] = Float.parseFloat(str.nextToken());
			numOperand++;
		}
		System.out.println(numOperand);
		char[] operator = {'+','-','*','/','^'};
		System.out.println(operand.length);
		createExpression(operand, operator, operand.length, 0, "");
		
	}
	static void createExpression(float[] operand, char[] operator, int numOperand, int curOperand, String prefix)
	{
		
		
	}

}