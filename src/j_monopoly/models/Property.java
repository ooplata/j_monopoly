package j_monopoly.models;

import j_monopoly.models.cards.PropertyCard;

/**
 * Defines a property. Gets its info from a PropertyCard object.
 */
public class Property {
    public final PropertyCard info;

    /**
     * Whether this property is owned by any player. Defaults to false.
     */
    public Boolean isOwned = false;

    /**
     * Whether this property's group is fully owned by any player.
     * Defaults to false.
     */
    public Boolean isGroupOwned = false;

    /**
     * Amount of houses in this property. If the value is 5, it means the
     * property has a single hotel.
     */
    private int houses = 0;

    public Property(PropertyCard info) {
        this.info = info;
    }

    /**
     * Gets the price of rent based on the current conditions.
     */
    public int getRent() {
        switch (houses) {
            case 1:
                return info.rentWithHouse;
            case 2:
                return info.rentWithTwoHouses;
            case 3:
                return info.rentWithThreeHouses;
            case 4:
                return info.rentWithFourHouses;
            case 5:
                return info.rentWithHotel;
            default:
                if (isGroupOwned) {
                    return info.rentWithGroup;
                }
                return info.rent;
        }
    }

    /**
     * Gets the amount of houses in this property. Return
     * value of 5 indicates a single hotel.
     */
    public int getHouses() {
        return houses;
    }

    /**
     * Adds the specified number of houses to this property.
     * @param amount Amount of houses to add.
     * @return Whether the houses were added.
     */
    public boolean addHouses(int amount) {
        int cost = getCostForNewHouses(amount);
        if (cost != 0)
            houses += amount;

        return cost != 0;
    }

    /**
     * Gets the cost of adding the specified number of houses
     * to this property.
     * @param amount Amount of houses to add.
     * @return The amount of money the user should pay for the
     * houses. If the specified number of houses is invalid, this
     * value will be 0.
     */
    public int getCostForNewHouses(int amount) {
        if (amount < 1 || houses + amount > 5)
            return 0;

        // If the player now has a hotel, calculate the price needed
        // for one hotel, plus the amount of houses minus one
        if (houses + amount == 5) {
            amount--;
            return (info.houseCost * amount) + info.hotelCost;
        }
        return info.houseCost * amount;
    }
}
