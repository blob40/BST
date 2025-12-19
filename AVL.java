 class AVL extends BST{
        public void insert(int key){
            super.insert(key);
            balanceTree(root);
        }
        
        public void remove(int key){
            super.remove(key);
            balanceTree(root);
            
        }

        private void balanceTree(Node n){
            if (n == null) {
                return;
            }
            balanceTree(n.left);
            balanceTree(n.right);
            if (balanceTree(n) > 1){
                super.rotateLeft(n, null);
            } else if (balanceTree(n) < -1){
                super.rotateRight(n, null);
            }

        }

        public static void main(String[] args) {
            AVL tree = new AVL();
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(40);
            tree.insert(50);
            tree.insert(25);
            tree.printTree();
        }
     }


