package j_monopoly.models.cards;

import j_monopoly.enums.CardType;

/**
 * Defines a card and its behavior.
 */
public class Card {
    public final CardType cardType;

    /**
     * A title for this card.
     */
    public final String title;

    /**
     * A description for this card.
     */
    public final String description;

    public Card(CardType cardType, String title, String description) {
        this.cardType = cardType;
        this.title = title;
        this.description = description;
    }
}
