import java.util.*;
import java.io.*;

public class blockWorld {
	private static Scanner scanIn;
	private static List<String> goal = new ArrayList<String>();
	private static List<List<String>> opPath = new ArrayList<List<String>>();
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
		//List<Block> blStack = new ArrayList<Block>();
		//List<Integer> canMove = new ArrayList<Integer>();
		String line = null;
		//int curStack = 0;
		while (!(line = scanIn.nextLine()).isEmpty())
		{
			//System.out.println(scanIn.nextLine());
			String str = line.substring(line.indexOf('.')+1);
			String spot = str.trim();
			/*if (spot != ""){
				/*for (int i = 0; i < spot.length(); i++)
				{
					/*Block eachBl = new Block();
					eachBl.ch = spot.charAt(i);
					if (i == spot.length() - 1)
						eachBl.isClear = true;
					eachBl.pStack =  curStack;
					blStack.add(eachBl);
				}
				//canMove.add(curStack);
			}*/
			initWorld.add(spot);
			//curStack++;
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
		iteraDeepSearch(initWorld);
		for (int i = 0; i < opPath.size(); i++)
		{
			List<String> node = new ArrayList<String>(opPath.get(i));
			for (int j = 0; j < node.size(); j++)
			{
				System.out.println( j + 1 + ". " + node.get(j));
			}
			System.out.println();
		}
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
		{
			opPath.add(node);
			return true;
		}
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
					opPath.add(newNode);
					return true;
				}
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

/*class Block {
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
}*/