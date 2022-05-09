package j_monopoly.models.cards;

import j_monopoly.enums.CardType;

/**
 * Defines a card of type company.
 */
public class CompanyCard extends Card {
    /**
     * A number that identifies the action this card should perform.
     */
    public final int actionId;

    /**
     * The cost of this property.
     */
    public final int cost;

    /**
     * Initializes a new instance of a company card.
     */
    public CompanyCard(String title,
                       String description,
                       int actionId,
                       int cost) {
        super(CardType.COMPANY, title, description);

        this.actionId = actionId;
        this.cost = cost;
    }
}
