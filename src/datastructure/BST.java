package datastructure;

import static java.lang.String.format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BST<T extends Comparable<T>> {

    public Node root;

    public BST() {
    }

    
    public boolean isEmpty() {
        return root == null;
    }

    public void deleteAll() {
        root = null;
    }
    
    public void add(T el) {
        Node<T> p = new Node(el);
        if (isEmpty()) {
            root = p;
            return;
        }
        Node<T> tmp = root;
        while (tmp != null) {
            if (p.val.compareTo(tmp.val) <= 0) {
                if (tmp.left == null) {
                    tmp.left = p;
                    return;
                }
                tmp = tmp.left;
            } else {
                if (tmp.right == null) {
                    tmp.right = p;
                    return;
                }
                tmp = tmp.right;
            }
        }
        if (!isBalanced(root)) balanceTree();
    }





    

//    private boolean isLeaf(Node input) {
//        return input.left == null && input.right == null;
//    }

//    public void printLeaf(Node tmp) {
//        if (tmp != null) {
//            if (isLeaf(tmp)) {
//                System.out.print(tmp.val + " ");
//            }
//            printLeaf(tmp.left);
//            printLeaf(tmp.right);
//        }
//    }
//
//    public int numberLeaf(Node tmp) {
//        if (tmp == null) {
//            return 0;
//        }
//        if (isLeaf(tmp)) {
//            return 1;
//        };
//        return numberLeaf(tmp.left) + numberLeaf(tmp.right);
//    }

    
//
//    public int heightTree(Node tmp) {
//        if (tmp == null) {
//            return -1;
//        } else if (tmp.left == null && tmp.right == null) {
//            return 0;
//        } else if (tmp.left != null && tmp.right != null) {
//            return max(heightTree(tmp.left), heightTree(tmp.right)) + 1;
//        } else if (tmp.left == null) {
//            return 1 + heightTree(tmp.right);
//        } else {
//            return 1 + heightTree(tmp.left);
//        }
//    }

    public Node rightMost(Node tmp) {
        if (tmp.left != null) {
            tmp = tmp.left;
            while (tmp.right != null) {
                tmp = tmp.right;
            }
        }
        return tmp;
    }

    private Node leftMost(Node tmp) {
        if (tmp.right != null) {
            tmp = tmp.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
        }
        return tmp;
    }

    private Node rightMostOf(T val) {
        Node tmp = root;
        while (tmp.val.compareTo(val) != 0 && tmp != null) {
            if (tmp.val.compareTo(val) == 1) {
                tmp = tmp.left;
            }
            if (tmp.val.compareTo(val) == -1) {
                tmp = tmp.right;
            }
        }
        System.out.println(tmp.val);
        return rightMost(tmp);
    }
    
    public Node leftMostOf(T val) {
        Node tmp = root;
        while (tmp.val.compareTo(val) != 0 && tmp != null) {
            if (tmp.val.compareTo(val) == -1) {
                tmp = tmp.left;
            }
            if (tmp.val.compareTo(val) == 1) {
                tmp = tmp.right;
            }
        }
        System.out.println(tmp.val);
        return leftMost(tmp);
    }
    
    public Node deleteNode(Node tmp, T val) {
        if (isEmpty()) return tmp;       
        
        switch (tmp.val.compareTo(val)) {
            case 1:
                tmp.left = deleteNode(tmp.left, val);
                break;
            case -1:
                tmp.right = deleteNode(tmp.right, val);
                break;  
            default:
                if (tmp.left == null) {
                    return tmp.right;
                } else if (tmp.right == null) {
                    return tmp.left;
                }
                
                tmp.val = leftMost(tmp).val;
                tmp.right = deleteNode(tmp.right, (T) tmp.val);
                break;
        }
        
        if (!isBalanced(root)) balanceTree();

        return tmp;
    }
    
    public Node search(T key) {
        return searchNode(root, key);
    }
    
    private Node searchNode(Node root, T key) {
        // Base Cases: root is null or key is present at root
        if (root==null || root.val.compareTo(key) == 0)
            return root;

        // Key is greater than root's key
        if (root.val.compareTo(key) < 0)
            return searchNode(root.right, key);

        // Key is smaller than root's key
        return searchNode(root.left, key);
    }
    
    private ArrayList sortArr(ArrayList<T> arr) {
        int n = arr.size();
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++)
                if (arr.get(j).compareTo(arr.get(j+1)) > 0)
                {
                    // swap arr[j+1] and arr[j]
                    T temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j+1, temp);
                }
        return arr;
    }
    
    public int size() {
        return numberNode(root);
    }
    
    private int numberNode(Node tmp) {
        if (tmp == null) {
            return 0;
        }
        return 1 + numberNode(tmp.left) + numberNode(tmp.right);
    }
    
    public void ArrToSBT(ArrayList<T> arr) {
        arr = sortArr(arr);
        //System.out.println(Arrays.toString(arr));
        root = SortArrToSBT(arr, 0, arr.size() - 1);
    }

