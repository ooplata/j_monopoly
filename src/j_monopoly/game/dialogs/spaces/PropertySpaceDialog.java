package j_monopoly.game.dialogs.spaces;

import j_monopoly.assets.Resources;
import j_monopoly.enums.PropertyPurchaseResult;
import j_monopoly.game.dialogs.SimpleMessageDialog;
import j_monopoly.models.Player;
import j_monopoly.models.Property;

import javax.swing.*;
import java.awt.event.*;

public final class PropertySpaceDialog extends JDialog {
    public static PropertySpaceDialog createDialog(Property property, Player player) {
        PropertySpaceDialog dialog = new PropertySpaceDialog(property, player);
        dialog.pack();
        return dialog;
    }

    private void onOK(Property property, Player player) {
        PropertyPurchaseResult result = player.purchaseProperty(property);
        String header = switch (result) {
            case PURCHASED -> "Purchased!";
            case GROUP_PURCHASED -> "Full group purchased!";
            case NOT_PURCHASED -> "Unable to purchase";
        };

        String content = switch (result) {
            case PURCHASED -> "You purchased the property for $" + property.info.cost + ".";
            case GROUP_PURCHASED ->
                    "You purchased the property for $" + property.info.cost + ", and now own the full " + property.info.group + " group!";
            case NOT_PURCHASED -> "You don't have enough money to purchase this property.";
        };

        this.setVisible(false);
        SimpleMessageDialog.createDialog("Purchase result", header, content).setVisible(true);

        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private PropertySpaceDialog(Property property, Player player) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK(property, player));
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

        setTitle(property.info.description);
        setIconImage(Resources.getAppIcon());

        propName.setText(property.info.title);
        propInfo.setText("It costs $" + property.info.cost + " and belongs to group " + property.info.group + ". Would you like to buy it?");
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel propName;
    private JLabel propInfo;
}
