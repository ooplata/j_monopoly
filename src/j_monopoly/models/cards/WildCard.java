package j_monopoly.models.cards;

import j_monopoly.enums.CardType;

/**
 * Defines a card of type wild.
 */
public class WildCard extends Card {
    /**
     * A number that identifies the action this card should perform.
     */
    public final int actionId;

    /**
     * Initializes a new instance of a wild card.
     */
    public WildCard(String title,
                    String description,
                    int actionId) {
        super(CardType.WILD, title, description);

        this.actionId = actionId;
    }
}
