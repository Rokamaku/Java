import java.util.*;
import java.io.*;

public class blockWorld {
	private static Scanner scanIn;
	private static List<String> goal = new ArrayList<String>();
	public static void main(String[] args) throws FileNotFoundException
	{
		scanIn = new Scanner(System.in);
		//String filename = "";
		System.out.println("Blocks world program by Bui Hoang Anh");
		//System.out.print("Enter an input file: \n");
		//filename = scanIn.nextLine();
		FileInputStream	fis = new FileInputStream("/home/parakoda/Input.txt");
		scanIn = new Scanner(fis);
		List<String> initWorld = new ArrayList<String>();
		List<Block> blStack = new ArrayList<Block>();
		String line = null;
		int curStack = 0;
		while (!(line = scanIn.nextLine()).isEmpty())
		{
			//System.out.println(scanIn.nextLine());
			String str = line.substring(line.indexOf('.')+1);
			String spot = str.trim();
			if (spot != ""){
				for (int i = 0; i < spot.length(); i++)
				{
					Block eachBl = new Block();
					eachBl.ch = spot.charAt(i);
					if (i == spot.length() - 1)
						eachBl.isClear = true;
					eachBl.pStack =  curStack;
					blStack.add(eachBl);
				}
			}
			initWorld.add(spot);
			curStack++;
		}
		/*Iterator<String> ite = initWorld.iterator();
		while (ite.hasNext())
		{
			System.out.println(ite.next());
		}*/
		while (scanIn.hasNextLine())
		{
			line = scanIn.nextLine();
			String str = line.substring(line.indexOf('.')+1);
			String spot = str.trim();
			goal.add(spot);
		}
		/*Iterator<String> ite = goal.iterator();
		while (ite.hasNext())
		{
			System.out.println(ite.next());
		}*/
		iteraDeepSearch(initWorld, blStack);
	}
	public static void iteraDeepSearch(List<String> initWorld, List<Block> blStack)
	{
		for (int i = 0; i < Integer.MAX_VALUE; i++)
		{
			if (depthLimitSearch(initWorld, blStack, i))
				break;
		}
	}
	public static boolean depthLimitSearch(List<String> node, List<Block> blStack, int limit)
	{
		if (testGoal(node))
			return true;
		if (limit <= 0)
			return false;
		for (int i = 0; i < blStack.size(); i++)
		{
			if (!blStack.get(i).isClear)
				continue;
			Block temp = blStack.get(i);
			for (int j = 0; j < node.size(); j++)
			{
				if (j == temp.pStack) continue;
				List<String> newNode = new ArrayList<String>(node);
				newNode.set(j, node.get(j) + temp.ch); 
				String pop = node.get(temp.pStack);
				newNode.set(temp.pStack, pop.substring(0, pop.length() - 1));
				Block updateBl = new Block(temp);
				updateBl.pStack = j;
				List<Block> newBlStack = new ArrayList<Block>(blStack);
				newBlStack.set(i, updateBl);
				if (depthLimitSearch(newNode, newBlStack, limit - 1))
					return true;
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

class Block {
	Block() {
		isClear = false;
	}
	Block(Block other){
		ch = other.ch;
		isClear = other.isClear;
		pStack = other.pStack;
	}
	char ch;
	boolean isClear;
	int pStack;
}
