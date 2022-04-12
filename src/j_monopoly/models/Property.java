package j_monopoly.models;

import j_monopoly.models.cards.PropertyCard;

/**
 * Defines a property. Gets its info from a PropertyCard object.
 */
public class Property {
    public final PropertyCard card;

    /**
     * Whether this property is owned by any player. Defaults to false.
     */
    private Boolean isOwned = false;

    /**
     * Whether this property's group is fully owned by any player.
     * Defaults to false.
     */
    private Boolean isGroupOwned = false;

    /**
     * Whether this property is mortgaged by any player. Defaults to false.
     */
    private Boolean isMortgaged = false;

    /**
     * Amount of houses in this property. If the value is 5, it means the
     * property has a single hotel.
     */
    private int houses = 0;

    public Property(PropertyCard card) {
        this.card = card;
    }

    /**
     * Gets the price of rent based on the current conditions.
     */
    public int getRent() {
        switch (houses) {
            case 1:
                return card.rentWithHouse;
            case 2:
                return card.rentWithTwoHouses;
            case 3:
                return card.rentWithThreeHouses;
            case 4:
                return card.rentWithFourHouses;
            case 5:
                return card.rentWithHotel;
            default:
                if (isGroupOwned) {
                    return card.rentWithGroup;
                }
                return card.rent;
        }
    }
}
