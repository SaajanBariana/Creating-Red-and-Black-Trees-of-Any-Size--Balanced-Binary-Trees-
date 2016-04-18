package sjsu.Bariana.cs146.project2;

import static org.junit.Assert.*; 
import org.junit.Test;
public class RBTTester 
{
	@Test 
	public void test() 
	{
		RedBlackTree rbt = new RedBlackTree();
		rbt.addNode("D");
		rbt.addNode("B");
		rbt.addNode("A");
		rbt.addNode("C");
		rbt.addNode("F");
		rbt.addNode("E");
		rbt.addNode("H");
		rbt.addNode("G");
		rbt.addNode("I");
		rbt.addNode("J"); 
		assertEquals("DBACFEHGIJ", makeString(rbt));
		rbt.printTree();
		String str= "Color: 1, Data:D Parent: \n"+
					"Color: 1, Data:B Parent: D\n"+ 
					"Color: 1, Data:A Parent: B\n"+ 
					"Color: 1, Data:C Parent: B\n"+ 
					"Color: 1, Data:F Parent: D\n"+ 
					"Color: 1, Data:E Parent: F\n"+ 
					"Color: 0, Data:H Parent: F\n"+ 
					"Color: 1, Data:G Parent: H\n"+ 
					"Color: 1, Data:I Parent: H\n"+
					"Color: 0, Data:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));
		
	}
	
	public static String makeString(RedBlackTree t)  
	{ 
		class MyVisitor implements RedBlackTree.Visitor1
		{         
			String result = ""; 
			public void visit(RedBlackTree.Node n)
			{    
				result = result + n.data;   
			}     
		};      
		MyVisitor v = new MyVisitor();
		t.preOrderVisit(v);
		return v.result; 
	}
	
	
	public static String makeStringDetails(RedBlackTree t)
	{    
		 
			class MyVisitor implements RedBlackTree.Visitor1
			{    
				String result = "";   
				public void visit(RedBlackTree.Node n)   
				{    
					if(!(n.data).equals(""))
					{
						if (n.parent != null)
						{
							result = result +"Color: "+n.color+", Data:"+n.data+" Parent: "+n.parent.data+"\n";                
						}
						else
						{
							result = result +"Color: "+n.color+", Data:"+n.data+" Parent: \n";
						}
					}
				}
	         };  
	         MyVisitor v = new MyVisitor();    
	         t.preOrderVisit(v); 
	         return v.result; 
	         
	}

	
	
}  
      