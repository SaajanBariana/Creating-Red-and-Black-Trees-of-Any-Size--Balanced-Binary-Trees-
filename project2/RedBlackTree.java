package sjsu.Bariana.cs146.project2;

import java.util.List;

public class RedBlackTree {

	private Node root;
	private static final int BLACK = 1;
	private static final int RED = 0;

	/**
	 * the constructor
	 */
	public RedBlackTree() 
	{
		//do nothing
	}

	/**
	 * prints the tree in preorder
	 */
	public void printTree()
	{
		root.printTree();
	}

	/**
	 * searches through the tree for some word
	 * @param string the string it searches for
	 * @return returns 1 if it found the word and -1 if it did not
	 */
	public int searchTree(Comparable string)
	{
		Node temp = new Node(string);
		return root.searchTree(temp);
	}

	/**
	 * adds in a word into the tree
	 * @param info the word it wants to add in
	 */
	public void addNode(Comparable info)
	{
		Node temp = new Node(info);
		temp.color = RED;
		if (root != null)
		{
			root.addNode(temp);
		}
		else
		{
			root = temp;
			temp.color = BLACK;
		}
		fixTree(temp);
	}

	/**
	 * gets the Sibling of that node
	 * @param n the node it wants a sibling for
	 * @return returns the sibling
	 */
	public Node getSibling(Node n)
	{
		return n.getSibling();
	}

	/**
	 * gets the Aunt of that node
	 * @param n the node you want the aunt for
	 * @return returns the aunt
	 */
	public Node getAunt(Node n)
	{
		return n.getAunt();
	}

	/**
	 * gets the grandparent of that node
	 * @param n the node you want the grandparent of
	 * @return returns the grandparent
	 */
	public Node getGranparent(Node n)
	{
		return n.getGrandparent();
	}

	/**
	 * rotates the tree to the left
	 * @param n the node you want to rotate around
	 */
	public void rotateLeft(Node n)
	{		 
		Node thechild = n.right;
		n.right = thechild.left;
		if (thechild.left != null)
		{
			thechild.left.parent = n;
		}
		thechild.parent = n.parent;
		if (root == n)
		{
			root = thechild;
		}
		else if (n == n.parent.left)
		{
			n.parent.left = thechild;
		}
		else
		{
			n.parent.right = thechild;
		}
		thechild.left = n;
		n.parent = thechild;
	}

	/**
	 * rotates the node to the right
	 * @param n the node you want to rotate around
	 */
	public void rotateRight(Node n)
	{		 
		Node thechild = n.left;
		n.left = thechild.right;
		if (thechild.right != null)
		{
			thechild.right.parent = n;
		}
		thechild.parent = n.parent;
		if (root == n)
		{
			root = thechild;
		}
		else if (n == n.parent.right)
		{
			n.parent.right = thechild;

		}
		else
		{
			n.parent.left = thechild;
		}

		thechild.right = n;
		n.parent = thechild;
	}

	/**
	 * resets the colors and the position of the tree if it is unbalanced
	 * @param temp the node you just added in or want to check for to make sure the tree is balanced
	 */
	public void fixTree(Node temp)
	{
		if (temp == root)
		{
			temp.color = BLACK;
		}
		else if (temp.parent.color == BLACK)
		{
			//do nothing
		}

		//case 1
		else if (temp.getAunt() == null || temp.getAunt().color == BLACK)
		{
			//case A
			if (temp.parent == temp.getGrandparent().left && temp == temp.parent.right)
			{
				rotateLeft(temp.parent);
				fixTree(temp.parent);					
			}
			//case B
			else if(temp.parent == temp.getGrandparent().right && temp == temp.parent.left) 
			{
				rotateRight(temp.parent);
				fixTree(temp.parent);
			}
			//case C
			else if (temp.parent == temp.getGrandparent().left && temp == temp.parent.left)
			{
				temp.parent.color = BLACK;
				temp.getGrandparent().color = RED;
				rotateRight(temp.getGrandparent());
			}
			//case D
			else if (temp.parent == temp.getGrandparent().right && temp == temp.parent.right)
			{
				temp.parent.color = BLACK;
				temp.getGrandparent().color = RED;
				rotateLeft(temp.getGrandparent());
			}
		}
		//case 2
		else 
		{
			temp.parent.color = BLACK;
			temp.getGrandparent().color = RED;
			temp.getAunt().color = BLACK;
			fixTree(temp.getGrandparent());
		}
	}


	class Node
	{
		public Node left;
		public Node right;
		public int color;
		public Comparable data;
		public Node parent;

		/**
		 * the constructor of the Node class
		 * @param info the data for the node
		 */
		public Node(Comparable info)
		{
			data = info;
		}

		/**
		 * searching for a node throughout the tree
		 * @param n the node you are searching for
		 * @return return -1 if not found and 1 if found
		 */
		public int searchTree(Node n)
		{
			Node temp = this;
			while(temp != null)
			{
				if (temp.data.compareTo(n.data) == 0)
				{
					return 1;
				}
				else if (temp.data.compareTo(n.data) < 0)
				{
					temp = temp.right;
				}
				else
				{
					temp = temp.left;
				}
			}
			return -1;
		}

		/**
		 * Add the node into the correct position in the tree
		 * @param temp the node you want to add in
		 */
		public void addNode(Node temp)
		{
			if (this.data.compareTo(temp.data) < 0)
			{
				if (this.right != null)
				{
					this.right.addNode(temp);
				}
				else
				{
					this.right = temp;
					this.right.parent = this;
				}
			}
			else if (this.data.compareTo(temp.data) >= 0)
			{
				if (this.left != null)
				{
					this.left.addNode(temp);
				}
				else
				{
					this.left = temp;
					this.left.parent = this;
				}
			}
		}

		/**
		 * get the sibling of the current node
		 * @return returns the sibling
		 */
		public Node getSibling()
		{
			if (this.parent != null)
			{
				if (this.data.compareTo(this.parent.data) >= 0)
				{
					return this.parent.left;
				}
				else
				{
					return this.parent.right;
				}
			}
			else
			{
				return null;
			}
		}

		/**
		 * finds the Aunt of the node
		 * @return returns the aunt of the node
		 */
		public Node getAunt()
		{
			if (this.parent != null)
			{
				return this.parent.getSibling();
			}
			else
			{
				return null;
			}
		}

		/**
		 * finds the grandparent of the node
		 * @return returns the grandparent
		 */
		public Node getGrandparent()
		{
			if (this.parent != null)
			{
				return this.parent.parent;
			}
			else
			{
				return null;
			}
		}

		/**
		 * prints out the tree in preorder
		 */
		public void printTree()
		{
			System.out.println(this.data);
			if (this.left != null)
			{
				this.left.printTree();
			}
			if (this.right != null)
			{
				this.right.printTree();
			}
		}	      
	}
	// add this in your class  
	public static interface Visitor1   
	{   
		/**
		This method is called at each node.  
		@param n the visited node   
		 */ 
		void visit(Node n);   
	} 
	public void preOrderVisit(Visitor1 v)  
	{   
		preOrderVisit(root, v);  
	}   
	private static void preOrderVisit(Node n, Visitor1 v)   
	{   
		if (n == null) return;  
		v.visit(n);  
		preOrderVisit(n.left, v);
		preOrderVisit(n.right, v);
	}

}
