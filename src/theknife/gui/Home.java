package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import theknife.Controller;
import theknife.obj.restaurant.Restaurant;

/**
 * The Home class represents the home screen of the application.
 * <p>
 * It contains a list of restaurants found by typing on the {@code Upperbar} {@link JTextField}.<br>
 * The components are arranged in a {@link BorderLayout} and properly styled.<br>
 * This class handles events such as button clicks and text input to interact with the rest of the application.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class Home extends javax.swing.JPanel {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code Home} panel and initializes its components.
     * <p>
     * This constructor also sets the main panel to control the screen transitions using {@link PanelMain#showCard}.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     */
    public Home(Controller controller) {
        initComponents();
        this.controller = controller;
        pnl_main = controller.getPanelMain();
        initGUI();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the home page.
     */
    private void initGUI() {
        initFields();
        initHome();
        initEvents();
    }
    
    /**
     * Initializes the basic fields of the {@code Home} panel.
     */
    private void initFields() {
        pnl_home            = new JPanel(new GridBagLayout());
        pnl_restaurants     = new JPanel(new GridLayout(controller.getRestaurants().getList().size(), 1, 15, 15));
        scrlPnl_restaurants = new JScrollPane(pnl_restaurants);
        lbl_title           = new JLabel(TITLE);
    }
    
    /**
     * Initializes the layout and appearance of the home page.
     */
    private void initHome() {
        this.setLayout(new BorderLayout());
        
        pnl_restaurants.setBackground(this.getBackground().darker());
        pnl_home       .setBackground(this.getBackground());
        
        scrlPnl_restaurants.getVerticalScrollBar()      .setUI(new CustomScrollBar());
        scrlPnl_restaurants.getHorizontalScrollBar()    .setUI(new CustomScrollBar());
        scrlPnl_restaurants.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrlPnl_restaurants.setVerticalScrollBarPolicy  (ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrlPnl_restaurants.setBorder                   (PADDING_SCROLLPANE);
        
        lbl_title.setBackground(BG_TITLE);
        lbl_title.setForeground(FG_DEFAULT);
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setVerticalAlignment  (JLabel.CENTER);
        lbl_title.setFont(new Font(this.getFont().getFontName(), this.getFont().getStyle(), 48));
        lbl_title.setOpaque(true);
        
        int i = 0;
        for (Restaurant restaurant : controller.getRestaurants().getList()) {
            i++;
            if (i > 3)
                break;
            pnl_restaurants.add(new PreviewRestaurant(controller, restaurant));
        }
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx   = 0;
        gbc.gridy   = 0;
        gbc.weightx = 0.25;
        gbc.ipadx   = 30;
        gbc.insets  = INSETS;
        gbc.anchor  = GridBagConstraints.CENTER;
        gbc.fill    = GridBagConstraints.VERTICAL;
        pnl_home.add(lbl_title, gbc);
        
        gbc.gridy++;
        gbc.weighty = 0.75;
        gbc.ipadx   = 0;
        gbc.insets  = INSETS_ZERO;
        gbc.fill    = GridBagConstraints.BOTH;
        pnl_home.add(scrlPnl_restaurants, gbc);
        
        this.add(new Upperbar(controller), BorderLayout.NORTH);
        this.add(pnl_home,                 BorderLayout.CENTER);
    }
    
    private void initEvents() {
        scrlPnl_restaurants.addMouseWheelListener((java.awt.event.MouseWheelEvent e) -> {
            scrlPnl_filters_MouseWheelMoved(e);
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the {@link JScrollPane} {@link JScrollBar}.
     * 
     * @param e the component event triggered by wheel-scrolling using the mouse.
     */
    private void scrlPnl_filters_MouseWheelMoved(java.awt.event.MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        int fasterScroll = notches * 40;

        JScrollBar vertical = scrlPnl_restaurants.getVerticalScrollBar();
        vertical.setValue(vertical.getValue() + fasterScroll);
    }
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(50, 255, 47));
        setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N

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
    private final Color  FG_DEFAULT         = Color.BLACK;
    private final Color  BG_TITLE           = new Color(101, 179, 0);
    private final Border PADDING_SCROLLPANE = BorderFactory.createEmptyBorder(30, 60, 30, 60);
    private final Insets INSETS             = new Insets(20, 0, 0, 0);
    private final Insets INSETS_ZERO        = new Insets(0, 0, 0, 0);  
    private final String TITLE              = "All restaurants";  
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private final PanelMain   pnl_main;
    private       JPanel      pnl_home;
    private       JPanel      pnl_restaurants;
    private       JScrollPane scrlPnl_restaurants;
    private       JLabel      lbl_title;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller controller;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
