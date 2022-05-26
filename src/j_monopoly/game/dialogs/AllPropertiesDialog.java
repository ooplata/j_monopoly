package j_monopoly.game.dialogs;

import j_monopoly.models.Player;
import j_monopoly.models.Property;

import javax.swing.*;
import java.awt.event.*;

public final class AllPropertiesDialog extends JDialog {
    public static AllPropertiesDialog createDialog(Player player) {
        AllPropertiesDialog dialog = new AllPropertiesDialog(player);
        dialog.pack();
        return dialog;
    }

    private void onOK() {
        dispose();
    }

    private AllPropertiesDialog(Player player) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        // call onOK() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        // call onOK() on ESCAPE
        contentPane.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setTitle(player.name);
        header.setText("All properties owned by " + player.name);

        for (Property prop : player.properties)
            properties.append(prop.info.title + ": " + prop.info.group + "\n");
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel header;
    private JTextArea properties;
}
