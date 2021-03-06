package data;

import datastructure.BST;
import datastructure.Node;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tool.Tool;

/**
 *
 * @author tri
 */
public class Dictionary {

    private Scanner sc = new Scanner(System.in);

    public BST<Vocabulary> b = new BST();
    
   
    public void readFile(String fileName) {   //done
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner read = new Scanner(f);
            do {
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
            } while (read.hasNextLine());
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
    
     public void simpleBalancedTree() {
        if (!b.isBalanced(b.root)) {
            b.balanceTree();
        }
        System.out.println("Tree has been balanced.");
    }

    //H??m th??m m???t t??? m???i
    public void addANewWord() {
        String word, meaning;
        word = Tool.getString("Input new word: ", "Word cann't be empty.");

        // Check th??? t??? ???? t???n t???i hay ch??a
        boolean choice = false;

        //N???u t??? ???? t???n t???i th?? h???i c?? mu???n s???a hay kh??ng
        if (b.search(new Vocabulary(word, "")) != null) {
            Vocabulary tmp = (Vocabulary) b.search(new Vocabulary(word, "")).val;
            tmp.showInformation();
            System.out.println("This word has been existed. Do you want to change its meaning?");
            choice = askYN("Input your choice (Y/N):", "Choice cann't be empty.");
            if (choice) {
                meaning = Tool.getString("Input word's meaning: ", "Meaning cann't be empty.");
                Vocabulary v = new Vocabulary(word, meaning);
                b.search(new Vocabulary(word, "")).val = v;
                System.out.println("Add new word successfully.");
                v.showInformation();
                System.out.println("Do you want to continue to add another word?");
                choice = askYN("Input your choice (Y/N):", "Choice cann't be empty.");
                if (choice) {
                    addANewWord();
                }
            } else {
                System.out.println("Add word fail!");
            }
        } else {

            //N???u ch??a t???n t???i th?? nh???p ngh??a
            meaning = Tool.getString("Input word's meaning: ", "Meaning cann't be empty.");
            Vocabulary v = new Vocabulary(word, meaning);
            b.add(v);
            System.out.println("Add new word successfully.");
            v.showInformation();
            System.out.println("Do you want to continue to add another word?");
            choice = askYN("Input your choice (Y/N):", "Choice cann't be empty.");
            if (choice) {
                addANewWord();
            } else {
                if (!b.isBalanced(b.root)) {
                    b.balanceTree();
                }
            }
        }
    }

    //H??m x??? l?? c??c c??u h???i yes no
    private boolean askYN(String inforMessage, String errorMessage) {
        while (true) {
            String choice;
            choice = Tool.getString(inforMessage, errorMessage);
            if (choice.equalsIgnoreCase("y")) {
                return true;
            } else if (choice.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid value!!!");
                System.out.println("Enter y/Y if yes.");
                System.out.println("Enter n/N if no.");
            }
        }
    }

    //Check xem t??? ???? c?? t???n t???i hay ch??a
    public boolean isVocaExisted(String word) {
        if (b.search(new Vocabulary(word, "")) == null) {   // s???a m???t ch??t
            return false;
        }
        return true;
    }

    //X??a m???t t???
    public void deleteAWord() {
        String word = Tool.getString("Input the word you want to delete: ", "Word cann't be empty!");
        Vocabulary voca = new Vocabulary(word, "");

        //T??m th??? t??? ???? c?? trong danh s??ch kh??ng 
        if (b.search(voca) == null) {
            //N???u ch??a c?? th?? b??o l???i
            System.out.println("Delete fail: Vocabulary " + word + " doesn't exist.");
        } else {
            //N???u t??m th???y th?? show th??ng tin t??? ???? ra
            voca = (Vocabulary) b.search(voca).val;
            System.out.println("");
            System.out.println("This is the vocabulary you want to delete: ");
            voca.showInformation();
            System.out.println("Are you sure to delete this word?");
            boolean choice = askYN("Input your choice(Y/N): ", "Choice cann't be empty!");
            System.out.println("");
            if (choice) {
                b.deleteNode(voca);
                System.out.println("Delete voca " + voca.word + " successfully.");
                System.out.println("");

                //H???i c?? mu???n x??a th??m hay kh??ng
                System.out.println("Do you want to delete another voca?");
                choice = askYN("Input your choice(Y/N): ", "Choice cann't be empty!");
                if (choice) {
                    deleteAWord();
                } else {
                    // N???u kh??ng mu???n x??a n???a th?? c??n b???ng l???i c??y 
                    if (!b.isBalanced(b.root)) {
                        b.balanceTree();
                    }
                }
            } else {
                System.out.println("Delete fail.");
            }
        }
    }

    //T??m ki???m m???t t??? v???ng
    public void search() {
        String word = Tool.getString("Input voca you want to search: ", "Voca cann't be empty!");
        Vocabulary voca = new Vocabulary(word, "");

        if (b.search(voca) == null) {
            System.out.println("Voca " + word + " doesn't exist!");
        } else {
            voca = (Vocabulary) b.search(voca).val;
            System.out.println("+--------------------+----------------------------------------------------------------------+");
            voca.showInformation();
            System.out.println("+--------------------+----------------------------------------------------------------------+");
        }
    }

    //In ra ???????ng ??i gi???a 2 node
    public void printTrack() {
        String src, dest;
        src = Tool.getString("Input first word: ", "Word cann't be empty!");
        dest = Tool.getString("Input second word: ", "Word cann't be empty!");
        Vocabulary vocaSrc = new Vocabulary(src, "");
        Vocabulary vocaDest = new Vocabulary(dest, "");

        b.printTrack(vocaSrc, vocaDest);
    }

    //In ra to??n b??? c??y
    public void printAll() {
        ArrayList<Vocabulary> arr = b.toArray();
        System.out.println("+--------------------+----------------------------------------------------------------------+");
        for (Vocabulary x : arr) {
            x.showInformation();
        }
        System.out.println("+--------------------+----------------------------------------------------------------------+");
    }

    public void printRoot() {
        System.out.println(b.root.val);
    }
}
