package j_monopoly.models;

import j_monopoly.enums.CardType;

/**
 * Defines a card of type property.
 */
public class PropertyCard extends Card {
    /**
     * The group this card belongs to, generally a color.
     */
    public final String group;

    /**
     * The cost of rent for this property when you own no houses, hotels or
     * entire groups.
     */
    public final int rent;

    /**
     * The cost of rent for this property if you own every property that
     * belongs to its group and have no houses/hotels.
     */
    public final int rentWithGroup;

    /**
     * The cost of houses for this property.
     */
    public final int houseCost;

    /**
     * The cost of a hotel for this property.
     */
    public final int hotelCost;

    /**
     * The cost of rent for this property if you own one house.
     */
    public final int rentWithHouse;

    /**
     * The cost of rent for this property if you own two houses.
     */
    public final int rentWithTwoHouses;

    /**
     * The cost of rent for this property if you own three houses.
     */
    public final int rentWithThreeHouses;

    /**
     * The cost of rent for this property if you own four houses.
     */
    public final int rentWithFourHouses;

    /**
     * The cost of rent for this property if you own one hotel.
     */
    public final int rentWithHotel;

    /**
     * How much you get for the mortgage of this property.
     */
    public final int mortgage;

    /**
     * The cost of reversing the mortgage for this property.
     */
    public final int reverseMortgage;

    /**
     * Initializes a new instance of a property card.
     */
    public PropertyCard(String title,
                        String description,
                        String group,
                        int rent,
                        int rentWithGroup,
                        int houseCost,
                        int hotelCost,
                        int rentWithHouse,
                        int rentWithTwoHouses,
                        int rentWithThreeHouses,
                        int rentWithFourHouses,
                        int rentWithHotel,
                        int mortgage,
                        int reverseMortgage) {
        super(CardType.PROPERTY, title, description);

        this.group = group;
        this.rent = rent;
        this.rentWithGroup = rentWithGroup;
        this.houseCost = houseCost;
        this.hotelCost = hotelCost;
        this.rentWithHouse = rentWithHouse;
        this.rentWithTwoHouses = rentWithTwoHouses;
        this.rentWithThreeHouses = rentWithThreeHouses;
        this.rentWithFourHouses = rentWithFourHouses;
        this.rentWithHotel = rentWithHotel;
        this.mortgage = mortgage;
        this.reverseMortgage = reverseMortgage;
    }
}
