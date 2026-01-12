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

        //left- right double rotation is wrong fix later (might not have a case to do that rotations)
        private void balanceTree(ArrayList<Node> path){

            if (path == null) {
                return;
            }
            
            for (int i = path.size()-1; i >= 0; i--){

                Node parent = null;
                if (i - 1 >= 0) {
                    parent = path.get(i-1);
                } 


            if (balance(path.get(i)) > 1){
                if (path.get(i).right != null && balance(path.get(i).right) < 0){
                    super.rotateRight(path.get(i).right, path.get(i));
                        super.rotateLeft(path.get(i), parent);
                     }
                      super.rotateLeft(path.get(i), parent);
                } 
                
                
                if (balance(path.get(i)) < -1){
                   if (path.get(i).left != null  && balance(path.get(i).left) > 0){
                        super.rotateLeft(path.get(i).left, path.get(i));
                        super.rotateRight(path.get(i), parent);
                     } else {
                         super.rotateRight(path.get(i), parent);
                     }
                     
                }  
            }
        }

        public static void main(String[] args) {
            System.out.println("AVL Tree");
        }
     }


