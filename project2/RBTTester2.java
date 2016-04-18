/**
 * 
 */
package sjsu.Bariana.cs146.project2;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Saajan Bariana
 *
 */
public class RBTTester2 
{
	RedBlackTree tree = new RedBlackTree();
	Scanner in;

	@Test
	public void test2() throws FileNotFoundException 
	{
		long time = System.currentTimeMillis();
		in = new Scanner(new File("Dictionary"));
		while(in.hasNext())
		{
			String line = in.next();
			tree.addNode(line.toLowerCase());
		}
		long create = System.currentTimeMillis() - time;
		in = new Scanner(new File("TheConstitution"));
		String notInDict = "";
		time = System.currentTimeMillis();
		while(in.hasNextLine())
		{
			String newline = in.nextLine();
			String[] words = newline.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
			for (String i: words)
			{
				 if (tree.searchTree(i) == -1 && !i.isEmpty()) 
				 {
					 notInDict += i + "\n";
				 }
			}
		}
		long search = System.currentTimeMillis() - time;
		System.out.print("The following words are not in the dictionary: \n" + notInDict + "\n");
		System.out.println(create + " milliseconds to create the Dictionary Tree");
		System.out.println(search + " milliseconds to search through the Dictionary Tree");

	}
}
