/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictonaryprogramme;

import data.Dictionary;

/**
 *
 * @author Huu Minh
 */
public class Test {

    public static void main(String[] args) {
        Dictionary D = new Dictionary();
        D.addVoca("apple", "táo");
        D.addVoca("banana", "chuối");
        D.addVoca("coconut", "dừa");
        //D.addVoca("coconut", "gà");
        D.printAll();
    }
}
