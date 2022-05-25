package j_monopoly.game.dialogs;

import j_monopoly.game.board.GameHelper;
import j_monopoly.models.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PlayerStatusDialog extends JDialog {
    private final Player player;

    public PlayerStatusDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        // call onOk() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        // call onOk() on ESCAPE
        contentPane.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        player = GameHelper.getCurrentPlayer();
        header.setText("Stats for " + player.name);

        statsArea.append("Money: $" + player.money + "\n");
        if (!player.isInJail()) {
            statsArea.append("Not in jail :D\n");
        } else {
            statsArea.append("In jail :(\n");
        }
    }

    private void onOK() {
        dispose();
    }

    public static PlayerStatusDialog createDialog() {
        PlayerStatusDialog dialog = new PlayerStatusDialog();
        dialog.pack();
        return dialog;
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel header;
    private JTextArea statsArea;
}
