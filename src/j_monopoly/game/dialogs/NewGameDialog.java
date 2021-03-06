package j_monopoly.game.dialogs;

import j_monopoly.assets.Resources;
import j_monopoly.game.board.GameHelper;
import j_monopoly.game.views.MainPage;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public final class NewGameDialog extends JDialog {
    private JComboBox<Integer> playersComboBox;

    public static NewGameDialog createDialog() {
        NewGameDialog dialog = new NewGameDialog();
        dialog.pack();
        return dialog;
    }

    private void onOK() {
        GameHelper.startNewGame((Integer) Objects.requireNonNull(playersComboBox.getSelectedItem()));
        JFrame frame = new MainPage();
        frame.setResizable(false);

        this.setVisible(false);
        frame.setVisible(true);

        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private NewGameDialog() {
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

        setTitle("New game");
        setIconImage(Resources.getAppIcon());
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel newGameLabel;
}
