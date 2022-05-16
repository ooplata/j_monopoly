package j_monopoly.models.cards;

import j_monopoly.enums.CardType;

public class PurchasableCard extends Card {
    /**
     * The cost of this item.
     */
    public final int cost;

    public PurchasableCard(CardType cardType,
                           String title,
                           String description,
                           int cost) {
        super(cardType, title, description);

        this.cost = cost;
    }
}
