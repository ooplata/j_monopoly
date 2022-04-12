package j_monopoly.models.cards;

import j_monopoly.enums.CardType;
import j_monopoly.models.Card;

/**
 * Defines a card of type company.
 */
public class CompanyCard extends Card {
    /**
     * A number that identifies the action this card should perform.
     */
    public final int actionId;

    /**
     * Path to an image that's shown to the player.
     */
    public final String imagePath;

    /**
     * Initializes a new instance of a company card.
     */
    public CompanyCard(String title,
                       String description,
                       int actionId,
                       String imagePath) {
        super(CardType.COMPANY, title, description);

        this.actionId = actionId;
        this.imagePath = imagePath;
    }
}
