package Manager;

import Frames.Status;
import org.example.LibraryManager.Book;
import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public interface CommonFunctions {
    default ImageIcon setIcon(String source){
        URL imageUrl = getClass().getResource(source);

        ImageIcon icon=null;
        if (imageUrl != null) {
            try (InputStream inputStream = imageUrl.openStream()) {

                Image originalImage = ImageIO.read(inputStream);

                icon = new ImageIcon(originalImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Nie udało się znaleźć zasobu.");
        }
        return icon;
    }

    default  String extractTitle(String inputString) {

        String[] parts = inputString.split(", ");
        for (String part : parts) {
            if (part.startsWith("Title:")) {
                String[] titleParts = part.split(": ");
                return titleParts[1];
            }
        }
        return null;
    }

    default List<Book> ListOfBorrowedBook(Library flowLibrary) {
        List<Book> booksAvailable = new ArrayList<>();

        for (Book book : flowLibrary.getListOfBooks()) {
            if (book.getStatus() == Status.BORROWED) {
                booksAvailable.add(book);
            }
        }
        return booksAvailable;
    }

    default List<Book> ListOfAvailableBook(Library flowLibrary) {
        List<Book> booksAvailable = new ArrayList<>();

        for (Book book : flowLibrary.getListOfBooks()) {
            if (book.getStatus() == Status.AVAILABLE) {
                booksAvailable.add(book);
            }
        }
        return booksAvailable;
    }




    default  boolean checkIfAllBooksReturned(List<Book> books) {

        for (Book book : books) {
            if (book.getStatus() == Status.BORROWED) {
                return false;
            }
        }
        return true;
    }

    default boolean checkIfAllBooksBorrowed( List<Book> books){

        for(Book book : books){
            if(book.getStatus()==Status.AVAILABLE){
                return false;
            }
        }
        return true;
    }



    default DefaultListModel<String> ListOfUsers(Library flowLibrary){

        DefaultListModel<String> modifiedModel = new DefaultListModel<>();
        for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
            modifiedModel.addElement(user.toString());
        }
        return modifiedModel;
    }

}
