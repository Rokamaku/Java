import java.util.*;
import java.io.*;

public class blocksWorld {
	private static Scanner scanIn;
	private static List<String> goal = new ArrayList<String>();
	private static List<List<String>> opPath = new ArrayList<List<String>>();
	private static int recurCall = 0; 
	public static void main(String[] args) throws FileNotFoundException
	{
		scanIn = new Scanner(System.in);
		String filename = null;
		System.out.println("Blocks world program by Bui Hoang Anh");
		System.out.print("Enter an input file: ");
		filename = scanIn.nextLine();
		FileInputStream	fis = new FileInputStream(filename);
		scanIn = new Scanner(fis);
		List<String> initWorld = new ArrayList<String>();
		String line = null;
		while (!(line = scanIn.nextLine()).isEmpty())
		{
			String str = line.substring(line.indexOf('.')+1);
			String spot = str.trim();
			initWorld.add(spot);
		}
		while (scanIn.hasNextLine())
		{
			line = scanIn.nextLine();
			String str = line.substring(line.indexOf('.')+1);
			String spot = str.trim();
			goal.add(spot);
		}
		iteraDeepSearch(initWorld);
		opPath.add(initWorld);
		for (int i = opPath.size() - 1 ; i >= 0; i--)
		{
			List<String> node = new ArrayList<String>(opPath.get(i));
			for (int j = 0; j < node.size(); j++)
			{
				System.out.println( j + 1 + ". " + node.get(j));
			}
			System.out.println();
		}
		System.out.println("Optimal solution has " + (opPath.size() - 1) + " moves");
		System.out.println(recurCall + " total recursive calls");
	}
	public static void iteraDeepSearch(List<String> initWorld)
	{
		for (int i = 0; i < Integer.MAX_VALUE; i++)
		{
			if (depthLimitSearch(initWorld, i))
				break;
		}
	}
	public static boolean depthLimitSearch(List<String> node, int limit)
	{
		if (testGoal(node))
			return true;
		if (limit <= 0)
			return false;
		List<Integer> canMove = new ArrayList<Integer>();
		for (int i = 0; i < node.size(); i++)
			if (!node.get(i).isEmpty())
				canMove.add(i);
		for (int i = 0; i < canMove.size(); i++)
		{
			int bl = canMove.get(i);
			for (int j = 0; j < node.size(); j++)
			{
				if (j == bl)
					continue;
				List<String> newNode = new ArrayList<String>(node);
				String newSpot = node.get(bl);
				newNode.set(bl, newSpot.substring(0, newSpot.length() - 1));
				char moveBl = node.get(bl).charAt(node.get(bl).length() - 1);
				newNode.set(j, newNode.get(j) + moveBl);
				if (depthLimitSearch(newNode, limit - 1))
				{
					recurCall++;
					opPath.add(newNode);
					return true;
				}
				else
					recurCall++;
			}
			
		}
		return false;
	}
	private static boolean testGoal(List<String> node)
	{
		for (int i = 0; i < goal.size(); i++)
		{
			String spotNode = node.get(i);
			if (!spotNode.equals(goal.get(i)))
				return false;
			
		}
		return true;
	}
}