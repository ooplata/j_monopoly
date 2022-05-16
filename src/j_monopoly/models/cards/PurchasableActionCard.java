package j_monopoly.models.cards;

import j_monopoly.enums.CardType;

public class PurchasableActionCard extends PurchasableCard {
    /**
     * A number that identifies the action this card should perform.
     */
    public final int actionId;

    public PurchasableActionCard(CardType cardType,
                                 String title,
                                 String description,
                                 int cost,
                                 int actionId) {
        super(cardType, title, description, cost);

        this.actionId = actionId;
    }
}
