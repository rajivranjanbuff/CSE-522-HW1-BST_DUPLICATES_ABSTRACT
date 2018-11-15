
// CSE 522: Homework 1, Part 1

class BST_Part1 {
	public static void main(String args[]) {
		Tree tr;
		tr = new Tree(100);
		 tr.insert(50);
		 tr.insert(125);
		 tr.insert(150);
		 tr.insert(25);
		 tr.insert(75);
		 tr.insert(20);	
		 tr.insert(90);
		 tr.delete(20);
		 tr.delete(20);
		 tr.delete(125);
		 tr.delete(150);
		 tr.delete(100);
		 tr.delete(50);
		 tr.delete(75);
		 tr.delete(25);
		 tr.delete(90);
		
	}
}

class Tree { // Defines one node of a binary search tree

	public Tree(int n) {
		value = n;
		left = null;
		right = null;
	}

	public void insert(int n) {
		if (value == n)
			return;
		if (value < n)
			if (right == null)
				right = new Tree(n);
			else
				right.insert(n);
		else if (left == null)
			left = new Tree(n);
		else
			left.insert(n);
	}

	public Tree min() {
		// returns the Tree node with the minimum value
		// you should write the code
		Tree temp = this;
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}

	public Tree max() {
		// returns the Tree node with the maximum value
		// you should write the code
		Tree temp = this;
		while (temp.right != null)
			temp = temp.right;
		return temp;
	}

	public Tree find(int n) {
		// returns the Tree node with value n
		// returns null if n is not present in the tree
		// you should write the code
		Tree temp = this;
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

	public void delete(int n) {
		Tree t = find(n);

		if (t == null) {
			System.out.println("Not in the tree");
			return;
			// print out error message and return
		}
		Tree root = this;

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

	private void case1(Tree t, Tree root) {
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

	private void case2(Tree t, Tree root) {
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

	private void case3(Tree t, String which) {
		    Tree root = this;
			if(root.left != null) {
			Tree big = t.left.max();
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
		else { //if the max in left could not be found as it was null go to right to find minimum value there
			Tree small = t.right.min();
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
			t.value=small.value;
			root = t;
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
	
	protected int value;
	protected Tree left;
	protected Tree right;
}
