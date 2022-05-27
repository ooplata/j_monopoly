package j_monopoly.assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * Dummy class to be able to load resources from this package.
 */
public final class Resources {
    private static BufferedImage icon;

    public static URL getResource(String name) {
        return Objects.requireNonNull(Resources.class.getResource(name));
    }

    public static InputStream getResourceAsStream(String name) {
        return Objects.requireNonNull(Resources.class.getResourceAsStream(name));
    }

    public static BufferedImage getAppIcon() {
        if (icon == null) {
            try {
                icon = ImageIO.read(Resources.getResource("Token.png"));
            } catch (IOException ignored) {
            }
        }
        return icon;
    }
}
