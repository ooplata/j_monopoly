package j_monopoly.game.dialogs;

import j_monopoly.game.board.GameHelper;
import j_monopoly.models.Player;

import javax.swing.*;

public class PlayerStatusDialog extends JDialog {
    private final Player player;

    public PlayerStatusDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        player = GameHelper.getCurrentPlayer();
        header.setText("Stats for " + player.name);

        statsArea.append("Money: $" + player.money + "\n");
        if (!player.inJail) {
            statsArea.append("Not in jail :D\n");
        } else {
            statsArea.append("In jail :(\n");
        }
    }

    private void onOK() {
        dispose();
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel header;
    private JTextArea statsArea;
}
