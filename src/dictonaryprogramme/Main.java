package dictonaryprogramme;

import data.Dictionary;
import ui.Menu;
import tool.GetDirectory;

public class Main {

    public static void main(String[] args) {
        String filePath = GetDirectory.DIR + "\\Dictionary.txt";
        System.out.println(filePath);
        Menu menu = new Menu();
        menu.addNewOption("1. Add A Word");
        menu.addNewOption("2. Delete A Word");
        menu.addNewOption("3. Search A Word");
        menu.addNewOption("4. Print Path");
        menu.addNewOption("5. Show Word List");
        menu.addNewOption("6. Quit");

        Dictionary d = new Dictionary();
        d.readFile(filePath);
        int choice;
        do {
            System.out.println("------------Welcom to Dictionary Management Program-------------");
            menu.printMenu();
            System.out.println("----------------------------------------------------------------");
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    d.addANewWord();
                    d.writeFile(filePath);
                    break;
                case 2:
                    d.deleteAWord();
                    d.writeFile(filePath);
                    break;
                case 3:
                    d.search();
                    break;
                case 4:
                    d.printTrack();
                    break;
                case 5:
                    d.printAll();
                    break;
                case 6:
                    break;
            }
        } while (choice != 6);
    }

}
