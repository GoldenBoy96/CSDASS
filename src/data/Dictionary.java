package data;

import datastructure.BST;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tri
 */
public class Dictionary {

    private Scanner sc = new Scanner(System.in);

    public BST<Vocabulary> b = new BST();
    
    public void searchVoca(String key) {
//        Vocabulary tmp = b.search(new Vocabulary(key, ""));
        if (b.search(new Vocabulary(key, "")) != null) System.out.println("Existed!");
                else System.out.println("Not existed!");
    }
    
    public boolean isVocaExisted(String word) {
        if (b.search(new Vocabulary(word, "")) == null) return false;
        return true;
    }
    
    
    //cần thêm điều kiện kiểm tra xem đã có từ chưa
    public void addVoca(String word, String meaning) {
        if (isVocaExisted(word)) {
            System.out.println("Vocabulary is existed!");
            return;
        }
        b.add(new Vocabulary(word, meaning));
    }
    
    public void printAll() {
        //b.LNR(b.root);
        System.out.println(b.toString());

    }

    public void readFile(String fileName) {   //done
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner read = new Scanner(f);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                if (line.trim() != "") {
                    String data[] = line.split(";");
                    String word, meaning;
                    word = data[0].toString();
                    meaning = data[1].toString();
                    Vocabulary v = new Vocabulary(word, meaning);
                    if (!isVocaExisted(word)) {
                        b.add(v);
                    }
                }
            }
            read.close();
        } catch (Exception e) {
            System.out.println("An error occured when read file!");
            e.printStackTrace();
        }
    }

    public void writeFile(String fileName) {         //almost done, need further test

        try {
            File myFile = new File(fileName);
            myFile.createNewFile();

            FileWriter myWriter = new FileWriter(fileName);
            //Vocabulary arr[] = (Vocabulary[]) b.toArray().toArray();
            ArrayList<Vocabulary> arr = b.toArray();
            for (int i = 0; i < arr.size(); i++) {
                String data = arr.get(i).word + ";" + arr.get(i).meaning + "\n";
                myWriter.write(data);
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred when saving file!");
            e.printStackTrace();
        }
    }
    

}
