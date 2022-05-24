package j_monopoly.models;

import j_monopoly.enums.SpaceType;

/**
 * Represents a space on the board, which can hold some data.
 * @param <T> Type of data the space can hold.
 */
public class Space<T> {
    /**
     * Some data the space can hold, usually a property or ID.
     */
    public final T data;

    /**
     * Type of space.
     */
    public final SpaceType type;

    public Space(T data, SpaceType type) {
        this.data = data;
        this.type = type;
    }
}