//    https://www.geeksforgeeks.org/sorted-array-to-balanced-SBT/
    private Node SortArrToSBT(ArrayList<T> arr, int begin, int end) {       
        
        if (begin > end) {
            return null;
        }
        
        int mid = (begin + end) / 2;
        
        Node node = new Node(arr.get(mid));
        

        node.left = SortArrToSBT(arr, begin, mid - 1);        
        node.right = SortArrToSBT(arr, mid + 1, end);
        
        return node;
    }
    
//    public void LRN(Node tmp) {
//        if (tmp != null) {
//            LRN(tmp.left);
//            LRN(tmp.right);
//            System.out.print(tmp.val + " ");
//            System.out.println(isBalanced(root));
//        }
//    }
    

    
    public ArrayList toAray() {
        ArrayList<T> tmp = new ArrayList<>();
        toArray(root, tmp);
        return tmp;
    }
    
//    public T[] toArray() {
//        MyArray<T> tmp = new MyArray<T>(new T[10]);
//    }
//    
//    private int toArray(Node tmp, MyArray<T> arr, int index) {
//        toArray(tmp.left, arr, index); 
//        arr.arr[index] = (T) tmp.val;
//        toArray(tmp.right,arr, index);
//        return index + 1;
//    }
    
    private ArrayList<T> toArray(Node tmp, ArrayList<T> arr) {    
        if (tmp == null) return arr;
        
        toArray(tmp.left,arr); 
        arr.add((T)tmp.val); 
        toArray(tmp.right,arr); 
        
        return arr;
        
    }
    
//    private int SBTToArr(Node tmp, T arr[], int index) {
//        if (tmp != null) {
//            SBTToArr(tmp.left, arr, index);
//            SBTToArr(tmp.right, arr, index);
//        }
//        arr[index] = (T) tmp.val;
//        return index + 1;
//    }
    
    public boolean isBalanced(Node node) {
        int lh; /* for height of left subtree */
 
        int rh; /* for height of right subtree */
 
        /* If tree is empty then return true */
        if (node == null)
            return true;
 
        /* Get the height of left and right sub trees */
        lh = height(node.left);
        rh = height(node.right);
 
        if (Math.abs(lh - rh) <= 1
            && isBalanced(node.left)
            && isBalanced(node.right))
            return true;
 
        /* If we reach here then tree is not height-balanced */
        return false;
    }
        
    private int height(Node node) {
        /* base case tree is empty */
        if (node == null)
            return 0;
 
        /* If tree is not empty then height = 1 + max of left
         height and right heights */
        return 1 + Math.max(height(node.left), height(node.right));
    }
    
    //T[] arr = (T[])new Arrays[10];
    public void LNR(Node tmp) {
        if (tmp != null) {
            LNR(tmp.left);
            System.out.println(tmp);
            LNR(tmp.right);
        }
    }
    
    public void balanceTree() {
        ArrayList<T> tmp = toAray();
        ArrToSBT(tmp);
    }
    @Override
    public String toString() {
        ArrayList<T> arr = (ArrayList<T>) toAray();
        String tmp = "";
        for (int i = 0; i < arr.size(); i++) {
            tmp = tmp.concat(arr.get(i).toString() + "\n");
        }
        return tmp;
    }

}
