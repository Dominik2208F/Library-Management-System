package org.example;

import java.util.Scanner;

public class Logic {

    Library library;
   public Logic(Library library){
       this.library= library;
   }

    public void bookShopFlow(String decision){
        String decisionInner = "";
        Scanner scanner = new Scanner(System.in);
        switch (decision) {
            case "1":
                do {
                    library.addBookToLibrary();
                    System.out.println("Do you want to go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "2":
                do {
                    library.returnAllBooksInfo();
                    System.out.println("Do you want to go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "3":
                do {
                    library.returnSpecificBookInfoFromLibrary();
                    System.out.println("Do you want to go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "4":
                do {
                    library.deleteBookFromLibrary();
                    System.out.println("Do you want to go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "5": {
                do {
                    library.updateBookInfoInTheLibrary();
                    System.out.println("Do you want to go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            }
        }
    }


}
