package j_monopoly.game.dialogs;

import javax.swing.*;
import java.awt.event.*;

public final class SimpleMessageDialog extends JDialog {
    private SimpleMessageDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onOk() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        // call onOk() on ESCAPE
        contentPane.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
    }

    public static SimpleMessageDialog createDialog(String title, String header, String content) {
        SimpleMessageDialog dialog = new SimpleMessageDialog();

        dialog.setTitle(title);
        dialog.header.setText(header);
        dialog.content.setText(content);

        dialog.pack();
        return dialog;
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel header;
    private JLabel content;
}
