package j_monopoly.game.board;

import j_monopoly.models.cards.PropertyCard;

public class Properties {
    /**
     * Gets a property card from a comma space (", ") separated string.
     */
    public PropertyCard getPropertyCardFromString(String str) {
        String[] split = str.split(", ");

        String title = split[0];
        String desc = split[1];
        String group = split[2];

        int inGrp = Integer.parseInt(split[3]);
        int cost = Integer.parseInt(split[4]);
        int rent = Integer.parseInt(split[5]);
        int rentWithGrp = Integer.parseInt(split[6]);
        int house = Integer.parseInt(split[7]);
        int hotel = Integer.parseInt(split[8]);
        int withHouse = Integer.parseInt(split[9]);
        int withTwoHouse = Integer.parseInt(split[10]);
        int withThreeHouse = Integer.parseInt(split[11]);
        int withFourHouse = Integer.parseInt(split[12]);
        int withHotel = Integer.parseInt(split[13]);

        return new PropertyCard(
                title,
                desc,
                group,
                inGrp,
                cost,
                rent,
                rentWithGrp,
                house,
                hotel,
                withHouse,
                withTwoHouse,
                withThreeHouse,
                withFourHouse,
                withHotel
        );
    }
}
