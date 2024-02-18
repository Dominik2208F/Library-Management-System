package Frames;

import Manager.CommonFunctions;
import Manager.Queries;
import org.example.LibraryManager.Book;
import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;


public class UserActionFrame extends JFrame implements CommonFunctions {

    JMenuBar menubar;
    JMenu Options, Program;
    JMenuItem changeUser, changeLibrary, programInfo;
    JButton lookfor,BorrowAbook,ReturnAbook,ConfirmChoice,returnAll,borrowALL, filter,ShowAllBook,QuickView,Search,TransactionButton,transactionLeftSelect,transactionRightSelect;
    JList<String> list;
    Library flowLibrary;
    UserChooseIFrame userChooseIFrame;
    JCheckBox ascendingCheckBox,ascendingCheckBoxFiltering,ascendingCheckBoxFilteringBorrow,ascendingCheckBoxLOOKFOR;
    JCheckBox descendingCheckBox,descendingCheckBoxFiltering,descendingCheckBoxFilteringBorrow,descendingCheckBoxLOOKFOR;
    JLabel booksLabel,lookForJLabel;
    JTextField lookForTextField,transactionRight,transactionLeft;

    private JComboBox<String> sortComboBox, categoryComboBox, SubCategoryComboBox,categoryComboBoxBorrow,SubCategoryComboBoxBorrow,TransactionComboBox;
    private JComboBox<Integer> rangeComboBoxLeft,scrollableComboBox;

    private boolean borrowButtonClicked = false;
    private boolean returnButtonClicked = false;
    private boolean informationBorrow = true;
    private boolean informationReturn = true;
    private Map<String, List<String>> subcategoriesMap = new HashMap<>();

    private String CurrentLibraryName;

    public JTextField getTransactionRight() {
        return transactionRight;
    }

    public JTextField getTransactionLeft() {
        return transactionLeft;
    }

    public UserActionFrame(UserChooseIFrame userChooseIFrame, Library library) {

        this.flowLibrary = library;
        this.userChooseIFrame = userChooseIFrame;
        CurrentLibraryName=userChooseIFrame.getLibraryManagementFrame().getSelectedLibrary();
        userChooseIFrame.getSelectedUserValue();

        DefaultListModel<String> listOfAction = new DefaultListModel<>();
        list = new JList<>(listOfAction);
        list.setBounds(150, 20, 600, 150);
        add(list);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);


                int width = getWidth();
                int height = getHeight();


                GradientPaint gradient = new GradientPaint(0, 0, new Color(240, 248, 255), width, height, new Color(0, 191, 255));

