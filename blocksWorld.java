import java.util.*;
import java.io.*;

public class blocksWorld {
	private static Scanner scanIn;
	private static List<String> goal = new ArrayList<String>();
	//store the array of optimal path to the goal
	private static List<List<String>> opPath = new ArrayList<List<String>>();
	// count the recursive call
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
		//read the initial state of block world 
		while (!(line = scanIn.nextLine()).isEmpty())
		{
			String str = line.substring(line.indexOf('.')+1);
			String spot = str.trim();
			initWorld.add(spot);
		}
		//read the goal of block world
		while (scanIn.hasNextLine())
		{
			line = scanIn.nextLine();
			String str = line.substring(line.indexOf('.')+1);
			String spot = str.trim();
			goal.add(spot);
		}
		//find the optimal path
		iteraDeepSearch(initWorld);
		opPath.add(initWorld);
		//and print to console
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
		// increase the litmit until find the goal
		for (int i = 0; i < Integer.MAX_VALUE; i++)
			if (depthLimitSearch(initWorld, i))
				break;
	}
	public static boolean depthLimitSearch(List<String> node, int limit)
	{
		//return true if find the goal successfully
		if (testGoal(node))
			return true;
		//or false if reach the limit 
		if (limit <= 0)
			return false;
		//create an array of the position where spot is not empty
		List<Integer> canMove = new ArrayList<Integer>();
		for (int i = 0; i < node.size(); i++)
			if (!node.get(i).isEmpty())
				canMove.add(i);
		//for each position where spot not empty pop the block on top and push to
		//the first spot, do the loop until reach the last spot
		for (int block = 0; block < canMove.size(); block++)
		{
			int bl = canMove.get(block);
			//if current block belongs to current spot then skip
			for (int spot = 0; spot < node.size(); spot++)
			{
				if (spot == bl)
					continue;
				List<String> newNode = new ArrayList<String>(node);
				//pop the block on top of the spot
				String newSpot = node.get(bl);
				newNode.set(bl, newSpot.substring(0, newSpot.length() - 1));
				//and push that block to new spot
				char moveBl = node.get(bl).charAt(node.get(bl).length() - 1);
				newNode.set(spot, newNode.get(spot) + moveBl);
				//do recursive call until find the goal successful
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
	//test whether the current node duplicate with the goal or not
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