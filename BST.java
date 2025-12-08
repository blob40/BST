//Alex Krouse
//12/9/25
//Binary Search Tree Implementation, creating insert, find, remove and toString methods


class BST {
    Node root;

    public BST()
    {
          root = null;
    }

   

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


    boolean remove(int key){
        Node above = root;
        Node current = root;
        

        //If key is not found
        if (find(key) == false || root == null){
            return false;
        }
      

        //no children
        if (key < current.key){
            while (current.key != key){
                above = current;
                current = current.left;
            }
        } else if (key > current.key){
            while (current.key != key){
                above = current;
                current = current.right;
            }
        }

        if(current.key == key && current.left == null && current.right == null){
            System.out.println("removing node with no children " + current.key + " above is " + above.key);
            current = null;
            return true;
        } 



        //one child 
        if(current.key == key && current.left != null&& current.right == null){
            System.out.println("removing node with one child " + current.key );
            above = current.left;
            System.out.println("new node is " + above.key);
        }
         if(current.key == key && current.left == null && current.right != null){
            System.out.println("removing node with one child " + current.key );
            above = current.right;
            System.out.println("new node is " + above.key);
        }

        //two children
        //TODO
        if(current.key == key && current.left != null && current.right != null){
           Node child = current.left;
           Node parent = current;

           while (child.right != null){
            child = child.right;
            parent = child;
           }
           child.right = parent.left;
           

        }

        //node.left == toremove.right
       
        return true;
    }


    //TODO
    public String toString(){
        return "";
    }




    public static void main(String[] args) {
        BST tree = new BST ();
        tree.insert(6);
        tree.insert(4);
        tree.insert(8);
        tree.insert(3);
       
        tree.printTree();
      BST tree2 = new BST ();
        tree2.insert(10);
        tree2.insert(5);
        tree2.remove(10); 
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
   };
 

}