                ((Graphics2D) g).setPaint(gradient);
                g.fillRect(0, 0, width, height);

            }
        });

        menubar = new JMenuBar();
        Options = new JMenu("Options");
        Program = new JMenu("Info");
        changeUser = new JMenuItem("Log out");
        changeLibrary = new JMenuItem("Change library");
        programInfo = new JMenuItem("About");
        Options.add(changeUser);
        Options.add(changeLibrary);
        Program.add(programInfo);

        setJMenuBar(menubar);
        menubar.add(Options);
        menubar.add(Program);

        lookForTextField=new JTextField();
        lookForTextField.setBounds(590,190,160,40);
        add(lookForTextField);




        lookForTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                DefaultListModel modifiedModel = new DefaultListModel<>();
                for (String book : Queries.getCurrentStateOfBooks(CurrentLibraryName)) {
                    modifiedModel.addElement(book);
                }
                list.setModel(modifiedModel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        lookForTextField.setVisible(false);

                lookForJLabel = new JLabel("Enter phrase");
        lookForJLabel.setBounds(520,190,160,40);
        add(lookForJLabel);

        lookForJLabel.setVisible(false);
        ImageIcon iconLookFor= setIcon("/search.png");
        Search= new JButton("Search");
        Search.setIcon(iconLookFor);
        Search.setBounds(590,240,160,40);
        add(Search);

        Search.setVisible(false);

        returnAll = new JButton("Return all");
        returnAll.setBounds(590, 190, 160, 40);
        add(returnAll);
        returnAll.setVisible(false);

        ImageIcon returnAllIcon = setIcon("/all.png");
        returnAll.setIcon(returnAllIcon);

        borrowALL = new JButton("Borrow all");
        borrowALL.setBounds(590, 190, 160, 40);
        add(borrowALL);
        borrowALL.setVisible(false);

        ImageIcon borrowAllIcon = setIcon("/all.png");
        borrowALL.setIcon(borrowAllIcon);

        sortComboBox = new JComboBox<>(new String[]{"Title", "Author", "Genre","Status"});
        sortComboBox.setBounds(590, 190, 160, 40);
        add(sortComboBox);

        categoryComboBox = new JComboBox<>(new String[]{"Author", "Genre", "Select","Status","Production date","Pages"});
        categoryComboBox.setBounds(590, 190, 160, 40);
        add(categoryComboBox);
        categoryComboBox.setSelectedItem("Select");

        SubCategoryComboBox = new JComboBox<>();
        SubCategoryComboBox.setBounds(590, 240, 180, 40);
        add(SubCategoryComboBox);

      //  booksLabel = new JLabel(RefreshListOfAvailableBook(library) + " books available in library");
     //   booksLabel.setBounds(350, -5, 200, 30);
     //   add(booksLabel);


        ascendingCheckBox = new JCheckBox("Sort \uD83E\uDC79");
        ascendingCheckBox.setBounds(500, 185, 150, 30);
        ascendingCheckBox.setSelected(true);

        descendingCheckBox = new JCheckBox("Sort \uD83E\uDC7B");
        descendingCheckBox.setBounds(500, 205, 150, 30);
        add(ascendingCheckBox);
        add(descendingCheckBox);

        ascendingCheckBoxFiltering = new JCheckBox("Sort filter \uD83E\uDC79");
        ascendingCheckBoxFiltering.setBounds(500, 185, 150, 30);

        descendingCheckBoxFiltering = new JCheckBox("Sort filter \uD83E\uDC7B");
        descendingCheckBoxFiltering.setBounds(500, 205, 150, 30);
        add(ascendingCheckBoxFiltering);
        add(descendingCheckBoxFiltering);


        BorrowAbook = new JButton("Borrow");
        BorrowAbook.setBounds(10, 20, 130, 40);
        add(BorrowAbook);

        ImageIcon borrow = setIcon("/borrow.png");
        BorrowAbook.setIcon(borrow);


        ShowAllBook =new JButton("Show all");
        ShowAllBook.setBounds(10,120,130,40);
        add(ShowAllBook);

        ImageIcon showboook = setIcon("/all_5334695.png");
        ShowAllBook.setIcon(showboook);

        ReturnAbook = new JButton("Return");
        ReturnAbook.setBounds(10, 70, 130, 40);
        add(ReturnAbook);

        ImageIcon returnBook = setIcon("/return.png");
        ReturnAbook.setIcon(returnBook);

        QuickView = new JButton("Quick view");
        QuickView.setBounds(755, 20, 120, 40);
        add(QuickView);

        ImageIcon qucikView = setIcon("/view.png");
        QuickView.setIcon(qucikView);

        filter = new JButton("Full filter of books");
        filter.setBounds(10, 200, 200, 40);
        add(filter);

        ImageIcon filterIcon = setIcon("/filter.png");
        filter.setIcon(filterIcon);

        ConfirmChoice = new JButton("Confirm Choice");
        ConfirmChoice.setBounds(590, 240, 160, 40);
        add(ConfirmChoice);
        ConfirmChoice.setBackground(Color.PINK);
        ConfirmChoice.setVisible(false);

        ImageIcon confirm = setIcon("/approved.png");
        ConfirmChoice.setIcon(confirm);

        lookfor = new JButton("Search title");
        lookfor.setBounds(10, 250, 200, 40);
        add(lookfor);

        ImageIcon sortIcon = setIcon("/from-a-to-z.png");
        lookfor.setIcon(sortIcon);


        TransactionButton=new JButton("Transactions history");
        TransactionButton.setBounds(10,300,200,40);
        add(TransactionButton);

        TransactionComboBox= new JComboBox<>(new String[]{"All","is borrowed","is returned"});
        TransactionComboBox.setBounds(590, 190, 160, 40);
        add(TransactionComboBox);
        TransactionComboBox.setSelectedItem("All");
        TransactionComboBox.setVisible(false);

        TransactionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transactionFiltering();
            }
        });

        TransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                ascendingCheckBoxFiltering.setVisible(false);
                descendingCheckBoxFiltering.setVisible(false);
                sortComboBox.setVisible(false);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                borrowButtonClicked = false;
                ConfirmChoice.setEnabled(false);
                ConfirmChoice.setVisible(false);
                borrowALL.setVisible(false);
                borrowALL.setEnabled(false);
                returnAll.setVisible(false);
                list.setEnabled(true);

                SubCategoryComboBoxBorrow.setEnabled(false);
                categoryComboBoxBorrow.setEnabled(false);

                ascendingCheckBoxFilteringBorrow.setVisible(false);
                descendingCheckBoxFilteringBorrow.setVisible(false);
                SubCategoryComboBoxBorrow.setVisible(false);
                categoryComboBoxBorrow.setVisible(false);

                rangeComboBoxLeft.setVisible(false);
                scrollableComboBox.setVisible(false);

                lookForTextField.setVisible(false);
                lookForJLabel.setVisible(false);
                Search.setVisible(false);

                ascendingCheckBoxLOOKFOR.setVisible(false);
                descendingCheckBoxLOOKFOR.setVisible(false);

                TransactionComboBox.setVisible(true);
                transactionLeft.setVisible(true);
                transactionRight.setVisible(true);

                transactionLeftSelect.setVisible(true);
                transactionRightSelect.setVisible(true);

                DefaultListModel model= new DefaultListModel<>();
                model.addAll(Queries.getAllTransactionByUser(CurrentLibraryName, userChooseIFrame.getChoosenUserName()));
                list.setModel(model);
            }
        });

        ImageIcon transaction = setIcon("/transaction (1).png");
        TransactionButton.setIcon(transaction);

        categoryComboBoxBorrow = new JComboBox<>(new String[]{"Author", "Genre", "Select","All available"});
        categoryComboBoxBorrow.setBounds(400, 190, 160, 40);
        add(categoryComboBoxBorrow);
        categoryComboBoxBorrow.setSelectedItem("Select");

        SubCategoryComboBoxBorrow = new JComboBox<>();
        SubCategoryComboBoxBorrow.setBounds(400, 240, 180, 40);
        add(SubCategoryComboBoxBorrow);


        ascendingCheckBoxFilteringBorrow = new JCheckBox("Sort results \uD83E\uDC79");
        ascendingCheckBoxFilteringBorrow.setBounds(260, 185, 150, 30);
        add(ascendingCheckBoxFilteringBorrow);

        descendingCheckBoxFilteringBorrow = new JCheckBox("Sort results \uD83E\uDC7B");
        descendingCheckBoxFilteringBorrow.setBounds(260, 205, 150, 30);
        add(descendingCheckBoxFilteringBorrow);



        ascendingCheckBoxFilteringBorrow.setVisible(false);
        descendingCheckBoxFilteringBorrow.setVisible(false);
        SubCategoryComboBoxBorrow.setVisible(false);
        categoryComboBoxBorrow.setVisible(false);


        ascendingCheckBoxFiltering.setVisible(false);
        descendingCheckBoxFiltering.setVisible(false);
        SubCategoryComboBox.setVisible(false);
        categoryComboBox.setVisible(false);


        scrollableComboBox = new JComboBox<>();
        DefaultComboBoxModel<Integer> scrollableModel = new DefaultComboBoxModel<>();
        for (int i = 0; i <= 2024; i++) {
            scrollableModel.addElement(i);
        }
        scrollableComboBox.setModel(scrollableModel);
        scrollableComboBox.setMaximumRowCount(10);

        rangeComboBoxLeft = new JComboBox<>();
        DefaultComboBoxModel<Integer> rangeModel = new DefaultComboBoxModel<>();
        for (int i = 0; i <= 2024; i++) {
            rangeModel.addElement(i);
        }
        rangeComboBoxLeft.setModel(rangeModel);

        add(rangeComboBoxLeft);
        add(scrollableComboBox);

        rangeComboBoxLeft.setBounds(590, 290, 70, 40);
        scrollableComboBox.setBounds(670,290,70,40);


        rangeComboBoxLeft.setVisible(false);
        scrollableComboBox.setVisible(false);

        lookForTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && lookForTextField.isFocusOwner()){
                    DefaultListModel model= new DefaultListModel();
                    String textToSearch= lookForTextField.getText();
                    if(textToSearch.isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "Type title to look for", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        model.addAll(Queries.searchForBookByTitle(CurrentLibraryName,textToSearch,"ASC"));
                        list.setModel(model);
                    }
                }
            }
        });



        rangeComboBoxLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subCategoryFiltering();
            }
        });
        scrollableComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subCategoryFiltering();
            }
        });

        QuickView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(list.getSelectedIndex()!=-1) {
                    new OverViewBookJFrame(library, list);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Choose one book from list", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        ShowAllBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.setEnabled(true);
                returnAll.setVisible(false);
                ConfirmChoice.setVisible(false);
                ascendingCheckBox.setVisible(true);
                descendingCheckBox.setVisible(true);
                sortComboBox.setVisible(true);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                ConfirmChoice.setEnabled(true);
                borrowALL.setVisible(false);
                ascendingCheckBoxFiltering.setVisible(false);
                descendingCheckBoxFiltering.setVisible(false);

                ascendingCheckBoxFilteringBorrow.setVisible(false);
                descendingCheckBoxFilteringBorrow.setVisible(false);
                SubCategoryComboBoxBorrow.setVisible(false);
                categoryComboBoxBorrow.setVisible(false);

                rangeComboBoxLeft.setVisible(false);
                scrollableComboBox.setVisible(false);

                lookForTextField.setVisible(false);
                lookForJLabel.setVisible(false);
                Search.setVisible(false);

                ascendingCheckBoxLOOKFOR.setVisible(false);
                descendingCheckBoxLOOKFOR.setVisible(false);

                TransactionComboBox.setVisible(false);
                transactionLeft.setVisible(false);
                transactionRight.setVisible(false);

                transactionLeftSelect.setVisible(false);
                transactionRightSelect.setVisible(false);

                DefaultListModel<String> modifiedModel = new DefaultListModel<>();

                if(Queries.getCurrentStateOfBooks(CurrentLibraryName).isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "No book in library ","Messege", JOptionPane.INFORMATION_MESSAGE);
                    sortComboBox.setVisible(false);
                    ascendingCheckBox.setVisible(false);
                    descendingCheckBox.setVisible(false);
                }
                for (String book : Queries.getCurrentStateOfBooks(CurrentLibraryName)) {
                    modifiedModel.addElement(book);
                }
                list.setModel(modifiedModel);
            }
        });
        ReturnAbook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                ascendingCheckBoxFiltering.setVisible(false);
                descendingCheckBoxFiltering.setVisible(false);
                sortComboBox.setVisible(false);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                ConfirmChoice.setEnabled(true);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                returnAll.setVisible(true);
                returnAll.setEnabled(true);
                borrowALL.setVisible(false);
                ConfirmChoice.setVisible(true);
                ascendingCheckBoxFilteringBorrow.setVisible(false);
                descendingCheckBoxFilteringBorrow.setVisible(false);
                SubCategoryComboBoxBorrow.setVisible(false);
                categoryComboBoxBorrow.setVisible(false);

                rangeComboBoxLeft.setVisible(false);
                scrollableComboBox.setVisible(false);

                lookForTextField.setVisible(false);
                lookForJLabel.setVisible(false);
                Search.setVisible(false);

                ascendingCheckBoxLOOKFOR.setVisible(false);
                descendingCheckBoxLOOKFOR.setVisible(false);

                TransactionComboBox.setVisible(false);
                transactionLeft.setVisible(false);
                transactionRight.setVisible(false);
                transactionLeftSelect.setVisible(false);
                transactionRightSelect.setVisible(false);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();


                for (String book : Queries.getAllBorrowedBooksByUser(CurrentLibraryName,userChooseIFrame.getSelectedUserValue())) {
                        modifiedModel.addElement(book);
                }
                list.setModel(modifiedModel);

                if (!Queries.checkIfAnyBookIsInStatusBorrowed(CurrentLibraryName,userChooseIFrame.getSelectedUserValue())) {

                    ConfirmChoice.setEnabled(false);
                    returnAll.setEnabled(false);

                    list.setModel(modifiedModel);
                    JOptionPane.showMessageDialog(null, "No books to return", "Warning", JOptionPane.WARNING_MESSAGE);
                    borrowButtonClicked = false;
                } else {
                    ConfirmChoice.setEnabled(true);
                    list.setModel(modifiedModel);
                    if (informationReturn) {
                        JOptionPane.showMessageDialog(null, "Choose at least one book to return and confirm", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                    informationReturn = false;
                    borrowButtonClicked = false;
                    returnButtonClicked = true;
                }
            }
        });
        lookfor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                ascendingCheckBoxFiltering.setVisible(false);
                descendingCheckBoxFiltering.setVisible(false);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                sortComboBox.setVisible(false);
                returnAll.setVisible(false);
                borrowALL.setVisible(false);
                ConfirmChoice.setVisible(false);
                ascendingCheckBoxFilteringBorrow.setVisible(false);
                descendingCheckBoxFilteringBorrow.setVisible(false);
                SubCategoryComboBoxBorrow.setVisible(false);
                categoryComboBoxBorrow.setVisible(false);
                list.setEnabled(true);
                rangeComboBoxLeft.setVisible(false);
                scrollableComboBox.setVisible(false);

                lookForTextField.setVisible(true);
                lookForJLabel.setVisible(true);
                Search.setVisible(true);

                ascendingCheckBoxLOOKFOR.setVisible(true);
                descendingCheckBoxLOOKFOR.setVisible(true);

                TransactionComboBox.setVisible(false);
                transactionLeft.setVisible(false);
                transactionRight.setVisible(false);
                transactionLeftSelect.setVisible(false);
                transactionRightSelect.setVisible(false);

                DefaultListModel<String> modifiedModel = new DefaultListModel<>();

                if (Queries.getCurrentStateOfBooks(CurrentLibraryName).isEmpty()) {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "No books in library", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (String book : Queries.getCurrentStateOfBooks(CurrentLibraryName)) {
                       modifiedModel.addElement(book);
                    }
                    list.setModel(modifiedModel);
                }
            }
        });
        ConfirmChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                ascendingCheckBoxFiltering.setVisible(false);
                descendingCheckBoxFiltering.setVisible(false);
                sortComboBox.setVisible(false);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                ConfirmChoice.setEnabled(true);

                rangeComboBoxLeft.setVisible(false);
                scrollableComboBox.setVisible(false);

                lookForTextField.setVisible(false);
                lookForJLabel.setVisible(false);
                Search.setVisible(false);

                ascendingCheckBoxLOOKFOR.setVisible(false);
                descendingCheckBoxLOOKFOR.setVisible(false);

                if(!returnButtonClicked) {
                    ascendingCheckBoxFilteringBorrow.setVisible(true);
                    descendingCheckBoxFilteringBorrow.setVisible(true);
                    SubCategoryComboBoxBorrow.setVisible(true);
                    categoryComboBoxBorrow.setVisible(true);
                }
                TransactionComboBox.setVisible(false);
                transactionLeft.setVisible(false);
                transactionRight.setVisible(false);
                transactionLeftSelect.setVisible(false);
                transactionRightSelect.setVisible(false);

                int[] selectedIndices = list.getSelectedIndices();
                List<String> selectedValues = list.getSelectedValuesList();
                if (borrowButtonClicked) {

                    if (selectedIndices.length > 0) {

                        if(selectedValues.size()>1){
                            int odp = JOptionPane.showConfirmDialog(null, "Do you want to borrow all selected books ?");
                            if (odp == JOptionPane.YES_OPTION) {
                            for(int i=0; i<selectedValues.size();i++) {

                                String titleOfBookToUnassingFromUser = extractTitle(selectedValues.get(i));
                                Queries.updateBookStatusByTitle(CurrentLibraryName,"BORROWED",titleOfBookToUnassingFromUser,userChooseIFrame.getSelectedUserValue());
                                Queries.insertBorrowedBookToHistory(userChooseIFrame.getSelectedUserValue(),titleOfBookToUnassingFromUser,CurrentLibraryName,"is borrowed");
                            }

                                DefaultListModel<String> modifiedModel1 = new DefaultListModel<>();
                                for (String book : Queries.getAllAvailableBook(CurrentLibraryName)) {
                                    modifiedModel1.addElement(book);
                                }
                                list.setModel(modifiedModel1);
                                JOptionPane.showMessageDialog(null, "Selected books have been borrowed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                                if (!Queries.checkIfAnyBookIsAvailable(CurrentLibraryName)) {
                                    borrowALL.setEnabled(false);
                                    ConfirmChoice.setEnabled(false);
                                    SubCategoryComboBoxBorrow.setEnabled(false);
                                    categoryComboBoxBorrow.setEnabled(false);
                                }
                           //     booksLabel.setText(RefreshListOfAvailableBook(library) + " books available in library");
                            }
                        }

                        if (selectedValues.size() == 1) {
                            String titleOfBookToUnassingFromUser = extractTitle(list.getSelectedValue());

                            int odp = JOptionPane.showConfirmDialog(null, "Do you want to borrow a book: " + titleOfBookToUnassingFromUser + " ?");
                            if (odp == JOptionPane.YES_OPTION) {


                                Queries.updateBookStatusByTitle(CurrentLibraryName,"BORROWED",titleOfBookToUnassingFromUser,userChooseIFrame.getSelectedUserValue());
                                Queries.insertBorrowedBookToHistory(userChooseIFrame.getSelectedUserValue(),titleOfBookToUnassingFromUser,CurrentLibraryName,"is borrowed");
                                DefaultListModel<String> modifiedModel1 = new DefaultListModel<>();
                                for (String book : Queries.getAllAvailableBook(CurrentLibraryName)) {
                                    modifiedModel1.addElement(book);
                                }
                                list.setModel(modifiedModel1);

                                JOptionPane.showMessageDialog(null, "Book " + titleOfBookToUnassingFromUser + " has been borrowed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                                if (!Queries.checkIfAnyBookIsAvailable(CurrentLibraryName)) {
                                    borrowALL.setEnabled(false);
                                    ConfirmChoice.setEnabled(false);
                                    SubCategoryComboBoxBorrow.setEnabled(false);
                                    categoryComboBoxBorrow.setEnabled(false);
                                }
                             //   booksLabel.setText(RefreshListOfAvailableBook(library) + " books available in library");
                            }
                         }
                        }

                    else {
                            JOptionPane.showMessageDialog(null, "Choose at least one book", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                } else if (returnButtonClicked) {

                    if (selectedIndices.length > 0) {
                        DefaultListModel<String> modifiedModel = new DefaultListModel<>();

                        if(selectedValues.size()>1) {
                            int odp = JOptionPane.showConfirmDialog(null, "Do you want return all selected books? ");
                            if (odp == JOptionPane.YES_OPTION) {


                            for (int i = 0; i < selectedValues.size(); i++) {
                                String titleOfBookToUnassingFromUser = extractTitle(selectedValues.get(i));

                                Queries.updateBookStatusByTitle(CurrentLibraryName,"AVAILABLE",titleOfBookToUnassingFromUser,null);
                                Queries.insertBorrowedBookToHistory(userChooseIFrame.getSelectedUserValue(),titleOfBookToUnassingFromUser,CurrentLibraryName,"is returned");
                            }


                            for (String book : Queries.getAllBorrowedBooksByUser(CurrentLibraryName,userChooseIFrame.getSelectedUserValue())) {
                                modifiedModel.addElement(book);
                            }
                                list.setModel(modifiedModel);

                                if (!Queries.checkIfAnyBookIsInStatusBorrowed(CurrentLibraryName,userChooseIFrame.getSelectedUserValue())) {
                                    returnAll.setEnabled(false);
                                    ConfirmChoice.setEnabled(false);
                                    SubCategoryComboBoxBorrow.setEnabled(false);
                                    categoryComboBoxBorrow.setEnabled(false);
                                }
                                JOptionPane.showMessageDialog(null, "All selected books has been returned successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                              //  booksLabel.setText(RefreshListOfAvailableBook(library) + " books available in library");
                            }
                        }
                        if (selectedValues.size() == 1) {
                            String titleOfBookToUnassingFromUser = extractTitle(list.getSelectedValue());

                            int odp = JOptionPane.showConfirmDialog(null, "Do you want return a book: " + titleOfBookToUnassingFromUser + " ?");
                            if (odp == JOptionPane.YES_OPTION) {

                                Queries.updateBookStatusByTitle(CurrentLibraryName,"AVAILABLE",titleOfBookToUnassingFromUser,null);
                                Queries.insertBorrowedBookToHistory(userChooseIFrame.getSelectedUserValue(),titleOfBookToUnassingFromUser,CurrentLibraryName,"is returned");
                                for (String book : Queries.getAllBorrowedBooksByUser(CurrentLibraryName,userChooseIFrame.getSelectedUserValue())) {
                                    modifiedModel.addElement(book);
                                }
                                list.setModel(modifiedModel);


                                if (!Queries.checkIfAnyBookIsInStatusBorrowed(CurrentLibraryName,userChooseIFrame.getSelectedUserValue())) {
                                    returnAll.setEnabled(false);
                                    ConfirmChoice.setEnabled(false);
                                    SubCategoryComboBoxBorrow.setEnabled(false);
                                    categoryComboBoxBorrow.setEnabled(false);
                                }
                                JOptionPane.showMessageDialog(null, "Book " + titleOfBookToUnassingFromUser + " has been returned successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                               // booksLabel.setText(RefreshListOfAvailableBook(library) + " books available in library");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Choose at least one book", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }

        });
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                ascendingCheckBoxFiltering.setVisible(false);
                descendingCheckBoxFiltering.setVisible(false);
                sortComboBox.setVisible(false);

                ascendingCheckBoxFiltering.setVisible(false);
                descendingCheckBoxFiltering.setVisible(false);
                SubCategoryComboBoxBorrow.setVisible(false);
                categoryComboBoxBorrow.setVisible(false);

                rangeComboBoxLeft.setVisible(false);
                scrollableComboBox.setVisible(false);

                lookForTextField.setVisible(false);

                new UserChooseIFrame(userChooseIFrame.getFlowLibrary(), userChooseIFrame.getLibraryManagementFrame());
                setVisible(false);
            }
        });
        changeLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                ascendingCheckBoxFiltering.setVisible(false);
                descendingCheckBoxFiltering.setVisible(false);

                ascendingCheckBoxFiltering.setVisible(false);
                descendingCheckBoxFiltering.setVisible(false);
                SubCategoryComboBoxBorrow.setVisible(false);
                categoryComboBoxBorrow.setVisible(false);

                rangeComboBoxLeft.setVisible(false);
                scrollableComboBox.setVisible(false);

                lookForTextField.setVisible(false);

                sortComboBox.setVisible(false);
                new LibraryManagementFrame(userChooseIFrame.getLibraryManagementFrame().getLibraryDataBase());
                setVisible(false);
            }
        });
        BorrowAbook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                ascendingCheckBoxFiltering.setVisible(false);
                descendingCheckBoxFiltering.setVisible(false);
                sortComboBox.setVisible(true);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                borrowButtonClicked = true;
                ConfirmChoice.setEnabled(false);
                ConfirmChoice.setVisible(true);
                borrowALL.setVisible(true);
                borrowALL.setEnabled(false);
                returnAll.setVisible(false);
                list.setEnabled(true);

                SubCategoryComboBoxBorrow.setEnabled(true);
                categoryComboBoxBorrow.setEnabled(true);

                ascendingCheckBoxFilteringBorrow.setVisible(false);
                descendingCheckBoxFilteringBorrow.setVisible(false);
                SubCategoryComboBoxBorrow.setVisible(true);
                categoryComboBoxBorrow.setVisible(true);

                rangeComboBoxLeft.setVisible(false);
                scrollableComboBox.setVisible(false);

                lookForTextField.setVisible(false);
                lookForJLabel.setVisible(false);
                Search.setVisible(false);

                ascendingCheckBoxLOOKFOR.setVisible(false);
                descendingCheckBoxLOOKFOR.setVisible(false);

                TransactionComboBox.setVisible(false);

                transactionLeft.setVisible(false);
                transactionRight.setVisible(false);
                transactionLeftSelect.setVisible(false);
                transactionRightSelect.setVisible(false);

                DefaultListModel<String> modifiedModelOverall = new DefaultListModel<>();

              ///  for (String book : Queries.getAllAvailableBook(CurrentLibraryName)) {
              //          modifiedModelOverall.addElement(book);
              //  }
                 list.setModel(modifiedModelOverall);

                if (Queries.checkIfAnyBookIsAvailable(CurrentLibraryName)) {
                    if (informationBorrow) {
                        JOptionPane.showMessageDialog(null, "Choose at least one book to borrow and confirm", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                    String StartVariable= (String )categoryComboBoxBorrow.getSelectedItem();
                    if(!StartVariable.equals("Select")){
                        categoryFilteringBorrow();
                    }
                    informationBorrow = false;
                    borrowButtonClicked = true;

                } else {
                    JOptionPane.showMessageDialog(null, "No books to borrow", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    borrowALL.setEnabled(false);
                    ConfirmChoice.setEnabled(false);
                    SubCategoryComboBoxBorrow.setEnabled(false);
                    categoryComboBoxBorrow.setEnabled(false);
                }
            }
        });
        programInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Program written by Dominik Jakubaszek. \n Version 2.0.0", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        descendingCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (descendingCheckBox.isSelected()) {
                    ascendingCheckBox.setSelected(false);
                    SortingComboBox();
                } else if (!ascendingCheckBox.isSelected()) {
                    ascendingCheckBox.setSelected(true);
                    SortingComboBox();
                }
            }
        });

        ascendingCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ascendingCheckBox.isSelected()) {
                    descendingCheckBox.setSelected(false);
                    SortingComboBox();
                } else if (!descendingCheckBox.isSelected()) {
                    descendingCheckBox.setSelected(true);
                    SortingComboBox();
                }
            }
        });

        descendingCheckBoxFiltering.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (descendingCheckBoxFiltering.isSelected()) {
                    ascendingCheckBoxFiltering.setSelected(false);
                    subCategoryFiltering();
                } else if (!ascendingCheckBoxFiltering.isSelected()) {
                    ascendingCheckBoxFiltering.setSelected(true);
                    subCategoryFiltering();
                }
            }
        });
        ascendingCheckBoxFiltering.setSelected(true);
        ascendingCheckBoxFiltering.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ascendingCheckBoxFiltering.isSelected()) {
                    descendingCheckBoxFiltering.setSelected(false);
                    subCategoryFiltering();
                } else if (!descendingCheckBoxFiltering.isSelected()) {
                    descendingCheckBoxFiltering.setSelected(true);
                    subCategoryFiltering();
                }
            }
        });

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoryFiltering();
            }
        });
        SubCategoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                subCategoryFiltering();
            }
        });

        ////////////////////////////

        descendingCheckBoxFilteringBorrow.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (descendingCheckBoxFilteringBorrow.isSelected()) {
                    ascendingCheckBoxFilteringBorrow.setSelected(false);
                    subCategoryFilteringBorrow();
                } else if (!ascendingCheckBoxFilteringBorrow.isSelected()) {
                    ascendingCheckBoxFilteringBorrow.setSelected(true);
                    subCategoryFilteringBorrow();
                }
            }
        });
        ascendingCheckBoxFilteringBorrow.setSelected(true); //default value
        ascendingCheckBoxFilteringBorrow.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ascendingCheckBoxFilteringBorrow.isSelected()) {
                    descendingCheckBoxFilteringBorrow.setSelected(false);
                    subCategoryFilteringBorrow();
                } else if (!descendingCheckBoxFilteringBorrow.isSelected()) {
                    descendingCheckBoxFilteringBorrow.setSelected(true);
                    subCategoryFilteringBorrow();
                }
            }
        });


        categoryComboBoxBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoryFilteringBorrow();
            }
        });
        SubCategoryComboBoxBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subCategoryFilteringBorrow();

            }
        });
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SortingComboBox();
            }
        });
        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                ascendingCheckBoxFiltering.setVisible(true);
                descendingCheckBoxFiltering.setVisible(true);
                sortComboBox.setVisible(false);
                categoryComboBox.setVisible(true);
                SubCategoryComboBox.setVisible(true);
                borrowALL.setVisible(false);
                returnAll.setVisible(false);
                list.setEnabled(true);
                ConfirmChoice.setVisible(false);

                ascendingCheckBoxFilteringBorrow.setVisible(false);
                descendingCheckBoxFilteringBorrow.setVisible(false);
                SubCategoryComboBoxBorrow.setVisible(false);
                categoryComboBoxBorrow.setVisible(false);

                lookForTextField.setVisible(false);
                lookForJLabel.setVisible(false);
                Search.setVisible(false);

                ascendingCheckBoxLOOKFOR.setVisible(false);
                descendingCheckBoxLOOKFOR.setVisible(false);


                TransactionComboBox.setVisible(false);
                transactionLeft.setVisible(false);
                transactionRight.setVisible(false);
                transactionLeftSelect.setVisible(false);
                transactionRightSelect.setVisible(false);

                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                String selectedCategory = (String) categoryComboBox.getSelectedItem();

                if(!selectedCategory.equals("Select")) {
                    if (Queries.getCurrentStateOfBooks(CurrentLibraryName).isEmpty()) {
                        ConfirmChoice.setEnabled(false);

                        list.setModel(modifiedModel);
                        ConfirmChoice.setEnabled(false);
                        SubCategoryComboBox.setEnabled(false);
                        categoryComboBox.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "No books to filter in library", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        ConfirmChoice.setEnabled(false);
                        SubCategoryComboBox.setEnabled(true);
                        categoryComboBox.setEnabled(true);


                        for (String book : Queries.getCurrentStateOfBooks(CurrentLibraryName)) {
                            modifiedModel.addElement(book);
                        }
                        list.setModel(modifiedModel);
                    }
                }
                else if(selectedCategory.equals("Production date")){

                    rangeComboBoxLeft.setVisible(true);
                    scrollableComboBox.setVisible(true);
                    SubCategoryComboBox.setEnabled(true);
                    categoryComboBox.setEnabled(true);
                    ConfirmChoice.setEnabled(false);
                }
                else{
                    list.setModel(modifiedModel);
                }
            }

        });


        ascendingCheckBoxLOOKFOR = new JCheckBox("Sort search \uD83E\uDC79");
        ascendingCheckBoxLOOKFOR.setBounds(420, 185, 150, 30);

        descendingCheckBoxLOOKFOR = new JCheckBox("Sort search \uD83E\uDC7B");
        descendingCheckBoxLOOKFOR.setBounds(420, 205, 150, 30);
        add(ascendingCheckBoxLOOKFOR);
        add(descendingCheckBoxLOOKFOR);

        ascendingCheckBoxLOOKFOR.setVisible(false);
        descendingCheckBoxLOOKFOR.setVisible(false);

        descendingCheckBoxLOOKFOR.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (descendingCheckBoxLOOKFOR.isSelected()) {
                    ascendingCheckBoxLOOKFOR.setSelected(false);
                    sortFunction();
                } else if (!ascendingCheckBoxLOOKFOR.isSelected()) {
                    ascendingCheckBoxLOOKFOR.setSelected(true);
                    sortFunction();
                }
            }
        });
        ascendingCheckBoxLOOKFOR.setSelected(true);
        ascendingCheckBoxLOOKFOR.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ascendingCheckBoxLOOKFOR.isSelected()) {
                    descendingCheckBoxLOOKFOR.setSelected(false);
                   sortFunction();
                } else if (!descendingCheckBoxFiltering.isSelected()) {
                    descendingCheckBoxFiltering.setSelected(true);
                    sortFunction();
                }
            }
        });

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel model = new DefaultListModel();
                String textToSearch = lookForTextField.getText();
                if (textToSearch.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Type title to look for", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    sortFunction();
                }
            }
        });

        borrowALL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
             //   User user = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.getChoosenUserName());


                    int odp = JOptionPane.showConfirmDialog(null, "Do you want to borrow all books?");
                    if (odp == JOptionPane.YES_OPTION) {

                        for (String book : Queries.getAllAvailableBook(CurrentLibraryName)) {
                           String title=extractTitle(book);
                           Queries.insertBorrowedBookToHistory(userChooseIFrame.getChoosenUserName(),title,CurrentLibraryName,"is borrowed");
                        }

                        Queries.setAllBooksBorrowed(CurrentLibraryName,userChooseIFrame.getSelectedUserValue(),"AVAILABLE","BORROWED");

                        JOptionPane.showMessageDialog(null, "All books have been borrowed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                        DefaultListModel<String> modifiedModel1 = new DefaultListModel<>();

                        for (String book : Queries.getAllAvailableBook(CurrentLibraryName)) {
                            modifiedModel.addElement(book);
                        }

                        list.setModel(modifiedModel1);
                     //   booksLabel.setText(RefreshListOfAvailableBook(library) + " books available in library");
                        if (!Queries.checkIfAnyBookIsAvailable(CurrentLibraryName)) {
                            borrowALL.setEnabled(false);
                            ConfirmChoice.setEnabled(false);
                            SubCategoryComboBoxBorrow.setEnabled(false);
                            categoryComboBoxBorrow.setEnabled(false);
                        }
                    }
            }
        });
        returnAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int odp = JOptionPane.showConfirmDialog(null, "Do you want to return all books?");
                if (odp == JOptionPane.YES_OPTION) {

                    for(String book : Queries.getAllBorrowedBooksByUser(CurrentLibraryName,userChooseIFrame.getSelectedUserValue())){
                        String title=extractTitle(book);
                        Queries.insertBorrowedBookToHistory(userChooseIFrame.getSelectedUserValue(),title,CurrentLibraryName,"is returned");
                    }


                    Queries.setAllBooksAvailable(CurrentLibraryName,null,"BORROWED","AVAILABLE",userChooseIFrame.getSelectedUserValue());
                    JOptionPane.showMessageDialog(null, "All books have been borrowed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);



                    DefaultListModel<String> modifiedModel1 = new DefaultListModel<>();
                    for (String book : Queries.getAllBorrowedBooksByUser(CurrentLibraryName,userChooseIFrame.getSelectedUserValue())) {
                        modifiedModel1.addElement(book);
                    }
                    list.setModel(modifiedModel1);

                 //   booksLabel.setText(RefreshListOfAvailableBook(library) + " books available in library");

                    if (!Queries.checkIfAnyBookIsInStatusBorrowed(CurrentLibraryName,userChooseIFrame.getSelectedUserValue())) {
                        ConfirmChoice.setEnabled(false);
                        returnAll.setEnabled(false);
                    }
                }
            }
        });


        transactionLeft = new JTextField();
        add(transactionLeft);
        transactionLeft.setEnabled(false);

        transactionRight = new JTextField();
        add(transactionRight);
        transactionRight.setEnabled(false);



        transactionLeft.setBounds(360, 190, 100, 40);
        transactionRight.setBounds(480,190,100,40);

        transactionLeft.setVisible(false);
        transactionRight.setVisible(false);


        transactionLeftSelect= new JButton("Select");
        add(transactionLeftSelect);

        transactionRightSelect = new JButton("Select");
        add(transactionRightSelect);



        transactionLeftSelect.setBounds(360, 240, 100, 30);

        transactionLeftSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalendarIFrame(UserActionFrame.this,"Left");
                transactionFiltering();
            }
        });
        transactionRightSelect.setBounds(480,240,100,30);

        transactionRightSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalendarIFrame(UserActionFrame.this,"Right");
                transactionFiltering();
            }
        });
        transactionLeftSelect.setVisible(false);
        transactionRightSelect.setVisible(false);



        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(150, 20, 600, 150);
        add(scrollPane);
        setSize(900, 400);
        setTitle("User logged as " + userChooseIFrame.getChoosenUserName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        ascendingCheckBox.setVisible(false);
        descendingCheckBox.setVisible(false);
        sortComboBox.setVisible(false);
        categoryComboBox.setVisible(false);
        SubCategoryComboBox.setVisible(false);

    }


    public  void subCategoryFiltering(){

        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        String selectedValue = (String) SubCategoryComboBox.getSelectedItem();

        DefaultListModel<String> modifiedModel = new DefaultListModel<>();
        if (selectedCategory.equals("Genre")) {


            if (ascendingCheckBoxFiltering.isSelected()) {
              modifiedModel.addAll(Queries.filterBookByGenre(CurrentLibraryName,selectedValue,"ASC"));

            }
            if (descendingCheckBoxFiltering.isSelected()) {
                modifiedModel.addAll(Queries.filterBookByGenre(CurrentLibraryName,selectedValue,"DESC"));
            }
            list.setModel(modifiedModel);

            if (modifiedModel.isEmpty()) {
                list.setModel(modifiedModel);
                JOptionPane.showMessageDialog(null,
                        "No book meets the criteria Category " + selectedCategory + " and " + selectedValue, "Message", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (selectedCategory.equals("Author")) {
            String[] partsOfAuthors=selectedValue.split(" ");

            if (ascendingCheckBoxFiltering.isSelected()) {
            modifiedModel.addAll(Queries.filterBookByAuthorNameAndSurname(CurrentLibraryName,partsOfAuthors[1],partsOfAuthors[0],"ASC"));
            }
            if (descendingCheckBoxFiltering.isSelected()) {
                modifiedModel.addAll(Queries.filterBookByAuthorNameAndSurname(CurrentLibraryName,partsOfAuthors[1],partsOfAuthors[0],"DESC"));
            }
            list.setModel(modifiedModel);

            if (modifiedModel.isEmpty()) {
                list.setModel(modifiedModel);
                JOptionPane.showMessageDialog(null,
                        "No book meets the criteria Category " + selectedCategory + " and " + selectedValue, "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(selectedCategory.equals("Status")){

            if (ascendingCheckBoxFiltering.isSelected()) {
                modifiedModel.addAll(Queries.filterBookByStatus(CurrentLibraryName, selectedValue,"ASC"));
            }
            if (descendingCheckBoxFiltering.isSelected()) {
                modifiedModel.addAll(Queries.filterBookByStatus(CurrentLibraryName, selectedValue,"DESC"));
            }
            list.setModel(modifiedModel);

            if (modifiedModel.isEmpty()) {
                list.setModel(modifiedModel);
                JOptionPane.showMessageDialog(null,
                        "No book meets the criteria Category " + selectedCategory + " and " + selectedValue, "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        else if(selectedCategory.equals("Production date")) {

            Integer minValue = (Integer) rangeComboBoxLeft.getSelectedItem();
            Integer MaxValue = (Integer) scrollableComboBox.getSelectedItem();

            if (minValue != 0 || MaxValue != 0) {
                if (ascendingCheckBoxFiltering.isSelected()) {
                    modifiedModel.addAll(Queries.filterBookByProductionDate(CurrentLibraryName, minValue, MaxValue, "ASC"));
                    list.setModel(modifiedModel);
                }
                if (descendingCheckBoxFiltering.isSelected()) {
                    modifiedModel.addAll(Queries.filterBookByProductionDate(CurrentLibraryName, minValue, MaxValue, "DESC"));
                    list.setModel(modifiedModel);
                }

                if (modifiedModel.isEmpty()) {
                    list.setModel(modifiedModel);
                    JOptionPane.showMessageDialog(null,
                            "No book meets the criteria Category " + selectedCategory + " and range between min: " + minValue + " and " + MaxValue, "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        else if(selectedCategory.equals("Pages")) {

            Integer minValue = (Integer) rangeComboBoxLeft.getSelectedItem();
            Integer MaxValue = (Integer) scrollableComboBox.getSelectedItem();

            if (minValue != 0 || MaxValue != 0) {
                if (ascendingCheckBoxFiltering.isSelected()) {
                    modifiedModel.addAll(Queries.filterBookByPages(CurrentLibraryName, minValue, MaxValue, "ASC"));
                    list.setModel(modifiedModel);
                }
                if (descendingCheckBoxFiltering.isSelected()) {
                    modifiedModel.addAll(Queries.filterBookByPages(CurrentLibraryName, minValue, MaxValue, "DESC"));
                    list.setModel(modifiedModel);
                }

                if (modifiedModel.isEmpty()) {
                    list.setModel(modifiedModel);
                    JOptionPane.showMessageDialog(null,
                            "No book meets the criteria Category " + selectedCategory + " and range between min: " + minValue + " and " + MaxValue, "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    public  void subCategoryFilteringBorrow(){

        String selectedCategory = (String) categoryComboBoxBorrow.getSelectedItem();
        String selectedValue = (String) SubCategoryComboBoxBorrow.getSelectedItem();

        DefaultListModel<String> modifiedModel = new DefaultListModel<>();

        if (selectedCategory.equals("Genre")) {


            if (ascendingCheckBoxFilteringBorrow.isSelected()) {
                modifiedModel.addAll(Queries.filterBookByGenreOnlyAvailable(CurrentLibraryName,selectedValue,"ASC"));

            }
            if (descendingCheckBoxFilteringBorrow.isSelected()) {
                modifiedModel.addAll(Queries.filterBookByGenreOnlyAvailable(CurrentLibraryName,selectedValue,"DESC"));
            }
            list.setModel(modifiedModel);

            if (modifiedModel.isEmpty()) {
                list.setModel(modifiedModel);
                JOptionPane.showMessageDialog(null,
                        "No book meets the criteria Category " + selectedCategory + " and " + selectedValue, "Message", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (selectedCategory.equals("Author")) {
            String[] partsOfAuthors=selectedValue.split(" ");

            if (ascendingCheckBoxFilteringBorrow.isSelected()) {
                modifiedModel.addAll(Queries.filterBookByAuthorNameAndSurnameOnlyAvailable(CurrentLibraryName,partsOfAuthors[1],partsOfAuthors[0],"ASC"));
            }
            if (descendingCheckBoxFilteringBorrow.isSelected()) {
                modifiedModel.addAll(Queries.filterBookByAuthorNameAndSurnameOnlyAvailable(CurrentLibraryName,partsOfAuthors[1],partsOfAuthors[0],"DESC"));
            }
            list.setModel(modifiedModel);

            if (modifiedModel.isEmpty()) {
                list.setModel(modifiedModel);
                JOptionPane.showMessageDialog(null,
                        "No book meets the criteria Category " + selectedCategory + " and " + selectedValue, "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (selectedCategory.equals("All available")) {

            if (ascendingCheckBoxFilteringBorrow.isSelected()) {
                modifiedModel.addAll(Queries.getAllAvailableBookSorting(CurrentLibraryName,"ASC"));

            }
            if (descendingCheckBoxFilteringBorrow.isSelected()) {
                modifiedModel.addAll(Queries.getAllAvailableBookSorting(CurrentLibraryName,"DESC"));
            }
            list.setModel(modifiedModel);

            if (modifiedModel.isEmpty()) {
                list.setModel(modifiedModel);
                JOptionPane.showMessageDialog(null,
                        "No book meets the criteria Category " + selectedCategory + " and " + selectedValue, "Message", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }
    public void categoryFiltering(){


        List<String> listOfAuthorSurnames = new ArrayList<>();

        for (String author: Queries.getAllAuthorsInLibrary(CurrentLibraryName)) {
            if (!listOfAuthorSurnames.contains(author)) {
                listOfAuthorSurnames.add(author);
            }
        }


        subcategoriesMap.put("Select", Arrays.asList(" "));
        subcategoriesMap.put("Status", Arrays.asList(Status.AVAILABLE.toString(),Status.BORROWED.toString()));
        subcategoriesMap.put("Author", listOfAuthorSurnames);
        subcategoriesMap.put("Genre", Arrays.asList("Akcji","Przygodowa", "ScienceFiction", "Romans", "Historyczne", "Akademickie", "Finansowe", "Dramat"));
        subcategoriesMap.put("Production date", Arrays.asList("Select range"));
        subcategoriesMap.put("Pages", Arrays.asList("Select range"));


        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        List<String> subcategories = subcategoriesMap.get(selectedCategory);
        String subcategoriesArray[] = subcategories.toArray(new String[subcategories.size()]);


        if (subcategories != null && !selectedCategory.equals("Select") && !selectedCategory.equals("Production date") && !selectedCategory.equals("Pages")) {
            SubCategoryComboBox.setEnabled(true);
            SubCategoryComboBox.setModel(new DefaultComboBoxModel<>(subcategoriesArray));
            SubCategoryComboBox.setVisible(true);
            rangeComboBoxLeft.setVisible(false);
            scrollableComboBox.setVisible(false);
            subCategoryFiltering();

        }else if(selectedCategory.equals("Production date") || selectedCategory.equals("Pages")){
            SubCategoryComboBox.setModel(new DefaultComboBoxModel<>(subcategoriesArray));
            rangeComboBoxLeft.setVisible(true);
            scrollableComboBox.setVisible(true);
            SubCategoryComboBox.setEnabled(false);
            SubCategoryComboBox.setVisible(true);
            subCategoryFiltering();
        }
        else {
            list.setModel(new DefaultListModel<>());
            SubCategoryComboBox.setModel(new DefaultComboBoxModel<>(subcategoriesArray));
            SubCategoryComboBox.setEnabled(false);
            rangeComboBoxLeft.setVisible(false);
            scrollableComboBox.setVisible(false);
            JOptionPane.showMessageDialog(null,
                    "Choose filter parameter", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void categoryFilteringBorrow(){

        List<String> listOfAuthorSurnames = new ArrayList<>();

        for (String author: Queries.getAllAuthorsInLibrary(CurrentLibraryName)) {
            if (!listOfAuthorSurnames.contains(author)) {
                listOfAuthorSurnames.add(author);
            }
        }

        subcategoriesMap.put("Select", Arrays.asList(" "));
        subcategoriesMap.put("Author", listOfAuthorSurnames);
        subcategoriesMap.put("Genre", Arrays.asList("Akcji","Przygodowa", "ScienceFiction", "Romans", "Historyczne", "Akademickie", "Finansowe", "Dramat"));
        subcategoriesMap.put("All available", Arrays.asList(" "));
        String selectedCategory = (String) categoryComboBoxBorrow.getSelectedItem();

        List<String> subcategories = subcategoriesMap.get(selectedCategory);
        String subcategoriesArray[] = subcategories.toArray(new String[subcategories.size()]);


        if (subcategories != null && !selectedCategory.equals("Select") && !selectedCategory.equals("All available")) {
            ConfirmChoice.setEnabled(true);
            borrowALL.setEnabled(false);
            ascendingCheckBoxFilteringBorrow.setVisible(true);
            descendingCheckBoxFilteringBorrow.setVisible(true);
            SubCategoryComboBoxBorrow.setEnabled(true);
            SubCategoryComboBoxBorrow.setModel(new DefaultComboBoxModel<>(subcategoriesArray));
            SubCategoryComboBoxBorrow.setVisible(true);
            subCategoryFilteringBorrow();
        } else if(selectedCategory.equals("Select")) {
            SubCategoryComboBoxBorrow.setModel(new DefaultComboBoxModel<>());
            SubCategoryComboBoxBorrow.setEnabled(false);
            ConfirmChoice.setEnabled(false);
            borrowALL.setEnabled(false);
            list.setModel(new DefaultListModel<>());
            ascendingCheckBoxFilteringBorrow.setVisible(false);
            descendingCheckBoxFilteringBorrow.setVisible(false);
            JOptionPane.showMessageDialog(null,
                        "Choose filter parameter", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        else { //ALL
            ConfirmChoice.setEnabled(true);
            borrowALL.setEnabled(true);
            ascendingCheckBoxFilteringBorrow.setVisible(true);
            descendingCheckBoxFilteringBorrow.setVisible(true);
            DefaultListModel model= new DefaultListModel<>();
            model.addAll(Queries.getAllAvailableBook(CurrentLibraryName));
            SubCategoryComboBoxBorrow.setEnabled(false);
            SubCategoryComboBoxBorrow.setModel(new DefaultComboBoxModel<>());
            list.setModel(model);
        }
    }
    public  void SortingComboBox(){
        DefaultListModel<String> modifiedModel = new DefaultListModel<>();

        String selectedSortOption = (String) sortComboBox.getSelectedItem();

                if (ascendingCheckBox.isSelected()) {
                    modifiedModel.addAll(Queries.sortBookByParameter(CurrentLibraryName,selectedSortOption.toLowerCase(),"ASC"));
                    list.setModel(modifiedModel);
                }

                if (descendingCheckBox.isSelected()) {
                    modifiedModel.addAll(Queries.sortBookByParameter(CurrentLibraryName,selectedSortOption.toLowerCase(),"DESC"));
                    list.setModel(modifiedModel);
                }

        }
        public void sortFunction(){
            DefaultListModel model = new DefaultListModel();
            String textToSearch = lookForTextField.getText();
            if (ascendingCheckBoxLOOKFOR.isSelected()) {
                model.addAll(Queries.searchForBookByTitle(CurrentLibraryName,textToSearch,"ASC"));
                list.setModel(model);
            }
            else if(descendingCheckBoxLOOKFOR.isSelected()){
                model.addAll(Queries.searchForBookByTitle(CurrentLibraryName,textToSearch,"DESC"));
                list.setModel(model);}
        }
        public void transactionFiltering(){
            String choice =(String) TransactionComboBox.getSelectedItem();
            DefaultListModel model= new DefaultListModel<>();
            if(!choice.equals("All")) {

                if(!transactionLeft.getText().isEmpty() && !transactionRight.getText().isEmpty()){
                    model.addAll(Queries.getAllTransactionByUserStatusANDDate(CurrentLibraryName, userChooseIFrame.getChoosenUserName(),choice,transactionLeft.getText(),transactionRight.getText()));
                    if(model.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No results for that range of date FROM: "+transactionLeft.getText()+" TO: "+transactionRight.getText(), "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    list.setModel(model);
                }
                else {
                    model.addAll(Queries.getAllTransactionByUserStatus(CurrentLibraryName, userChooseIFrame.getSelectedUserValue(), choice));
                    list.setModel(model);
                }
            }
            else{ //ALL
                if(!transactionLeft.getText().isEmpty() && !transactionRight.getText().isEmpty()){
                    model.addAll(Queries.getAllTransactionByUserAndDate(CurrentLibraryName, userChooseIFrame.getChoosenUserName(),transactionLeft.getText(),transactionRight.getText()));
                    if(model.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No results for that range of date FROM: "+transactionLeft.getText()+" TO: "+transactionRight.getText(), "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    list.setModel(model);
                }
                else{
                    model.addAll(Queries.getAllTransactionByUser(CurrentLibraryName, userChooseIFrame.getChoosenUserName()));
                    list.setModel(model);
                }

            }
        }
}
