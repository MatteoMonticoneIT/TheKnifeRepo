package theknife.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import simple.logging.LoggerUtils;
import theknife.obj.AppPaths;

/**
 * {@code CustomJLabel} is a subclass of {@link JLabel} that appends a custom symbol rendered using a personalized font to the standard label text.
 * <p>
 * It is useful for scenarios where symbolic icons must be displayed next to standard text without using image icons.
 * </p>
 * <p>
 * The custom glyph is drawn directly via {@link Graphics2D} using high-quality rendering.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class CustomJLabel extends JLabel {
    
    //<editor-fold defaultstate="collapsed" desc="Consts">
    private final int DEFAULT_SPACING = 4;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Font  customFont;
    private char  character;
    private Color characterColor;
    private int   charSpacing;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructs a new {@code CustomJLabel} with default character and font.
     * Attempts to load the custom font from application resources.
     * @param text - the text in the {@link JLabel}
     * @param character - the {@code char} used to display a certain caracter within the ttf file
     */
    public CustomJLabel(String text, char character) {
        super(text);
        this.setCharacter(character);
        this.setCharacterSpacing(DEFAULT_SPACING);
        try {
            File fontFile = AppPaths.getDataFile("img", "Stars.ttf");
            this.setCustomFont(Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(24f));
        } catch (FontFormatException | IOException e) {
            LoggerUtils.logWarning("Unable to set a personalized font: {0}", e);
            this.setCustomFont(this.getFont());
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the current custom font used for rendering the label character.
     *
     * @return the {@link Font} instance
     */
    public final Font getCustomFont() {
        return customFont;
    }

    /**
     * Sets the font used to render the character.
     *
     * @param customFont - the font to set
     */
    public final void setCustomFont(Font customFont) {
        this.customFont = customFont;
    }

    /**
     * Sets the character to be displayed by this label.
     *
     * @param character - the character to render
     */
    public final void setCharacter(char character) {
        this.character = character;
        repaint();
    }

    /**
     * Returns the character currently displayed by this label.
     *
     * @return the display character
     */
    public final char getCharacter() {
        return this.character;
    }

    /**
     * Returns the color currently used for the character.
     * 
     * @return the color of the character 
     */
    public Color getCharacterColor() {
        return characterColor;
    }

    /**
     * Sets the character color used to paint it.
     * 
     * @param characterColor - the color to set 
     */
    public void setCharacterColor(Color characterColor) {
        this.characterColor = characterColor;
    }
    
     /**
     * Gets the spacing between the text and the custom character.
     * 
     * @return spacing in pixels
     */
    public final int getCharacterSpacing() {
        return charSpacing;
    }

    /**
     * Sets the spacing between the text and the custom character.
     * 
     * @param spacing - the spacing in pixels
     */
    public final void setCharacterSpacing(int spacing) {
        this.charSpacing = spacing;
        repaint();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public final void setCustomFontSize(float size) {
        if (this.getCustomFont() != null) {
            this.setCustomFont(this.getCustomFont().deriveFont(size));
            repaint();
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Painting">
    /**
     * Paints the component by drawing the specified character at the center using the custom font.
     *
     * @param g - the {@link Graphics} context to use for painting
     */
    @Override
    protected final void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D  g2d         = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
        GlyphVector gv          = customFont.createGlyphVector(g2d.getFontRenderContext(), String.valueOf(this.getCharacter()));
        Shape       charShape   = gv.getGlyphOutline(0);
        int         charWidth   = (int) gv.getVisualBounds().getWidth();
        
        FontMetrics txtMetrics  = g.getFontMetrics(this.getFont());
        int         txtWidth    = txtMetrics.stringWidth(this.getText());
        
        int         ascent      = g.getFontMetrics(this.getCustomFont()).getAscent();
        int         y           = (this.getHeight() - g.getFontMetrics(this.getCustomFont()).getHeight()) / 2 + ascent;
        
        int charX;
        if (this.getText() == null || this.getText().isEmpty()) {
            switch (this.getHorizontalAlignment()) {
                case JLabel.LEFT:
                    charX = this.getCharacterSpacing();
                    break;
                case JLabel.CENTER:
                    charX = (this.getWidth() - charWidth) / 2;
                    break;
                case JLabel.RIGHT:
                    charX = this.getWidth() - charWidth - this.getCharacterSpacing();
                    break;
                default:
                    charX = this.getCharacterSpacing();
            }
        } else {
            switch (this.getHorizontalAlignment()) {
                case JLabel.LEFT:
                    charX = txtWidth + this.getCharacterSpacing();
                    break;
                case JLabel.RIGHT:
                    charX = this.getWidth() - txtWidth - charWidth - this.getCharacterSpacing();
                    break;
                case JLabel.CENTER:
                default:
                    int txtStartX = (this.getWidth() - txtWidth) / 2;
                    charX = txtStartX + txtWidth + this.getCharacterSpacing();
                    break;
            }
        }
        
        g2d.translate(charX, y);
        g2d.setColor(getCharacterColor());
        g2d.fill(charShape);

        g2d.dispose();
    }
    //</editor-fold>
}
