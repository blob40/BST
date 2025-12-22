//Alex Krouse
//12/9/25
//Binary Search Tree Implementation, creating insert, find, remove and toString methods

import java.util.ArrayList;

class BST {
    Node root;

    public BST()
    {
          root = null;
    }

   
//pre -condition: The tree is a valid binary search tree
//post-condition: inserts the key into the tree and maintains the proper rules in a BST
    void insert(int key){
        if (root == null){
            root = new Node(key);
    }
        Node temp = root;
        while(true){
            if(temp.key == key){
                break;
            }
           // System.out.println("chicken");
             if (temp.key > key){
                if (temp.left == null){
                temp.left = new Node(key);
                break;
                }  else {
                    temp = temp.left;
            }
        } else  if (temp.key < key ){
                if (temp.right == null){
                temp.right = new Node(key);
                break;
                
            } else {
                temp = temp.right;
            }
        }
    }
}


//pre-condition: The tree is a valid binary search tree
//post-condition: Returns true if the key is found in the tree, false if not
    boolean find(int key){
            return find(key, root);
        }

    private boolean find(int key, Node n){
            if(n == null)
                return false;
            if (n.key == key){
                return true;
            }
            if(key < n.key){
                return find(key, n.left);
                
            }
            if(key > n.key){
                return find(key, n.right);
            }
            return false;
        }

        
        //)Pre-condition: The tree is a valid binary search tree.
        //Post-condition: If the key is found in the tree and it is remooved
    void remove(int key){
        Node above = root;
        Node current = root;
        

        //If key is not found
        if (find(key) == false || root == null){
            return;
        }
      

        //no children
        while(key != current.key){
            if (key < current.key){
            if (current.key != key){
                    above = current;
                    current = current.left;
                }
            } else if (key > current.key){
                if (current.key != key){
                    above = current;
                    current = current.right;
                }
            }
        }
       
        if(current.key == key && current.left == null && current.right == null){
            if (above.left == current){
                above.left = null;
            } else {
                above.right = null;
            }
            above=null;
            return;
        } 



        //one child 
        if(current.key == key && ((current.left != null && current.right == null) || (current.left == null && current.right != null))){
            Node child;
            if (current.left != null) {
                child = current.left;
            } else {
                child = current.right;
            }
            if (above.left == current) {
                above.left = child;
            } else {
                above.right = child;
            }
        }

        //two children
        //TODO
        if(current.key == key && current.left != null && current.right != null){
           Node child = current.left;
           Node parent = current;
           while (child.right != null){
            parent = child;
            child = child.right;
           }
            current.key = child.key;

             if (parent.left == child) {
                parent.left = child.left;
            } else {
                parent.right = child.left;
            }
        }
        return;
    }

    public String toString(){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        toString(root, 0, list);
        String result = "";
        for (ArrayList<Integer> lists : list) {
            result += lists.toString() + "\n";
        }
        return result;
    }

    private void toString(Node n, int num, ArrayList<ArrayList<Integer>> list) {
        if (n == null) return;
        if (list.size() <= num) {
            list.add(new ArrayList<>());
        }
        list.get(num).add(n.key);
        toString(n.left, num + 1, list);
        toString(n.right, num + 1, list);
    }

    protected void rotateLeft(Node subRoot, Node prev){

        if (subRoot == null || subRoot.right == null){
            return;
        }

        if(prev == null){
            root = subRoot.right;
            subRoot.right = root.left;
            root.left = subRoot;
           
        } else if (prev.left == subRoot){
            Node x = subRoot.right;
            prev.left = x;
            subRoot.right = x.left;
            x.left = subRoot;
        } else {
            Node t = subRoot.right;  
            prev.right = t;
            subRoot.right = t.left;
            t.left = subRoot;
           
        }
           
        }
    

    protected void rotateRight(Node subRoot, Node prev){
         if (subRoot == null || subRoot.left == null){
            return;
        }

        if(prev == null){
            root = subRoot.left;
            subRoot.left = root.right;
            root.right = subRoot;
        }  else if (prev.left == subRoot ){
            Node x = subRoot.left;
            prev.left = x;
            subRoot.left = x.right;
            x.right = subRoot;
        } else {
            Node t = subRoot.left;  
            prev.right = t;
            subRoot.right = t.left;
            t.left = subRoot;
        }
    }


    //Pre-condition: The tree is a valid binary search tree.
    //Post-condition: The tree is balanced.
    protected int balance(Node node){
        //height of right - height of left
        if (node == null){
        return 0;} else {
            return height(node.right) - height(node.left);
        }
    }

    //Pre-condition: The tree is a valid binary search tree.
    //Post-condition: Returns the height of the tree.
    private int height(Node node){
        if (node == null){
            return -1;
        } else {
            if (height(node.left)>height(node.right)){
            return height(node.left) + 1; } else {
                return height (node.right) +1;
            }
        }
    }



    public static void main(String[] args) {
         BST tree = new BST();
         tree.insert(5);
         tree.insert(3);
        tree.insert(2);
         tree.insert(6);
         tree.insert(8);
        tree.insert(67);
        tree.insert(68);
        tree.insert(35);
        tree.printTree();
       
      BST tree2 = new BST ();
        tree2.insert(10);
        tree2.insert(5);
        tree2.insert(11);
        tree2.insert(13);
        tree2.insert(14);
        tree2.insert(6);
        tree2.insert(3);
       // tree2.remove(6); 
        tree2.printTree();
        tree2.rotateLeft(tree2.root.right, tree2.root);
       // tree2.remove(5);
        tree2.printTree();


        BST tree3 = new BST ();
        tree3.insert(5);
        tree3.insert(3);
        tree3.insert(4);
        tree3.printTree();
        tree3.remove(3);
        tree3.printTree();

       // AVL tree4 = new AVL();
       // tree4.insert(5);
      //  tree4.insert(3);
       // tree4.insert(4);
        
    }



    //Add the following functions to your BST
 //Please use this code to verify your tree integrity
    public boolean isBSTOrNot() {
        return isBSTOrNot(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTOrNot(Node root, int minValue, int maxValue) {
        // check for root is not null or not
        if (root == null) {
            return true;
        }
        // check for current node value with left node value and right node value and recursively check for left sub tree and right sub tree
        if(root.key >= minValue && root.key <= maxValue && isBSTOrNot(root.left, minValue, root.key) && isBSTOrNot(root.right, root.key, maxValue)){
            return true;
        }
        return false;
    }

 

   // please use the following pieces of code to display your tree in a more easy to follow style (Note* you'll need to place the Trunk class in it's own file)
    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
 
    public void printTree(){
        printTree(root, null, false);
    }

    private void printTree(Node root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }
 
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(root.right, trunk, true);
 
        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + root.key);
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
 
        printTree(root.left, trunk, false);
    }

//this goes into it's own file
    class Trunk
   {
    Trunk prev;
    String str;
 
    Trunk(Trunk prev, String str)
    {
        this.prev = prev;
        this.str = str;
    }
   }
 

}

