package j_monopoly.game.dialogs;

import j_monopoly.game.board.GameHelper;
import j_monopoly.game.board.Players;
import j_monopoly.game.dialogs.spaces.PropertySpaceDialog;
import j_monopoly.models.Player;
import j_monopoly.models.Property;
import j_monopoly.models.RollResult;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class CurrentTurnDialog extends JDialog {
    private final Player player;
    private boolean hideWhenDone = false;

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

        optionBox.addItem("Roll");
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
        if (hideWhenDone) dispose();
        else setVisible(true);
    }

    private void handleTurn(String action) {
        switch (action) {
            case checkProps -> AllPropertiesDialog.createDialog(player).setVisible(true);
            case build -> BuildDialog.createDialog(player).setVisible(true);
            case outOfJail -> {
                player.tryExitJailWithCard();
                showSimpleDialog("You used a get out of jail card!", "I too love bribing police officers!");
                finishTurn();
            }
            case giveUp -> {
                GameHelper.bankrupt();
                showSimpleDialog("You gave up!", "That's all. You can now leave.");
                finishTurn();
            }
            default -> {
                if (player.isInJail()) {
                    boolean result = tryEscapeJail();
                    if (result) {
                        showSimpleDialog("You got out of jail!", "yay!");
                    } else {
                        showSimpleDialog("You're still in jail!", "yay..?");
                        finishTurn();
                    }
                } else {
                    handleRoll(GameHelper.rollTwoDice());
                }
            }
        }
    }

    private void handleRoll(RollResult<Object> result) {
        this.setVisible(false);
        RollResultDialog.createDialog(result.firstDie, result.secondDie, false).setVisible(true);
        if (result.passedGo) showSimpleDialog("You passed Go!", "You get $200.");

        switch (result.space.type) {
            case GO -> showSimpleDialog("You landed on Go!", "Want a cookie? Well too bad we only have $200.");
            case PROPERTY -> {
                Property prop = (Property) result.space.data;
                if (prop.isOwned) {
                    Player owner = Players.getPropertyOwnerByTitle(prop.info.title);
                    assert owner != null;

                    // If we're the owner, do nothing.
                    if (owner.name.equals(player.name)) {
                        showSimpleDialog("You landed on " + prop.info.title + "!", "That's owned by you, so you can stay for free.");
                        return;
                    }

                    if (player.tryPayRent(prop)) {
                        owner.money += prop.getRent();
                        showSimpleDialog("You landed on " + prop.info.title + "!", "That's owned by " + owner.name + ". You paid $" + prop.getRent() + " for rent.");
                    } else {
                        GameHelper.bankrupt();
                        showSimpleDialog("You can't pay rent at " + prop.info.title + "!", "Rent there costs $" + prop.getRent() + ", but you only have $" + player.money + ". Now you're bankrupt. :(");
                    }
                } else {
                    PropertySpaceDialog.createDialog(prop, player).setVisible(true);
                }
            }
            case FREE_PASS -> showSimpleDialog("Free Parking!", "That's all. You can take a nap.");
            case COMMUNITY_CHEST -> {
                player.addOutOfJailCards(1);
                showSimpleDialog("Community Chest", "You got a get out of jail for free card!");
            }
            case CHANCE -> {
                player.addOutOfJailCards(1);
                showSimpleDialog("Chance", "You got a get out of jail for free card!");
            }
            case JAIL -> showSimpleDialog("Say hi to the criminals!", "You're at the jail :) enjoy while it lasts!");
            case GO_TO_JAIL -> {
                player.goToJail();
                showSimpleDialog("You got taken to jail!", "Time to delete the gym, hit your lawyer, and Facebook up.");
            }
        }

        // Only finish turns if player doesn't get doubles
        if (result.firstDie != result.secondDie) finishTurn();
    }

    private boolean tryEscapeJail() {
        for (int i = 0; i < 3; i++) {
            int first = player.rollSingleDie();
            int second = player.rollSingleDie();

            RollResultDialog.createDialog(first, second, true).setVisible(true);

            // If the die rolls are equal, take the player out
            if (first == second) {
                player.exitJail();
                break;
            }
        }

        return !player.isInJail();
    }

    private void showSimpleDialog(String header, String content) {
        this.setVisible(false);
        SimpleMessageDialog.createDialog(player.name, header, content).setVisible(true);
    }

    private void finishTurn() {
        GameHelper.finishTurn();
        hideWhenDone = true;
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
