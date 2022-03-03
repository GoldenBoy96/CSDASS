
package datastructure;

public class Node<T extends Comparable> {
    T val;
    Node<T> left;
    Node<T> right;
    Node(){}
    Node(T el){
        this.val = el;
    }
    Node(T el, Node lt, Node rt) {
        this.val = el;
        this.left = lt;
        this.right = rt;
    }
}
