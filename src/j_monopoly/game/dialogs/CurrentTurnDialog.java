package j_monopoly.game.dialogs;

import j_monopoly.game.board.GameHelper;
import j_monopoly.models.Player;
import j_monopoly.models.RollResult;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class CurrentTurnDialog extends JDialog {
    private final Player player;

    private final String roll = "Roll";
    private final String checkProps = "Check properties";
    private final String build = "Build houses";
    private final String giveUp = "Give up";
    private final String outOfJail = "Use out of jail card";

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

        player = GameHelper.getCurrentPlayer();

        playerTurn.setText("Your turn, " + player.name + "!");
        money.setText("You have $" + player.money);

        optionBox.addItem(roll);
        if (!player.isInJail()) {
            optionBox.addItem(checkProps);
            optionBox.addItem(build);
            optionBox.addItem(giveUp);
        } else {
            if (player.getOutOfJailCards() > 0) {
                optionBox.addItem(outOfJail);
            }
        }
    }

    private void onOK() {
        handleTurn((String) Objects.requireNonNull(optionBox.getSelectedItem()));
        GameHelper.finishTurn();
        dispose();
    }

    private void handleTurn(String action) {
        switch (action) {
            case outOfJail -> {
                player.tryExitJailWithCard();
            }
            case giveUp -> {
                GameHelper.bankrupt();
            }
            default -> {
                if (player.isInJail()) {
                    boolean result = tryEscapeJail();
                    if (result) {
                        SimpleMessageDialog.createDialog(player.name, "You got out of jail!", "yay!");
                    } else {
                        SimpleMessageDialog.createDialog(player.name, "You're still in jail!", "yay..?");
                    }
                } else {
                    RollResult result = roll();
                }
            }
        }
    }

    private RollResult<Object> roll() {
        RollResult result = GameHelper.rollTwoDice();
        return result;
    }

    private boolean tryEscapeJail() {
        for (int i = 0; i < 3; i++) {
            int first = player.rollSingleDie();
            int second = player.rollSingleDie();

            // If the die rolls are equal, take the player out
            if (first == second) {
                player.exitJail();
                break;
            }
        }

        return !player.isInJail();
    }

    private void onCancel() {
        dispose();
    }

    public static CurrentTurnDialog createDialog() {
        CurrentTurnDialog dialog = new CurrentTurnDialog();
        dialog.pack();
        return dialog;
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel playerTurn;
    private JComboBox<String> optionBox;
    private JLabel money;
}
