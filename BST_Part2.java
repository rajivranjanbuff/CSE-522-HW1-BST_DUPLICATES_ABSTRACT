//CSE 522: Homework 1, Part 2

  class BST_Part2 {

	  public static void main(String[] args) {
			AbsTree tr = new DupTree(100);
			 tr.insert(50);
			 tr.insert(125);
			 tr.insert(150);
			 tr.insert(25);
			 tr.insert(75);
			 tr.insert(20);	
			 tr.insert(90);
			 tr.insert(50);
			 tr.insert(125);
			 tr.insert(150);
			 tr.insert(25);
			 tr.insert(75);
			 tr.insert(20);	
			 tr.insert(90);
	
			 tr.delete(20);
			 tr.delete(20);
			 tr.delete(20);
			 tr.delete(150);
			 tr.delete(100);
			 tr.delete(150);
			 tr.delete(125);
			 tr.delete(125);
			 tr.delete(50);
			 tr.delete(50);
			 tr.delete(25);
			 tr.delete(50);
			 tr.delete(75);
			 tr.delete(90);
			 tr.delete(25);
			 tr.delete(50);
			 tr.delete(75);
			 tr.delete(90);
		}
  }
  
    abstract class AbsTree {

	public AbsTree(int n) {
		value = n;
		left = null;
		right = null;
	}
//inserting nodes
	public void insert(int n) {
		if (value == n)
			count_duplicates();
		else if (value < n)
			if (right == null)
				right = add_node(n);
			else
				right.insert(n);
		else if (left == null)
			left = add_node(n);
		else
			left.insert(n);
	}
	//delete function
	public void delete(int n) { // assume > 1 nodes in tree
		AbsTree t = find(n);
		
		if (t == null) {
			System.out.println("Not in the tree");
			return;
			// print out error message and return
		}
		if(t.getCount()>1) {
			t.countDecr();
		}
		else if(t.getCount()==1) {
		AbsTree root = this;

		if (root != t) {
			while (true) {

				if (root == null || root.left == t || root.right == t) {
					break;
				}

				if (root.value < t.value) {
					root = root.right;
				}
				else
					root = root.left;
			}
		}

		if (t.left == null && t.right == null) {
			case1(t, root);
			return;

		}
		if (t.left == null || t.right == null) {
			if (t != this) {
				case2(t, root);
				return;
			} else {
				case3(t, "left");
				return;
			}
		}
		// do case3 and return
		case3(t, "left");
		
		
		}
	}
	
	protected void case1(AbsTree t, AbsTree root) {  
		
		if(t==root) {
			System.out.println("tree will become empty");
			return;
		}
		if (root.right == t) {
			root.right = null;
		} else
			root.left = null;
		return;	
		
	}
	
	protected void case2(AbsTree t, AbsTree root) {  
		if (t.right != null) {
			if (t == root.right) {
				root.right = t.right;
			} else {
				root.left = t.right;
			}
		} else {
			if (t == root.right) {
				root.right = t.left;
			} else {
				root.left = t.left;
			}
		}
		return;
	}
	
	protected void case3(AbsTree t, String which) { 
		    AbsTree root = this;
		    if(root.left != null) {
		    	AbsTree big = t.left.max();
			if(big !=null) //if left side is not null
			{
			if (root != t) {
				while (true) {

					if (root == null || root.left == t || root.right == t) {
						break;
					}

					if (root.value < t.value) {
						root = root.right;
					}

					else
						root = root.left;
				}
			}
			t.value = big.value;
			t.setCount(big.getCount());
			root = t;
			// t=big;
			if (big.left == null && big.right == null) {
				while (true) {

					if (root == null || (root.left != null && root.left.value == t.value)
							|| (root.right != null && root.right.value == t.value)) {
						break;
					}

					if (root.value < t.value) {
						root = root.right;
					}

					else
						root = root.left;
				}

				case1(big, root);
				return;
			} else {
				case2(big, root);
				return;
			}
		}
		    }
	
	else {//if the max in left could not be found as it was null go to right to find minimum value there
		AbsTree small = t.right.min();
		if (root != t) {
			while (true) {

				if (root == null || root.left == t || root.right == t) {
					break;
				}

				if (root.value < t.value) {
					root = root.right;
				}

				else
					root = root.left;
			}
		}
		t.value = small.value;
		t.setCount(small.getCount());
		root = t;
		// t=big;
		if (small.left == null && small.right == null) {
			while (true) {

				if (root == null || (root.left != null && root.left.value == t.value)
						|| (root.right != null && root.right.value == t.value)) {
					break;
				}

				if (root.value < t.value) {
					root = root.right;
				}

				else
					root = root.left;
			}

			case1(small, root);
			return;
		} else {
			case2(small, root);
			return;
		}
	}
	}
		
	
	

	private AbsTree find(int n) {
		AbsTree temp = this;
		while (temp != null) {
			if (temp.value == n)
				return temp;
			if (temp.value > n) {
				temp = temp.left;
			} else {
				temp = temp.right;
			}
		}
		return null; // if not present
		
	}
	
	public AbsTree min() {
		AbsTree temp = this;
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}

	public AbsTree max() {
		AbsTree temp = this;
		while (temp.right != null)
			temp = temp.right;
		return temp;
	}

	protected int value;
	protected AbsTree left;
	protected AbsTree right;

	// Protected Abstract Methods
	
	protected abstract AbsTree add_node(int n);
	protected abstract void count_duplicates();
	protected abstract int getCount();
	protected abstract void setCount(int i);
	protected abstract void countDecr();

	// additional protected abstract methods, as needed
}


class Tree extends AbsTree {

	public Tree(int n) {
		super(n);
	}

	protected AbsTree add_node(int n) {
		return new Tree(n);
	}

	protected void count_duplicates() {
		;
	}
	
	protected int getCount()
	{
		return 1;
	}
	protected void setCount(int i)
	{
		return;
	}
	protected void countDecr()
	{
       return;
	}
	// additional protected methods, as needed

}

class DupTree extends AbsTree {

	public DupTree(int n) {
		super(n);
		count = 1;
	};

	protected AbsTree add_node(int n) {
		return new DupTree(n);
	}

	protected void count_duplicates() {
		count++;
	}

	protected int getCount()
	{
		return count;
	}// additional protected methods, as needed
	protected void countDecr()
	{
		count--;
		return;
	}
	
	protected void setCount(int i)
	{
		count=i;
	}
	protected int count;
}