package j_monopoly.models.cards;

import j_monopoly.enums.CardType;

public class ActionCard extends Card {
    /**
     * A number that identifies the action this card should perform.
     */
    public final int actionId;

    public ActionCard(CardType cardType,
                      String title,
                      String description,
                      int actionId) {
        super(cardType, title, description);
        this.actionId = actionId;
    }
}
