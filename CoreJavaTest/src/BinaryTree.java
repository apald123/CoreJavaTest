import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class BinaryTree {
	class Node {
	    int data;
	    Node left;
	    Node right;
	    
	    public Node() {
	    	data = 0;
	    	left = null;
	    	right = null;
	    }
	    
	    public Node(int data) {
	        this.data = data;
	        left = null;
	        right = null;
	    }

		@Override
		public String toString() {
			return "Node [data=" + data + ", left=" + left + ", right=" + right
					+ "]";
		}	    	    
	}
	
	private static int currMin = -1;
	
	public void treeInsert(Node root, int newData) {
        if (newData<=root.data) {     	
            if (root.left!=null) treeInsert(root.left, newData);
            else {
            	/*
            	 * Testing for bad binary search tree
            	 */
            	//if (newData == 1) newData = 5;

            	root.left = new Node(newData);
            }
        }
        else {
        	if (root.right!=null) treeInsert(root.right, newData);
            else root.right = new Node(newData);
        }
    }

	void printNode(String msg, Node n) {
		if (n == null) {
			System.out.println(msg+"Null Node");
		} else {
			System.out.println(msg+n.data);
		}
			
	}
	
    // Do an inorder traversal to print a tree
    // Does not print the ending "\n"
    public void printTree(Node root) {    	
        if (root==null) {
        	//System.out.println("root is NULL");
        	return;
        }
        //System.out.println("Before Left Subtree root="+root.data);
        printTree(root.left);
        //System.out.println("After Left Subtree root="+root.data);
    	/*
    	System.out.println("Data root.data="+root.data+" currMin="+currMin);
        if (root.data < currMin) {
        	System.out.println("**********************");
        	System.out.println("INVALID TREE root.data="+root.data+" currMin="+currMin);
        	System.out.println("**********************");
        } else {
        	currMin = root.data;
        }
        */
        System.out.print(Integer.toString(root.data) + " ");
        //System.out.println("Before Right Subtree root="+root.data);
        printTree(root.right);
        //System.out.println("After Right Subtree root="+root.data);
    }
    
    /*
     * INORDER
     */
    public void inOrder(Node root) {
    	Deque<Node> stack = new ArrayDeque<>();
    	stack.push(root);
    	Node node = root.left;
    	while (!stack.isEmpty() || node != null) {
    		if (node != null) {
    			stack.push(node);
    			node = node.left;
    		} else {
    	    	node = stack.pop();
    	    	System.out.println(node);
    	    	node = node.right;     			
    		}
    	}    	
    }

    /*
     * PREORDER
     */

    public void preOrder(Node root) {
    	Deque<Node> stack = new ArrayDeque<>();
    	stack.push(root);
    	int max = 0;;
    	
    	while (!stack.isEmpty() ) {
    		Node node = stack.pop();
    		System.out.println(node);
    		
    		if (node.right != null) {
    			stack.push(node.right);
    		} 
    		if (node.left != null) { 
    	    	stack.push(node.left);
    		}    	
    		if (stack.size() > max) {
    			max = stack.size();
    		}
    	}    	
    	System.out.println("Tree Height="+max);
    }
    
    /*
     * POSTORDER 
     */
    public void postOrder(Node root) {
    	Deque<Node> stack = new ArrayDeque<>();
    	stack.push(root);
    	Node prev = null;    	
    	Node node = null;
    	int max = 0;
    	
    	while (!stack.isEmpty()) {
    		node = stack.peek();
    		
    		// going down
    		if (prev == null || prev.left == node || prev.right == node) {
    			if (node.left != null) {
    				stack.push(node.left);
    			} else if (node.right != null) {
    				stack.push(node.right);
    			} else {
    				System.out.println(node); //print child
    				stack.pop();
    			}    			
    		}
    		
    		// going up from left
    		if (node.left == prev) {
    			if (node.right != null) {
    				stack.push(node.right);
    			} else {
    				System.out.println(node); //print parent
    				stack.pop();
    			}
    		}
    		
    		// going up from right
    		if (node.right == prev) {
    			System.out.println(node); //print parent
    			stack.pop();
    		}
    		prev = node;
    		
    		if (stack.size() > max) {
    			max = stack.size();
    		}
    	}
    	System.out.println("Tree Height="+max);
    }

    public void toList(Node curr, Node prev, Node head) {
    	if (curr == null) return;
    	    	
    	toList(curr.left, prev, head);
    	System.out.println("Curr="+curr.data);
    	
    	curr.left = prev;  	
    	if (prev != null) {
    		System.out.println("Prev="+prev.data);
    		prev.right = curr;    		
    	}
    	else
    		head = curr; // leftmost node
    	
    	Node right = curr.right;
    	head.left = curr;
    	curr.right = head;
    	
    	printNode("Head=", head);
    	
    	prev = curr;
    	
    	toList(right, prev, head);
    	
    	return;
    }
    
    public Node treeToList(Node root) {
        printNode("Root-1=",root);
    	if (root == null) {
            return null;
        }

        Node leftTree = treeToList(root.left);
        printNode("Root-Left=",root);
        printNode("LeftTree=",leftTree);
        Node rightTree = treeToList(root.right);
        printNode("Root-Right=",root);
        printNode("RightTree=",rightTree);
        Node head;
        if (leftTree == null) {
            head = root;
        } else {
        	printNode("Update Pointer for LeftTree=",leftTree);
            head = leftTree;
            printNode("Update Pointer for LeftTree.left=",leftTree.left);
            leftTree.left.right = root;            
            root.left = leftTree.left;
        }
        if (rightTree == null) {
            head.left = root;
            root.right = head;
        } else {
            head.left = rightTree.left;
            rightTree.left.right = head;
            rightTree.left = root;
            root.right = rightTree;
        }
        printNode("Head=", head);
        return head;
    }
 // Do a traversal of the list and print it out
    public void printList(Node head) {
        Node current = head;
        
        while (current != null) {
            System.out.print(Integer.toString(current.data) + " ");
            current = current.right;
            if (current == head) break;
        }
        
        System.out.println();
    }
    
    /*
     * IS VALID BST
     */
    
	boolean isValid(Node root) {
		return isValidHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		//return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isValidBST(Node node, int MIN, int MAX) {
	     if(node == null)
	         return true;
	     if(node.data > MIN  && node.data < MAX
	         && isValidBST(node.left, MIN, Math.min(node.data,MAX))
	         && isValidBST(node.right, Math.max(node.data,MIN), MAX))
	         return true;
	     else 
	         return false;
	}

	
	boolean isValidHelper(Node curr, int min, int max) {
		//System.out.println("START curr="+curr.data+" min="+min+" max="+max);
		if (curr != null) {
			if (curr.data < min || curr.data > max
					|| !isValidHelper(curr.left, min, curr.data))
				return false;
		}
		
		if (curr != null) {
			if (curr.data < min || curr.data > max
					|| !isValidHelper(curr.right, curr.data, max))
				return false;
		}
		return true;
	}
	
	/*
	 * SORTED ARRAY TO BST
	 */
	
	public Node sortedArrayToTree(int[] arr) {
		return sortedArrayToTree(arr, 0, arr.length-1);
	}
	
	private Node sortedArrayToTree(int[] arr, int start, int end) {
		if (start > end) return null;
		
		int mid = start + (end-start)/2;
		Node node = new Node(arr[mid]);
		
		node.left = sortedArrayToTree(arr, start, mid-1);
		node.right = sortedArrayToTree(arr, mid+1, end);
		
		return node;
	}

	/*
	 * SORTED LIST TO TREE
	 */
	public Node sortedListToBST(List<Integer> list) {
		int n = list.size();
		Iterator<Integer> itr = list.iterator();
		return sortedListToBST(0, n-1, itr);
	}

	private Node sortedListToBST(int lb, int hb,Iterator<Integer> itr) {
		System.out.println("START Low="+lb+" High="+hb);
		if (lb > hb) return null;
        // same as (lb+hb)/2, avoids overflow
        int mid = lb + (hb - lb) / 2;

        final Node treeNode = new Node();
        treeNode.left = sortedListToBST(lb, mid-1, itr);
        System.out.println("After Left treeNode.left="+treeNode.left);
        treeNode.data = itr.next();
        System.out.println("Setting Data treeNode.data="+treeNode.data);
        treeNode.right = sortedListToBST(mid + 1, hb, itr);
        System.out.println("After Right treeNode.left="+treeNode.left);

        return treeNode;
	}	 
	
	/*
	 * FIND LCA - BST
	 */
	
	public static Node findLCA(Node root, Node one, Node two) {
		if (root == null || one == null || two == null) {
			return null;
		}
		
		if (Math.max(one.data, two.data) < root.data) {
			return findLCA(root.left, one, two);
		} else if (Math.min(one.data, two.data) > root.data) {
			return findLCA(root.right, one, two);
		} else {
			return root;
		}
	}
	
	/*
	 * PRINT LEVEL ORDER BFS
	 */
	
	public void printLevelOrder(Node root) {
		Queue<Node> level = new LinkedList<>();
		int curCount = 0;
		int nextCount = 0;
		int numLevels = 0;
		level.add(root);
		curCount++;
		while (!level.isEmpty()) {
			Node node = level.remove();
			curCount--;
			System.out.print(node.data+" ");
			if (node.left != null) {
				level.add(node.left);
				nextCount++;
			}
			if (node.right != null) {
				level.add(node.right);
				nextCount++;
			}			
			
			if (curCount == 0) {
				curCount = nextCount;
				nextCount = 0;
				System.out.println("");
				numLevels++;
			}
		}
		System.out.println("Number of Levels="+numLevels);
	}

	/*
	 * PRINT LEVEL ORDER DFS
	 */
	public void printLevelOrder(Node root, int height) {
		for (int i=1; i<=height; i++) {
			printLevel(root, i);
			System.out.println(" ");
		}
	}
	
	private void printLevel(Node node, int level) {
		System.out.println("PRINT LEVEL Node="+node.data+" Level="+level);
		if (level == 1) {
			if (node != null) {
				System.out.print(node.data+" ");
			}
			return;
		}
		
		if (node != null)
			printLevel(node.left, level-1);
		System.out.println("AFTER Left Node="+node.data+" Level="+level);
		if (node != null)
			printLevel(node.right, level-1);
		System.out.println("AFTER Right Node="+node.data+" Level="+level);
	}
	
	/*
	 * MAX HEIGHT
	 */
	public int maxHeight(Node node)
	{		
	    if (node == null)
	    {
	    	System.out.println("Node is NULL");
	        return 0;
	    }
	    else
	    {
	    	System.out.println("* Before Left Height Node="+node.data);
	    	int lh = maxHeight(node.left);
	    	System.out.println("** After Left Height Node="+node.data+" LH="+lh);
	    	
	    	System.out.println("+ Before Right Height Node="+node.data);
	    	int rh = maxHeight(node.right);
	    	System.out.println("++ After Right Height Node="+node.data+" RH="+rh);
	        
	    	return 1 +  Math.max(lh, rh);
	    }
	}
	
    public static void main(String[] args) {
        
    	BinaryTree btree = new BinaryTree();
    	
    	Node root = btree.new Node(8);
    	btree.treeInsert(root, 3);
    	btree.treeInsert(root, 10);
    	btree.treeInsert(root, 1);
    	btree.treeInsert(root, 5);
    	btree.treeInsert(root, 4);
    	
    	System.out.println("Max Height -------------");
    	System.out.println(btree.maxHeight(root));
    	//System.out.println("In Order --------------");
    	//btree.inOrder(root);
    	Node lca = btree.findLCA(root, btree.new Node(1), btree.new Node(5));
    	//System.out.println("LCA --------------");
    	//System.out.println(lca);
    	System.out.println("Level Order -------------");
    	btree.printLevelOrder(root);
    	System.out.println("Pre Order --------------");
    	btree.preOrder(root);

    	System.out.println("Post Order --------------");
    	btree.postOrder(root);
    	
        // first build the tree shown in the problem document
        // http://cslibrary.stanford.edu/109/
    	/*
        Node root = btree.new Node(4);
        btree.treeInsert(root, 6);
        btree.treeInsert(root, 1);
        */
        //btree.treeInsert(root, 2);
        //btree.treeInsert(root, 1);
        //btree.treeInsert(root, 3);
        //btree.treeInsert(root, 5);
        
    	/*
        System.out.println("tree:");
        btree.printTree(root);   // 1 2 3 4 5
    	System.out.println("");
    	System.out.println("IsValid="+btree.isValid(root));

        System.out.println("\n------------------------\n");

    	int[] arr = {1,3,5,6,7,8,9,10,13};
    	Node tree = btree.sortedArrayToTree(arr);
    	btree.printTree(tree);
    	*/
    	List<Integer> list = new ArrayList<>();
    	list.add(1);
    	list.add(2);
    	list.add(5);
    	list.add(6);
    	list.add(8);
    	//Node tree = btree.sortedListToBST(list);
    	//btree.printTree(tree);
    	    	
    }
    
    
}
