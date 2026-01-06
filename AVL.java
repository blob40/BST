import java.util.ArrayList;

class AVL extends BST{
    //pre -condition: The tree is a valid AVL tree
    //post-condition: inserts the key into the tree and maintains the proper rules in a AVL


    //print 3s to find the issue
        void insert(int key){

            ArrayList<Node> path = new ArrayList<Node>();
        if (root == null){
            root = new Node(key);
    }
        Node temp = root;
        while(true){
            path.add(temp);
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
      balanceTree(path); 
}
        
//pre-condition: The tree is a valid AVL
//post-condition: removes the key from the tree and maintains the proper rules in a AVL
        public void remove(int key){
            super.remove(key);
            
        }


        //pre-condition: The tree is a valid AVL
        //post-condition: returns a balanced tree
        private void balanceTree(ArrayList<Node> path){
            if (path == null) {
                return;
            }
            
            for (int i = path.size()-1; i >= 0; i--){
                if (balance(path.get(i)) > 1){
                    super.rotateLeft(path.get(i), null);
                } else if (balance(path.get(i)) < -1){
                    super.rotateRight(path.get(i), null);
                }
            }
        }

        public static void main(String[] args) {
            System.out.println("AVL Tree");
        }
     }


