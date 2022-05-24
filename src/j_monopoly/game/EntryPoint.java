package j_monopoly.game;

import j_monopoly.game.views.NewGameDialog;

public class EntryPoint {
    public static void main(String[] args) {
        NewGameDialog dialog = NewGameDialog.createDialog();
        dialog.setVisible(true);
    }
}
