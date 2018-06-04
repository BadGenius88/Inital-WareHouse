package ui;

import interactor.LibrarianInteractor;
import interactor.OnRequestCompleteListener;
import models.BookInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class AddBookForm {
    private JFrame optframe;
    private JButton okButton;
    private JButton cancelButton;
    private final String[] title = {"Title: ", "Author:", "Quantity: "};
    JTextField[] textFields;
    private int numPaired = title.length;
    private BookInfo bookInfo;
    public String mtitle, mauthor, mdescript;
    public int mquantity = -1;

    private LibrarianInteractor librarianInteractor;
    private OnRequestCompleteListener listener;

    public AddBookForm(LibrarianInteractor librarianInteractor, OnRequestCompleteListener listener) {
        this.librarianInteractor = librarianInteractor;
        this.listener = listener;

        optframe = new JFrame("Add new Book");
        optframe.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 400);

        textFields = new JTextField[title.length];
        for (int i = 0; i < numPaired; i++) {
            JLabel label = new JLabel(title[i], JLabel.TRAILING);
            panel.add(label);
            textFields[i] = new JTextField();
            textFields[i].setPreferredSize(new Dimension(300,20));
            label.setLabelFor(textFields[i]);
            panel.add(textFields[i]);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        okButton = new JButton("Ok");
        okButton.setSize(100, 30);
        cancelButton = new JButton("Cancel");
        cancelButton.setSize(100,30);

        okButton.addActionListener(e -> {
            if (!isEmpty() && checkInput()) {
                mtitle = textFields[0].getText();
                mauthor = textFields[1].getText();
                mquantity = Integer.parseInt(textFields[2].getText());
                bookInfo = new BookInfo(mtitle, mauthor, mdescript, mquantity);
                librarianInteractor.addBook(bookInfo, listener);
                optframe.dispatchEvent(new WindowEvent(optframe, WindowEvent.WINDOW_CLOSING));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input");
            }
        });
        cancelButton.addActionListener(e -> optframe.dispatchEvent(new WindowEvent(optframe, WindowEvent.WINDOW_CLOSING)));

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel);

        optframe.add(panel);
        optframe.setVisible(true);
    }

    public boolean isEmpty() {
        for (int i = 0; i < numPaired; i++) {
            if (textFields[i].getText().length() == 0)
                return true;
        }
        return false;
    }

    public boolean checkInput() {
        String number = textFields[2].getText();
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
