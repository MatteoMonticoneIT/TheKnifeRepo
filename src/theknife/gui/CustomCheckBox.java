package theknife.gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.io.File;
import java.io.IOException;
import javax.swing.JCheckBox;
import simple.logging.LoggerUtils;
import theknife.obj.AppPaths;

/**
 * {@code CustomCheckBox} is a subclass of {@link JCheckBox} that displays a custom character
 * using a personalized font instead of the default checkbox appearance.
 * <p>
 * This component is typically used to represent symbolic states, such as toggling password
 * visibility, with a glyph rendered in place of the traditional checkmark.
 * </p>
 * <p>
 * The custom font is loaded from the application resources and defaults to the system font if
 * loading fails. The glyph is centered and antialiased for better visual quality.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class CustomCheckBox extends JCheckBox {
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Font customFont;
    private char displayChar;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructs a new {@code CustomCheckBox} with default character and font.
     * Attempts to load the custom font from application resources.
     */
    public CustomCheckBox() {
        try {
            File fontFile = AppPaths.getDataFile("img", "Password.ttf");
            this.setCustomFont(Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(24f));
        } catch (FontFormatException | IOException e) {
            LoggerUtils.logWarning("Unable to set a personalized font: {0}", e);
            this.setCustomFont(super.getFont());
        }
        this.setPreferredSize(super.getPreferredSize());
        this.setOpaque(super.isOpaque());
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the current custom font used for rendering the checkbox character.
     *
     * @return the {@link Font} instance
     */
    public final Font getCustomFont() {
        return customFont;
    }

    /**
     * Sets the font used to render the character.
     *
     * @param customFont the font to set
     */
    public final void setCustomFont(Font customFont) {
        this.customFont = customFont;
    }

    /**
     * Sets the character to be displayed by this checkbox.
     *
     * @param c the character to render
     */
    public final void setCharacter(char c) {
        this.displayChar = c;
        repaint();
    }

    /**
     * Returns the character currently displayed by this checkbox.
     *
     * @return the display character
     */
    public final char getCharacter() {
        return this.displayChar;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Painting">
    /**
     * Paints the component by drawing the specified character at the center using the custom font.
     *
     * @param g the {@link Graphics} context to use for painting
     */
    @Override
    protected final void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GlyphVector gv = customFont.createGlyphVector(g2d.getFontRenderContext(), String.valueOf(this.getCharacter()));
        Shape shape = gv.getGlyphOutline(0);

        g2d.translate(super.getHeight() / 2, super.getHeight() / 2 + 6);
        g2d.setColor(getForeground());
        g2d.draw(shape);

        g2d.dispose();
    }
    //</editor-fold>
}