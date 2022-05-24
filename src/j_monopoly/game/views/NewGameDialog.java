package j_monopoly.game.views;

import j_monopoly.game.board.GameHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewGameDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel newGameLabel;
    private JComboBox<Integer> playersComboBox;

    public NewGameDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        for (int i = 2; i <= 12; i++) playersComboBox.addItem(i);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        GameHelper.startNewGame((Integer) playersComboBox.getSelectedItem());
        JFrame frame = MainPage.createFrame();

        frame.setSize(new Dimension(592, 704));
        frame.setResizable(false);

        this.setVisible(false);
        frame.setVisible(true);
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static NewGameDialog createDialog() {
        NewGameDialog dialog = new NewGameDialog();
        dialog.pack();

        return dialog;
    }
}
