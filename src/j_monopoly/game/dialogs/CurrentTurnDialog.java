package j_monopoly.game.dialogs;

import j_monopoly.game.board.GameHelper;

import javax.swing.*;
import java.awt.event.*;

public class CurrentTurnDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel playerTurn;
    private JComboBox optionBox;

    public CurrentTurnDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

        playerTurn.setText("Your turn, player " + (GameHelper.getCurrentPlayerIndex() + 1) + "!");
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static CurrentTurnDialog createDialog() {
        CurrentTurnDialog dialog = new CurrentTurnDialog();
        dialog.pack();
        return dialog;
    }
}
