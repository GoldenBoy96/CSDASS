package assignment;

import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.Arrays;

public class SBT<T extends Comparable<T>> {

    public Node root;

    public SBT() {
    }

    ;
    
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
    }

    public void LNR(Node tmp) {
        if (tmp != null) {
            LNR(tmp.left);
            System.out.print(tmp.val + " ");
            LNR(tmp.right);
        }
    }

    public void NLR(Node tmp) {
        if (tmp != null) {
            System.out.print(tmp.val + " ");
            NLR(tmp.left);
            NLR(tmp.right);
        }
    }

    

    private boolean isLeaf(Node input) {
        return input.left == null && input.right == null;
    }

    public void printLeaf(Node tmp) {
        if (tmp != null) {
            if (isLeaf(tmp)) {
                System.out.print(tmp.val + " ");
            }
            printLeaf(tmp.left);
            printLeaf(tmp.right);
        }
    }

    public int numberLeaf(Node tmp) {
        if (tmp == null) {
            return 0;
        }
        if (isLeaf(tmp)) {
            return 1;
        };
        return numberLeaf(tmp.left) + numberLeaf(tmp.right);
    }

    

    public int heightTree(Node tmp) {
        if (tmp == null) {
            return -1;
        } else if (tmp.left == null && tmp.right == null) {
            return 0;
        } else if (tmp.left != null && tmp.right != null) {
            return max(heightTree(tmp.left), heightTree(tmp.right)) + 1;
        } else if (tmp.left == null) {
            return 1 + heightTree(tmp.right);
        } else {
            return 1 + heightTree(tmp.left);
        }
    }

    public Node rightMost(Node tmp) {
        if (tmp.left != null) {
            tmp = tmp.left;
            while (tmp.right != null) {
                tmp = tmp.right;
            }
        }
        return tmp;
    }

    public Node leftMost(Node tmp) {
        if (tmp.right != null) {
            tmp = tmp.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
        }
        return tmp;
    }

    public Node rightMostOf(T val) {
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

        return tmp;
    }
    
    private T[] sortArr(T arr[]) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++)
                if (arr[j].compareTo(arr[j+1]) > 0)
                {
                    // swap arr[j+1] and arr[j]
                    T temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        return arr;
    }
    
    public int numberNode(Node tmp) {
        if (tmp == null) {
            return 0;
        }
        return 1 + numberNode(tmp.left) + numberNode(tmp.right);
    }
    
    public void ArrToSBT(T[] arr) {
        arr = sortArr(arr);
        System.out.println(Arrays.toString(arr));
        deleteAll();
        root = SortArrToSBT(arr, 0, arr.length - 1);
        //return SortArrToSBT(arr, 0, numberNode(root));
    }

//    https://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
    private Node SortArrToSBT(T[] arr, int begin, int end) {       
        
        if (begin > end) {
            return null;
        }
        
        int mid = (begin + end) / 2;
        
        Node node = new Node(arr[mid]);

        node.left = SortArrToSBT(arr, begin, mid - 1);        
        node.right = SortArrToSBT(arr, mid + 1, end);
        
        return node;
    }
    
    public void LRN(Node tmp) {
        if (tmp != null) {
            LRN(tmp.left);
            LRN(tmp.right);
            System.out.print(tmp.val + " ");

        }
    }
    
    public T[] SBTToArr() {
        T[] arr = (T[])new Object[10];
        SBTToArr(root, arr, 0);
        return arr;
    }
    
    private int SBTToArr(Node tmp, T arr[], int index) {
        if (tmp != null) {
            SBTToArr(tmp.left, arr, index);
            SBTToArr(tmp.right, arr, index);
        }
        arr[index] = (T) tmp.val;
        return index + 1;
    }

}
