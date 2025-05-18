package theknife.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import theknife.Controller;
import theknife.obj.restaurant.Restaurant;

/**
 * The {@code PreviewRestaurant} panel provides a visual summary of a {@link Restaurant} object.
 * <p>
 * This panel displays key information such as the restaurant's name, address, rating, awards, and green star status.
 * It allows the user to view a summarized version of the restaurant and navigate to the full detail page by clicking a label.
 * </p>
 * <p>
 * This component is part of the GUI of the application and is typically added to a parent container managed by a {@link Controller}.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public class PreviewRestaurant extends javax.swing.JPanel {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code PreviewRestaurant} panel and initializes its components.
     * <p>
     * This constructor also sets the restaurant to insert the data needed to have a preview of the restaurant itself.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     * @param restaurant the {@link Restaurant} to set for the GUI preview
     * @param bg the background {@link Color} of the preview of the restaurant
     */
    public PreviewRestaurant(Controller controller, Restaurant restaurant, Color bg) {
        initComponents();
        this.controller = controller;
        this.restaurant = restaurant;
        this.bg = bg;
        initGUI();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code PreviewRestaurant} page.
     */
    private void initGUI() {
        initFields();
        initPreviewRestaurant();
        initEvents();
    }
    
    /**
     * Initializes the basic fields of the {@code PreviewRestaurant} panel.
     */
    private void initFields() {
//        try {
//            icon = ImageIO.read(ICON_FILE);
//        } catch (IOException e) {
//            LoggerUtils.logSevere("Error while loading the full star image: {0}", e);
//        }
        pnl_awardAndGreenStar = new JPanel(new GridBagLayout());
        lbl_name              = new JLabel(setMaxWidthContent(MAX_WIDTH,     restaurant.getName()));
        lbl_address           = new JLabel(setMaxWidthContent(MAX_WIDTH * 2, restaurant.getAddress()));
        lbl_rating            = new JLabel(String.valueOf(restaurant.getRating()));
        lbl_award             = new JLabel(restaurant.getAward());
        lbl_greenStar         = new JLabel(restaurant.isGreenStar() ? GREENSTAR_TRUE : GREENSTAR_FALSE);
        btn_details           = new JLabel(DETAILS);
        lbls                  = new JLabel[] {
            lbl_name,
            lbl_address,
            lbl_rating,
            lbl_award,
            lbl_greenStar,
            btn_details
        };
    }
    
    /**
     * Initializes the layout and appearance of the home page.
     */
    private void initPreviewRestaurant() {
        this.setBackground(bg);
        this.setLayout(new GridBagLayout());
        
        pnl_awardAndGreenStar.setOpaque(false);
        
        for (JLabel lbl : lbls) {
            lbl.setBackground(BG_DEFAULT);
            lbl.setForeground(FG_DEFAULT);
            lbl.setHorizontalAlignment(JLabel.CENTER);
            lbl.setVerticalAlignment(JLabel.CENTER);
            lbl.setFont(this.getFont());
            lbl.setOpaque(true);
        }
        
        lbl_rating.setHorizontalTextPosition(JLabel.LEFT);
        lbl_rating.setIconTextGap(ICON_GAP);
        
        lbl_award.setHorizontalTextPosition(JLabel.LEFT);
        lbl_award.setIconTextGap(ICON_GAP);
        
        lbl_greenStar.setHorizontalTextPosition(JLabel.LEFT);
        lbl_greenStar.setIconTextGap(ICON_GAP);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx      = 0;
        gbc.gridy      = 0;
        gbc.ipadx      = 10;
        gbc.ipady      = 10;
        gbc.gridwidth  = 1;
        gbc.gridheight = 2;
        gbc.insets     = INSETS;
        gbc.fill       = GridBagConstraints.BOTH;
        this.add(lbl_name, gbc);
        
        gbc.gridx++;
        gbc.gridwidth++;
        gbc.gridheight--;
        this.add(lbl_address, gbc);
        
        gbc.gridy++;
        this.add(lbl_rating, gbc);
        
        gbc.gridy++;
        gbc.gridx--;
        gbc.gridwidth--;
        this.add(btn_details, gbc);
        
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx     = 0;
        gbc1.gridy     = 0;
        gbc1.weightx   = 0.45;
        gbc1.weighty   = 1;
        gbc1.insets    = new Insets(0, 0, 0, 5);
        gbc1.fill      = GridBagConstraints.BOTH;
        pnl_awardAndGreenStar.add(lbl_award, gbc1);
        
        gbc1.gridx++;
        gbc1.insets    = new Insets(0, 5, 0, 0);
        pnl_awardAndGreenStar.add(lbl_greenStar, gbc1);
        
        gbc.gridx++;
        gbc.gridwidth++;
        this.add(pnl_awardAndGreenStar, gbc);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() {  
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                previewRestaurant_ComponentResized(e);
            }
        });      
        btn_details.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_details_MouseClicked(e);
            }
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the {@code PreviewRestaurant} {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     * 
     * @param e the component event triggered by resizing the GUI application
     */
    private void previewRestaurant_ComponentResized(java.awt.event.ComponentEvent e) {
        double avgPercentage = (double) ((this.getWidth() - SCROLLBAR_WIDTH) / ORIGINAL_WIDTH);
        lbl_name   .setText(setMaxWidthContent((int) ((MAX_WIDTH * avgPercentage) / 100) + MAX_WIDTH - SCROLLBAR_WIDTH, restaurant.getName()));
        lbl_address.setText(setMaxWidthContent((int) ((MAX_WIDTH * avgPercentage) / 100) + MAX_WIDTH - SCROLLBAR_WIDTH, restaurant.getAddress()));
    }
    
    /**
     * Handles the hover event on the details button {@link JLabel}.
     * <p>
     * When the button is clicked, the {@link Restaurant} given to this object will be passed to the {@link RestaurantGUI} page.
     * It is necessary to pass in order to handle the GUI page giving the restaurant's fields to the components.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button
     */
    private void btn_details_MouseClicked(java.awt.event.MouseEvent e) {
        restaurantGUI = new RestaurantGUI(controller, restaurant);
        controller.getPanelMain().getPanel().add(restaurantGUI, Page.RESTAURANT);
        controller.getPanelMain().showCard(Page.RESTAURANT);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Handles the width of a text inside a {@link JComponent}.
     * 
     * @param width - the width to set
     * @param text - the {@code JComponent}'s text
     * @return the {@code JComponent}'s text wrapped in HTML and basic CSS
     */
    private String setMaxWidthContent(int width, String text) {
        return "<html><div style='text-align: center; width: " + width + "px;'>" + text + "</div></html>";
    }
    //</editor-fold>
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(15, 166, 40));
        setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        setPreferredSize(new java.awt.Dimension(718, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Consts">
//    private final File               ICON_FILE              = AppPaths.getDataFile("img", "Full Star (256x256).png");
    private final Color              BG_DEFAULT             = new Color(85, 107, 10);
    private final Color              FG_DEFAULT             = new Color(47, 235, 78);
    private final Insets             INSETS                 = new Insets(5, 5, 5, 5);
    private final String             DETAILS                = "Go to details";
    private final String             GREENSTAR_TRUE         = "Green";
    private final String             GREENSTAR_FALSE        = "No Green";
    private final int                MAX_WIDTH              = 160;
    private final int                SCROLLBAR_WIDTH        = 16;
    private final int                ORIGINAL_WIDTH         = this.getPreferredSize().width;
    private final int                ICON_GAP               = 10;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    public  static RestaurantGUI      restaurantGUI;
    private        JPanel             pnl_awardAndGreenStar;
    private        JLabel             lbl_name;
    private        JLabel             lbl_address;
    private        JLabel             lbl_rating;
    private        JLabel             lbl_award;
    private        JLabel             lbl_greenStar;
    private        JLabel             btn_details;
    private        JLabel[]           lbls;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller    controller;
    private final Restaurant    restaurant;
    private       Color         bg;
//    private       BufferedImage icon;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
