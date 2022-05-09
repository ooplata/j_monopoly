package j_monopoly.models.cards;

import j_monopoly.enums.CardType;

/**
 * Defines a card of type company.
 */
public class CompanyCard extends ActionCard {
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
        super(CardType.COMPANY, title, description, actionId);

        this.cost = cost;
    }
}
