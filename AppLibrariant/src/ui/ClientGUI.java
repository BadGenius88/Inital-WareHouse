package ui;

import interactor.LibrarianInteractor;
import interactor.OnGetBooksCompleteListener;
import interactor.OnGetBorrowedBooksCompleteListener;
import interactor.OnRequestCompleteListener;
import models.BookPreview;
import models.BorrowedBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientGUI implements ActionListener {
    private static final String CMD_ADD = "add";
    private static final String CMD_EDIT = "edit";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_REFRESH = "refresh";
    private static final String CMD_RETURN = "return";

    private DefaultTableModel defaultBooksTableModel;
    private int booksTableSelectedRowIndex = -1;
    private String[] booksTableTitle = {"Title", "Author", "Quantity", "Borrowed Quantity"};
    private List<BookPreview> bookPreviews;

    private DefaultTableModel defaultBorrowedBooksTableModel;
    private int borrowedBooksTableSelectedRowIndex = -1;
    private String[] borrowedBooksTableTitle = {"Title", "Author", "Borrowed Quantity", "Borrowed Date", "Returned Date"};
    private List<BorrowedBook> borrowedBooks;

    private LibrarianInteractor librarianInteractor;

    public static void main(String[] args) {
        ClientGUI client = new ClientGUI();
        client.refreshBooks();
        client.refreshBorrowedBooks();
    }

    private ClientGUI() {
        prepareGUI();
        librarianInteractor = new LibrarianInteractor();
    }

    private void refreshBooks() {
        librarianInteractor.getBooks(new OnGetBooksCompleteListener() {
            @Override
            public void onGetBooksComplete(List<BookPreview> books) {
                bookPreviews = books;
                defaultBooksTableModel.setRowCount(0);
                for (BookPreview bookPreview : books) {
                    defaultBooksTableModel.addRow(bookPreview.toArray());
                }
            }

            @Override
            public void onError(String message) {
                JOptionPane.showMessageDialog(null, "Connection error");
            }
        });
    }

    private void refreshBorrowedBooks() {
        librarianInteractor.getBorrowedBooks(new OnGetBorrowedBooksCompleteListener() {

            @Override
            public void onGetBorrowedBooksComplete(List<BorrowedBook> books) {
                borrowedBooks = books;
                defaultBorrowedBooksTableModel.setRowCount(0);
                for (BorrowedBook borrowedBook : books) {
                    defaultBorrowedBooksTableModel.addRow(borrowedBook.toArray());
                }
            }

            @Override
            public void onError(String message) {
                JOptionPane.showMessageDialog(null, "Connection error");
            }
        });
    }

    private void prepareGUI() {
        JFrame frame = new JFrame("Library Management");
        frame.setBackground(Color.green);
        frame.setSize(1000, 600);
        frame.setLayout(new FlowLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 0, 400, 50);
        buttonPanel.setLayout(new FlowLayout());

        JTable booksTable = new JTable(new DefaultTableModel(booksTableTitle, 0));
        defaultBooksTableModel = (DefaultTableModel) booksTable.getModel();

        JScrollPane booksScroll = new JScrollPane(booksTable);
        booksScroll.setBounds(0, 0, 600, 200);
        booksTable.setFillsViewportHeight(true);

        JButton addBtn = new JButton("Add Book");
        JButton editBtn = new JButton("Edit Book");
        JButton delBtn = new JButton("Delete Book");
        JButton refreshBtn = new JButton("Refresh Books");
        JButton returnBtn = new JButton("Return Book");

        addBtn.setActionCommand(CMD_ADD);
        editBtn.setActionCommand(CMD_EDIT);
        delBtn.setActionCommand(CMD_DELETE);
        refreshBtn.setActionCommand(CMD_REFRESH);
        returnBtn.setActionCommand(CMD_RETURN);

        addBtn.addActionListener(this);
        editBtn.addActionListener(this);
        delBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
        returnBtn.addActionListener(this);

        ListSelectionModel model = booksTable.getSelectionModel();
        model.addListSelectionListener(e -> {
            if (!model.isSelectionEmpty()) {
                booksTableSelectedRowIndex = model.getMinSelectionIndex();
            }
        });

        JPanel booksContainer = new JPanel();
        booksContainer.setBounds(0, 0, 600, 400);
        booksContainer.setLayout(new BoxLayout(booksContainer, BoxLayout.Y_AXIS));

        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(delBtn);
        buttonPanel.add(refreshBtn);

        booksContainer.add(buttonPanel);
        booksContainer.add(booksScroll);

        JPanel borrowedContainer = new JPanel();
        borrowedContainer.setBounds(0, 0, 800, 400);
        borrowedContainer.setLayout(new BoxLayout(borrowedContainer, BoxLayout.Y_AXIS));

        JTable borrowedBooksTable = new JTable(new DefaultTableModel(borrowedBooksTableTitle, 0));
        defaultBorrowedBooksTableModel = (DefaultTableModel) borrowedBooksTable.getModel();

        ListSelectionModel borrowedModel = borrowedBooksTable.getSelectionModel();
        borrowedModel.addListSelectionListener(e -> {
            if (!borrowedModel.isSelectionEmpty()) {
                borrowedBooksTableSelectedRowIndex = borrowedModel.getMinSelectionIndex();
            }
        });

        JScrollPane borrowedScroll = new JScrollPane(borrowedBooksTable);
        borrowedScroll.setBounds(0, 0, 600, 200);
        booksTable.setFillsViewportHeight(true);

        JPanel borrowedButtonPanel = new JPanel();
        borrowedButtonPanel.setBounds(0, 0, 400, 50);
        borrowedButtonPanel.setLayout(new FlowLayout());
        borrowedButtonPanel.add(returnBtn);

        booksContainer.add(borrowedButtonPanel);
        borrowedContainer.add(returnBtn);
        borrowedContainer.add(borrowedScroll);

        frame.add(booksContainer);
        frame.add(borrowedContainer);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case CMD_ADD: {
                new AddBookForm(librarianInteractor, new OnRequestCompleteListener() {
                    @Override
                    public void onRequestSuccess() {
                        refreshBooks();
                    }

                    @Override
                    public void onError(String message) {
                        System.out.println(message);
                        JOptionPane.showMessageDialog(null, "Connection error");
                    }
                });
            }
            break;

            case CMD_EDIT: {
                if (booksTableSelectedRowIndex != -1) {
                    new EditBookForm(bookPreviews.get(booksTableSelectedRowIndex), librarianInteractor,
                            new OnRequestCompleteListener() {
                                @Override
                                public void onRequestSuccess() {
                                    refreshBooks();
                                }

                                @Override
                                public void onError(String message) {
                                    JOptionPane.showMessageDialog(null, "Connection error");
                                }
                            });
                } else {
                    JOptionPane.showMessageDialog(null, "Please select row");
                }
            }
            break;

            case CMD_DELETE: {
                if (booksTableSelectedRowIndex != -1) {
                    librarianInteractor.deleteBook(bookPreviews.get(booksTableSelectedRowIndex).getId(), new OnRequestCompleteListener() {
                        @Override
                        public void onRequestSuccess() {
                            refreshBooks();
                        }

                        @Override
                        public void onError(String message) {
                            JOptionPane.showMessageDialog(null, "Connection error");
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Please select row");
                }
            }
            break;

            case CMD_REFRESH: {
                refreshBooks();
            }
            break;

            case CMD_RETURN:{
                if(borrowedBooksTableSelectedRowIndex != -1) {
                    librarianInteractor.returnBorrowedBook(borrowedBooks.get(borrowedBooksTableSelectedRowIndex).getId(),
                            new OnRequestCompleteListener() {
                        @Override
                        public void onRequestSuccess() {
                            refreshBorrowedBooks();
                        }

                        @Override
                        public void onError(String message) {
                            JOptionPane.showMessageDialog(null, "Connection error");
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Please select row");
                }
            }
            break;

            default: {
                break;
            }
        }
    }
}
