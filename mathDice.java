//Name: Buui Hoang Anh
//Id: 14520014


import java.util.*;
import java.lang.Math;

class mathDice
{
	private static Scanner scanIn;
	// opsArr store the set of operators which permunated
	public static ArrayList<String> opsArr = new ArrayList<String>();
	private static double closestVal;	
	private static double valTar;	
	//initlize delta = positive_infinitY
	private static double delta = Double.POSITIVE_INFINITY;
	private static String closestExprs = new String();
	public static void main (String[] args)
	{
		scanIn = new Scanner(System.in);
		String setNum = "";
		System.out.println("Math dice program by Bui Hoang Anh");
		try 
		{
			System.out.print("Enter a set of numbers: ");
			setNum = scanIn.nextLine();
			System.out.print("Enter a target value: ");
			valTar = scanIn.nextInt();
		} 
		catch (Exception e)
		{
			System.out.println("Error!");
		}
		StringTokenizer str = new StringTokenizer(setNum," ");
		ArrayList<String> operands = new ArrayList<String>();
		while (str.hasMoreTokens())
			operands.add(str.nextToken());
		char[] operators = new char[]{'+','-','*','/','^'};
		solveMathDice(operands, operators);
		System.out.println("Closest expression");
		System.out.println(closestExprs + " = " + closestVal);
	}
	private static void solveMathDice(ArrayList<String> operands, char[] operators)
	{
		// print 1 length operand case
		for (int i = 0; i < operands.size(); i++)
		{
			double result = Double.parseDouble(operands.get(i));
			System.out.println(result + " = " + result);
			// calculate the distance between target value and each operand
			// if this value less than init delta value => delta = new distance value
			// and store the expression 
			double tempDelta = Math.abs(valTar - result);
			if (tempDelta < delta) 
			{
				closestVal = result;
				delta = tempDelta;
				closestExprs = result + " = " + result;
			}
		}
		// print from 2 to max length of operands
		for (int oprLen = 2; oprLen <= operands.size(); oprLen++)
		{
			// create the permutation of operators
			// the number of operators are equal the number of operands - 1
			CombinationOps(operators, "", operators.length, oprLen - 1);
			// create the permutation of operands
			ArrayList<String> comb = new ArrayList<String>();
			CombinationOpr(operands, comb, operands.size(), oprLen , 0);
			opsArr.clear();
		}
	}
	// create subset of operators then create the permutation of each subset
	private static void CombinationOps(char[] operators, String comb, int length, int subSize)
	{
	    if (subSize == 0)
	    	PermutationOps(comb.toCharArray(), 0, comb.length() - 1);
	    else
		    for (int i = 0; i < length; i++)
		    {
		        String newComb = comb + operators[i];
		        CombinationOps(operators, newComb, length, subSize - 1);
		    }
	}
	private static void PermutationOps(char[] operators, int begin, int end)
	{
		 if (begin == end)
		    {
			 	String ops = new String(operators);
		    	opsArr.add(ops);
		    }
		    else
		    {
		        for (int i = begin; i <= end; i++)
		        {
		            swap(operators, begin, i);
		            PermutationOps(operators, begin + 1, end);
		            swap(operators, begin, i);
		        }
		    }
	}
	private static void swap(char[] operators, int a, int  b)
	{
		char temp = operators[a];
		operators[a] = operators[b];
		operators[b] = temp;
	}
	private static void CombinationOpr(ArrayList<String> operands, ArrayList<String> comb, int length, int subSize, int noDup)
	{
		if (subSize == 0)
	    	PermutationOpr(comb, 0, comb.size() - 1);
	    else
		    for (int i = noDup; i < length; i++)
		    {
		    	ArrayList<String> newComb = new ArrayList<String>(comb);
		        newComb.add(operands.get(i));
		        CombinationOpr(operands, newComb, length, subSize - 1, i + 1);
		    }
	}
	private static void PermutationOpr(ArrayList<String> perm, int begin, int end)
	{
	    if (begin == end)
	    {
	        for (int i = 0; i < opsArr.size(); i++)
	        {
	        	char[] operators = opsArr.get(i).toCharArray();
	        	createExprs(perm, operators, "", perm.size() - 1, perm.size(), 0, 0);
	        }
	    }
	    else
	    {
	        for (int i = begin; i <= end; i++)
	        {
	            Collections.swap(perm, begin, i);
	            PermutationOpr(perm, begin + 1, end);
	            Collections.swap(perm, begin, i);
	        }
	    }
	}
	private static void createExprs(ArrayList<String> operands, char[] operators, String exprs, int ops, int nums, int curOps, int curNums)
	{
		if (nums == 0)
	        if (ops == 1)
	        {
	            exprs += operators[curOps];
	            System.out.print(exprs);
	            evaExprs(exprs);
	        }
	        else
	            createExprs(operands, operators, exprs + operators[curOps] + " ", ops - 1, nums, curOps + 1, curNums);
	    else
	        
	        {
	          createExprs(operands, operators, exprs + operands.get(curNums) + " ", ops, nums - 1, curOps, curNums + 1);
	          if (ops > nums)
	            createExprs(operands, operators, exprs + operators[curOps] + " ", ops - 1, nums, curOps + 1, curNums);
	        }
	}
	private static void evaExprs(String exprs)
	{
		Stack<Double> St = new Stack<Double>();
		char[] Exprsch = exprs.toCharArray();
		int flag = 0;
		for (int i = 0; i < Exprsch.length; i++)
		{
			if (Exprsch[i] == ' ')
				continue;
			if (Exprsch[i] >= '0' && Exprsch[i] <= '9' )
			{
				StringBuilder num = new StringBuilder();
				while(i < Exprsch.length && Exprsch[i] >= '0' && Exprsch[i] <= '9')
					num.append(Exprsch[i++]);
				St.add(Double.parseDouble(num.toString()));				
			}
			else
			{	
				double b = St.pop();
				double a = St.pop();
				if (b == 0 && Exprsch[i] == '/' )
				{
					System.out.println(" Cannot divide by zero!");
					flag++;
					break;
				}
				
				double result = calNum(Exprsch[i], a, b);
				St.push(result);
			}
		}
		if (flag == 0)
		{
			double lResult = St.pop();
			System.out.println(" = " + lResult);
			double tempDelta = Math.abs(valTar - lResult);
			if (tempDelta < delta) 
			{
				closestVal = lResult;
				delta = tempDelta;
				closestExprs = exprs;
			}
		}
	}
	private static double calNum(char ops, double a, double b)
	{
		switch (ops)
		{
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		case '^':
			return Math.pow(a, b);
		}
		return 0;
	}
}
