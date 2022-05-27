package j_monopoly.game.dialogs;

import j_monopoly.assets.Resources;

import javax.swing.*;
import java.awt.event.*;

public final class SimpleMessageDialog extends JDialog {
    public static SimpleMessageDialog createDialog(String title, String header, String content) {
        SimpleMessageDialog dialog = new SimpleMessageDialog(title, header, content);
        dialog.pack();
        return dialog;
    }

    private void onOK() {
        dispose();
    }

    private SimpleMessageDialog(String title, String header, String content) {
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

        this.setTitle(title);
        this.setIconImage(Resources.getAppIcon());

        this.header.setText(header);
        this.content.setText(content);
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel header;
    private JLabel content;
}
