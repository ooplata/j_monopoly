package j_monopoly.game.dialogs;

import j_monopoly.assets.Resources;
import j_monopoly.models.RollResult;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public final class RollResultDialog extends JDialog {
    public static RollResultDialog createDialog(int first, int second, boolean isInJail) {
        RollResultDialog dialog = new RollResultDialog(first, second, isInJail);
        dialog.pack();
        return dialog;
    }

    private void onOK() {
        dispose();
    }

    private RollResultDialog(int first, int second, boolean isInJail) {
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

        this.result.setText("You rolled " + (first + second) + "!");
        if (first == second) {
            if (isInJail) {
                extraInfo.setText("You got doubles, so you're out!");
            } else {
                extraInfo.setText("You got doubles, so you get another turn!");
            }
        } else {
            if (isInJail) {
                extraInfo.setText("No doubles, no luck. :(");
            }
        }

        try {
            BufferedImage firstPic = ImageIO.read(Resources.getResource(first + ".png"));
            BufferedImage secondPic = ImageIO.read(Resources.getResource(second + ".png"));

            this.first.setIcon(new ImageIcon(firstPic));
            this.second.setIcon(new ImageIcon(secondPic));
        } catch (IOException ignored) {
        }
    }

    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel result;
    private JLabel first;
    private JLabel second;
    private JLabel extraInfo;
}
