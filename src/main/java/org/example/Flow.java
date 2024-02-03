package org.example;

import org.example.LibraryManager.Library;
import org.example.LibraryManager.LibraryDataBase;
import org.example.UserManager.User;
import org.example.UserManager.UsersDataBase;

import java.util.Scanner;

public class Flow {

    Library flowLibrary;
    LibraryDataBase libraryDataBase;
    public Flow(LibraryDataBase libraryDataBase) {
        this.libraryDataBase=libraryDataBase;
    }

    public void mainFlow(){
        Scanner scanner = new Scanner(System.in);

        flowLibrary= selectLibraryInstanceToVisit();  // assign library instance to flowLibrary variable to work on

        flowLibrary.getLibraryUserDataBase().addUserToLibrary(); // add user to that library through database of User

        boolean forceSwitchLibrary=false;

        //USER ACTION
        while(true) {
            if(forceSwitchLibrary){
                break;
            }
            flowLibrary.getLibraryUserDataBase().getAllAvailableUserSwitch(); // Users assigned to that library instance
            String userSelection = scanner.nextLine();
            if (userSelection.equals("Switch")) {
                break;
            }
            if (flowLibrary.getLibraryUserDataBase().checkIfUserExist(userSelection)) {
                switch (userSelection) {
                    case "Admin":
                        if (flowLibrary.getLibraryUserDataBase().validateAdminLogin()) {
                            forceSwitchLibrary =adminFlow();
                        } else {
                            System.out.println("Wrong password.");
                        }
                        break;
                    default:
                        if (flowLibrary.getLibraryUserDataBase().validateUserLogin(userSelection)) {
                            forceSwitchLibrary=userFlow(userSelection);
                        } else {
                            System.out.println("Wrong password");
                        }
                        break;
                }
            } else {
                System.out.println("User does not exist");
            }
        }
    }
    public boolean adminFlow(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Decide what you want to do:\n" +
                    "1: Add a book to library\n" +
                    "2: Return info of all available books in library\n" +
                    "3: Return specific info of a book in library\n" +
                    "4: Delete book\n" +
                    "5: Update book\n" +
                    "6: Change user\n" +
                    "7: Add user \n"+
                    "8: Delete user \n"+
                    "9: Return book of a given User\n" +
                    "10: Change library\n"+
                    "11: Delete library\n" +
                    "12: Borrowed books by Users");
            String decision = scanner.nextLine();
            if(!(Integer.parseInt(decision)>12)) {
                if (decision.equalsIgnoreCase("6")) {
                    System.out.println("Thanks for visiting.You are going to user selection view");
                    break;
                }
                if (decision.equalsIgnoreCase("10")) {
                    System.out.println("Thanks for visiting. You are going to library selection view");
                    return true;
                }
                if (decision.equalsIgnoreCase("11")) {
                    libraryDataBase.setLibraryFlow(flowLibrary);
                    libraryDataBase.removeLibrary();
                    System.out.println("Admin logged out from library");
                    return true;
                }
                adminActions(decision);
            }
            else{
                System.out.println("No action assigned to that number.Type correct number");
            }
        }
        return false;
    }
    public boolean userFlow(String userSelection){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Decide what you want to do:\n" +
                    "1: Borrow a book\n" +
                    "2: Return a book\n"+
                    "3: Show book borrowed by User\n"+
                    "4: Change user\n" +
                    "5: Change library\n" +
                    "6: Show available book to borrow");

            String decision = scanner.nextLine();

            if(!(Integer.parseInt(decision)>6)) {
                userActions(decision, userSelection);
                if (decision.equalsIgnoreCase("4")) {
                    System.out.println("Thanks for visiting");
                    break;
                }
                if (decision.equalsIgnoreCase("5")) {
                    System.out.println("Thanks for visiting. You are going to library selection view");
                    return true;
                }
            }
            else{
                System.out.println("No action assigned to that number.Type correct number");
            }
        }
        return false;
    }
    public void adminActions(String decision){
        String decisionInner = "";
        Scanner scanner = new Scanner(System.in);
        boolean exitMenu=false;
        switch (decision) {
            case "1":
                    while (true) {
                        flowLibrary.addBookToLibrary();
                        while (true) {
                            System.out.println("Go back to main menu. Type Y/N");
                            decisionInner = scanner.nextLine();
                            if (decisionInner.equalsIgnoreCase("Y")) {
                                exitMenu = true;
                                break;
                            } else if (decisionInner.equalsIgnoreCase("N")) {
                                break;
                            } else {
                                System.out.println("Wrong answer.Type 'Y' or 'N'");
                            }
                        }
                        if (exitMenu) {
                            break;
                        }
                    }
                break;
            case "2":
                    while(true){
                        flowLibrary.returnAllBooksInfo();
                        while (true) {
                            System.out.println("Go back to main menu. Type Y/N");
                            decisionInner = scanner.nextLine();
                            if (decisionInner.equalsIgnoreCase("Y")) {
                                exitMenu=true;
                                break;
                            } else if(decisionInner.equalsIgnoreCase("N")) {
                                break;
                            }
                            else{
                                System.out.println("Wrong answer.Type 'Y' or 'N'");
                            }
                        }

                        if(exitMenu){
                            break;
                        }
                    }
                    break;
            case "3":
                    while(true) {
                        flowLibrary.returnSpecificBookInfoFromLibrary();
                        while (true) {
                            System.out.println("Go back to main menu. Type Y/N");
                            decisionInner = scanner.nextLine();
                            if (decisionInner.equalsIgnoreCase("Y")) {
                                exitMenu=true;
                                break;
                            } else if(decisionInner.equalsIgnoreCase("N")) {
                                break;
                            }
                            else{
                                System.out.println("Wrong answer.Type 'Y' or 'N'");
                            }
                        }

                        if(exitMenu){
                            break;
                        }
                    }
                    break;
            case "4":
              while(true) {
                  flowLibrary.deleteBookFromLibrary();
                  while (true) {
                      System.out.println("Go back to main menu. Type Y/N");
                      decisionInner = scanner.nextLine();
                      if (decisionInner.equalsIgnoreCase("Y")) {
                          exitMenu=true;
                          break;
                      } else if(decisionInner.equalsIgnoreCase("N")) {
                          break;
                      }
                      else{
                          System.out.println("Wrong answer.Type 'Y' or 'N'");
                      }
                  }

                  if(exitMenu){
                      break;
                  }
              }
                break;
            case "5":
               while(true) {
                   flowLibrary.updateBookInfoInTheLibrary();
                   while (true) {
                       System.out.println("Go back to main menu. Type Y/N");
                       decisionInner = scanner.nextLine();
                       if (decisionInner.equalsIgnoreCase("Y")) {
                           exitMenu=true;
                           break;
                       } else if(decisionInner.equalsIgnoreCase("N")) {
                           break;
                       }
                       else{
                           System.out.println("Wrong answer.Type 'Y' or 'N'");
                       }
                   }

                   if(exitMenu){
                       break;
                   }
               }
                break;
            case "7":
                while (true) {
                    flowLibrary.getLibraryUserDataBase().addUserToDatabase();
                    while (true) {
                        System.out.println("Go back to main menu. Type Y/N");
                        decisionInner = scanner.nextLine();
                        if (decisionInner.equalsIgnoreCase("Y")) {
                            exitMenu = true;
                            break;
                        } else if (decisionInner.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Wrong answer.Type 'Y' or 'N'");
                        }
                    }

                    if (exitMenu) {
                        break;
                    }
                }
                break;
            case "9":
                while(true) {
                    User userFromList;
                    String UserToManage;
                    if(flowLibrary.getAllAvailableUserToReturnABook()){
                        System.out.println("Type name of user you want to manage");
                        UserToManage = scanner.nextLine();

                        userFromList = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(UserToManage);
                        flowLibrary.returnBookByTitle(userFromList);
                    }
                    while (true) {
                        System.out.println("Go back to main menu. Type Y/N");
                        decisionInner = scanner.nextLine();
                        if (decisionInner.equalsIgnoreCase("Y")) {
                            exitMenu=true;
                            break;
                        } else if(decisionInner.equalsIgnoreCase("N")) {
                            break;
                        }
                        else{
                            System.out.println("Wrong answer.Type 'Y' or 'N'");
                        }
                    }

                    if(exitMenu){
                        break;
                    }
                }
                break;
            case "8":
                while(true) {
                    flowLibrary.getLibraryUserDataBase().deleteUserFromDatabase();
                    while (true) {
                        System.out.println("Go back to main menu. Type Y/N");
                        decisionInner = scanner.nextLine();
                        if (decisionInner.equalsIgnoreCase("Y")) {
                            exitMenu=true;
                            break;
                        } else if(decisionInner.equalsIgnoreCase("N")) {
                            break;
                        }
                        else{
                            System.out.println("Wrong answer.Type 'Y' or 'N'");
                        }
                    }

                    if(exitMenu){
                        break;
                    }
                }
                break;
            case "12":
                while(true) {
                    flowLibrary.getInfoAboutBorrowedBooksAmongsUser();
                    while (true) {
                        System.out.println("Go back to main menu. Type Y/N");
                        decisionInner = scanner.nextLine();
                        if (decisionInner.equalsIgnoreCase("Y")) {
                            exitMenu=true;
                            break;
                        } else if(decisionInner.equalsIgnoreCase("N")) {
                            break;
                        }
                        else{
                            System.out.println("Wrong answer.Type 'Y' or 'N'");
                        }
                    }

                    if(exitMenu){
                        break;
                    }
                }
                break;
        }
    }
    public void userActions(String decision,String user){
        String decisionInner = "";
        Scanner scanner = new Scanner(System.in);
        User userFromList;
        boolean exitMenu=false;
        switch(decision) {
            case "1":
                while (true) {
                    userFromList = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(user);
                    flowLibrary.rentABookByTitle(userFromList);
                    while (true) {
                        System.out.println("Go back to main menu. Type Y/N");
                        decisionInner = scanner.nextLine();
                        if (decisionInner.equalsIgnoreCase("Y")) {
                            exitMenu = true;
                            break;
                        } else if (decisionInner.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Wrong answer.Type 'Y' or 'N'");
                        }
                    }

                    if (exitMenu) {
                        break;
                    }
                }
                break;
            case "2":
                while(true) {
                    userFromList = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(user);
                    flowLibrary.returnBookByTitle(userFromList);
                    while (true) {
                        System.out.println("Go back to main menu. Type Y/N");
                        decisionInner = scanner.nextLine();
                        if (decisionInner.equalsIgnoreCase("Y")) {
                            exitMenu = true;
                            break;
                        } else if (decisionInner.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Wrong answer.Type 'Y' or 'N'");
                        }
                    }

                    if (exitMenu) {
                        break;
                    }
                }
                break;
            case "3":
                while(true) {
                    userFromList = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(user);
                    userFromList.showBooks();
                    while (true) {
                        System.out.println("Go back to main menu. Type Y/N");
                        decisionInner = scanner.nextLine();
                        if (decisionInner.equalsIgnoreCase("Y")) {
                            exitMenu = true;
                            break;
                        } else if (decisionInner.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Wrong answer.Type 'Y' or 'N'");
                        }
                    }

                    if (exitMenu) {
                        break;
                    }
                }
                break;
            case "6":
                while(true) {
                    flowLibrary.returnAllBooksInfo();
                    while (true) {
                        System.out.println("Go back to main menu. Type Y/N");
                        decisionInner = scanner.nextLine();
                        if (decisionInner.equalsIgnoreCase("Y")) {
                            exitMenu = true;
                            break;
                        } else if (decisionInner.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Wrong answer.Type 'Y' or 'N'");
                        }
                    }

                    if (exitMenu) {
                        break;
                    }
                }
                break;
        }
    }

    public Library selectLibraryInstanceToVisit(){
        System.out.println("Type a name of a library to visit");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            for (Library librarySpec : libraryDataBase.getListOfLibrary()) {
                System.out.println(librarySpec.getNameOfLibrary());
            }
            String librarydecision = scanner.nextLine();
            //LIBRARY SELECTION
            for (Library librarySpec : libraryDataBase.getListOfLibrary()) {
                if (librarySpec.getNameOfLibrary().equalsIgnoreCase(librarydecision)) {
                    return librarySpec;

                }
            }
            System.out.println("Wrong library name. Try once again");
        }
    }
}
