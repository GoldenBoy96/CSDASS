
package assignment;

public class Main {
    public static void main(String[] args) {
        SBT<Integer> sbt = new SBT();
        Integer arr[] = {1, 2, 4, 5, 7, 8, 9};
        sbt.ArrToSBT(arr);
        sbt.LNR(sbt.root);
    }
    
}
