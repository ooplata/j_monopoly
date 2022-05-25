package j_monopoly.game.dialogs;

import j_monopoly.models.Player;
import j_monopoly.models.Property;

import javax.swing.*;
import java.awt.event.*;

public class AllPropertiesDialog extends JDialog {
    public AllPropertiesDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onOK() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        // call onOK() on ESCAPE
        contentPane.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
    }

    public static AllPropertiesDialog createDialog(Player player) {
        AllPropertiesDialog dialog = new AllPropertiesDialog();

        dialog.setTitle(player.name);
        dialog.header.setText("All properties owned by " + player.name);

        for (Property prop : player.properties)
            dialog.properties.append(prop.info.title + ": " + prop.info.group + "\n");

        dialog.pack();
        return dialog;
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel header;
    private JTextArea properties;
}
