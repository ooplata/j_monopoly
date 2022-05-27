package j_monopoly.game.dialogs;

import j_monopoly.assets.Resources;
import j_monopoly.models.Player;
import j_monopoly.models.Property;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public final class BuildDialog extends JDialog {
    private boolean canBuild = false;
    private final Player player;

    public static BuildDialog createDialog(Player player) {
        BuildDialog dialog = new BuildDialog(player);
        dialog.pack();
        return dialog;
    }

    private void refillNumberBox(Property property) {
        numberBox.removeAllItems();

        int maxHouses = 1;
        int houseCost = property.getCostForNewHouses(maxHouses);

        while (houseCost > 0) {
            numberBox.addItem(maxHouses);
            maxHouses++;
            houseCost = property.getCostForNewHouses(maxHouses);
        }
    }

    private void showSimpleDialog(String header, String content) {
        this.setVisible(false);
        SimpleMessageDialog.createDialog(player.name, header, content).setVisible(true);
    }

    private void onOK() {
        if (canBuild) {
            Property prop = (Property) propertyBox.getSelectedItem();
            int amount = (Integer) Objects.requireNonNull(numberBox.getSelectedItem());

            assert prop != null;
            int cost = prop.getCostForNewHouses(amount);
            if (cost > player.money) {
                showSimpleDialog("You can't get that!", "You have $" + player.money + ", but your house(s) cost $" + cost + ". Sorry.");
            } else {
                boolean result = prop.addHouses(amount);
                if (result) {
                    player.money -= cost;
                    int houses = prop.getHouses();

                    String content = "You now have $" + player.money + ". Your house(s) cost $" + cost + ", and the property now has ";
                    if (houses == 5) content += "a hotel.";
                    else content += houses + " houses.";

                    showSimpleDialog("Purchased!", content);
                }
            }
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private BuildDialog(Player player) {
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

        this.player = player;

        setTitle(player.name);
        setIconImage(Resources.getAppIcon());

        for (Property property : player.properties) {
            if (property.isGroupPurchased && property.getCostForNewHouses(1) > 0) {
                propertyBox.addItem(property);
                canBuild = true;
            }
        }

        if (!canBuild) {
            propName.setText("You can't build any houses!");
            number.setVisible(false);

            propertyBox.setVisible(false);
            numberBox.setVisible(false);
        } else {
            propertyBox.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Property property = (Property) e.getItem();
                    refillNumberBox(property);
                }
            });

            assert player.properties.peekLast() != null;
            refillNumberBox(player.properties.peekLast());
        }
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel header;
    private JLabel propName;
    private JComboBox<Property> propertyBox;
    private JLabel number;
    private JComboBox<Integer> numberBox;
}
