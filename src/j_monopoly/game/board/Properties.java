package j_monopoly.game.board;

import j_monopoly.assets.Resources;
import j_monopoly.models.Property;
import j_monopoly.models.cards.PropertyCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public final class Properties {
    public static LinkedList<Property> properties = new LinkedList<>();

    /**
     * Populates the properties list with data from the default
     * properties file.
     */
    public static void populateList() {
        try (InputStream propStrm = Resources.getResourceAsStream("Properties.txt")) {
            assert propStrm != null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(propStrm));
            reader.lines().forEach(Properties::addPropertyToList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets a property card from a comma space (", ") separated string.
     */
    public static PropertyCard getPropertyCardFromString(String str) {
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

    private static void addPropertyToList(String str) {
        if (str.startsWith("#") || str.isEmpty()) return;

        PropertyCard card = getPropertyCardFromString(str);
        Property prop = new Property(card);

        properties.add(prop);
    }
}
