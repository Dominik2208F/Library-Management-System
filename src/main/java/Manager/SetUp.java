package Manager;


import org.example.LibraryManager.Library;
import org.example.LibraryManager.LibraryDataBase;
import org.example.UserManager.UsersDataBase;



public class SetUp {

    private LibraryDataBase libraryDataBase;

    public SetUp(LibraryDataBase libraryDataBase) {
        this.libraryDataBase = libraryDataBase;
    }


    public void createDefaultLibrariesSetUp(String name) {
        UsersDataBase usersDataBase = new UsersDataBase();
        Library library = new Library(usersDataBase, name);
        libraryDataBase.addLibrary(library);
        if (name.equals("Pruszkowska")) {
            library.addDefaultBooksToPruszkowskaLibrary();
            usersDataBase.createDefaultUsers();
        } else {
            library.addDefaultBookswarszawskaToLibrary();
            usersDataBase.createDefaultUsers();
        }
    }

}
