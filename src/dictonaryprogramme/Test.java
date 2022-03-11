/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictonaryprogramme;

import data.Dictionary;
import data.Vocabulary;

/**
 *
 * @author Huu Minh
 */
public class Test {

    public static void main(String[] args) {
        Dictionary D = new Dictionary();
        
//        D.addVoca("banana", "chuối");
//        D.addVoca("coconut", "dừa");
//        D.addVoca("apple", "táo");
        
        
        D.readFile("D:\\Desktop\\assignment\\Vocabulary.txt");
        //D.addVoca("coconut", "gà");
        D.printAll();
        D.printTrack("access", "adequate");
        //D.searchVoca("access");
        System.out.println("");
        D.deleteVoca("access");
        D.printAll();
        D.printTrack("access", "adequate");
        //D.writeFile("D:\\Desktop\\assignment\\Vocabulary.txt");
        
    }
}
