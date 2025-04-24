package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

/**
 * A panel that represents the advanced search screen in the application.
 * <p>
 * This class contains the graphical elements for the advanced search interface, including a button that switches the view to the home screen when clicked.<br>
 * It is managed by the {@link PanelMain} class using a {@link CardLayout}.
 * </p>
 * 
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */
public class AdvancedSearch extends javax.swing.JPanel {

    /**
     * Creates a new {@code AdvancedSearch} {@link JPanel} and initializes its components.
     * <p>
     * This constructor also sets the main panel to control the screen transitions using {@link PanelMain#showCard}.
     * </p>
     * 
     * @param main the main panel that manages the screen layout
     */
    public AdvancedSearch(PanelMain main) {
        initComponents();
        pnl_main = main;
        initGUI();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code AdvancedSearch} page.
     */
    private void initGUI() {
        initFields();
        initAdvancedSearch();
        initEvents();
    }
    
    /**
     * Initializes the basic fields of the {@code AdvancedSearch} panel.
     */
    private void initFields() {
        pnl_filters         = new JPanel(new GridBagLayout());
        pnl_btn_apply       = new JPanel(new BorderLayout());
        pnl_btn_cancel      = new JPanel(new BorderLayout());
        pnl_ratingBar       = new JPanel(new GridLayout(1, RATINGS));
        pnl_btns            = new JPanel(new GridLayout(1, 2, 10, 10));
        pnl_services        = new JPanel(new GridLayout((int) Math.ceil(CHKBX_TXT.length / 2), 2, 10, 10));
        scrlPnl_filters     = new JScrollPane(pnl_filters);
        lbl_title           = new JLabel(TITLE);
        lbl_star            = new JLabel[RATINGS];
        btn_apply           = new JLabel(APPLY_FILTERS);
        btn_cancel          = new JLabel(CANCEL);
        txt_location        = new JTextField();
        sld_price           = new JSlider(MIN_PRICE, MAX_PRICE);
        chkbx_services      = new JCheckBox[CHKBX_TXT.length];
        txt_layerUI         = new RoundedComponentUI(ARC_TEXTFIELD);
        btn_layerUI         = new RoundedComponentUI(ARC_BUTTON);
        pnl_layerUI         = new RoundedComponentUI(ARC_PANEL);
        txt_locationRounded = new JLayer<>(txt_location, txt_layerUI);
        btn_applyRounded    = new JLayer<>(btn_apply,    btn_layerUI);
        btn_cancelRounded   = new JLayer<>(btn_cancel,   btn_layerUI);
        pnl_servicesRounded = new JLayer<>(pnl_services, pnl_layerUI);
    }
    
    /**
     * Initializes the layout and appearance of the {@code AdvancedSearch} page.
     */
    private void initAdvancedSearch() {
        this.setLayout(new BorderLayout());
        
        pnl_filters   .setBackground(Color.RED);
        pnl_ratingBar .setBackground(Color.CYAN);
        pnl_btn_apply .setBackground(Color.BLUE);
        pnl_btn_cancel.setBackground(Color.ORANGE);
        pnl_btns      .setBackground(Color.MAGENTA);
        pnl_services  .setBackground(Color.YELLOW);
        
        pnl_services.setBorder(PADDING_PANEL_SERVICES);
        
        for (int i = 0; i < chkbx_services.length; i++) {
            chkbx_services[i] = new JCheckBox(CHKBX_TXT[i]);
            chkbx_services[i].setFont(this.getFont());
            chkbx_services[i].setBackground(Color.MAGENTA);
            pnl_services.add(chkbx_services[i]);
        }
        
        scrlPnl_filters.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrlPnl_filters.setVerticalScrollBarPolicy  (ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        lbl_title.setBackground(this.getBackground());
        lbl_title.setForeground(FG_DEFAULT);
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setVerticalAlignment  (JLabel.CENTER);
        lbl_title.setFont(new Font(this.getFont().getFontName(), this.getFont().getStyle(), 48));
        lbl_title.setOpaque(true);
        
        for (JLabel lbl : lbl_star) {
            lbl = new JLabel();
            lbl.setPreferredSize(new Dimension(this.getPreferredSize().width, 80));
            lbl.setBackground(this.getBackground());
            lbl.setForeground(FG_DEFAULT);
            lbl.setHorizontalAlignment(JLabel.CENTER);
            lbl.setVerticalAlignment  (JLabel.CENTER);
            lbl.setFont(this.getFont());
            lbl.setOpaque(true);
            pnl_ratingBar.add(lbl);
        }
        
        txt_location.setBackground(BG_TEXTFIELD);
        txt_location.setForeground(FG_PLACEHOLDER);
        txt_location.setBorder    (PADDING_TEXTFIELD);
        txt_location.setPreferredSize(new Dimension(this.getPreferredSize().width, 40));
        txt_location.setFont(new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
        
        btn_apply.setBackground(BG_APPLY_BTN);
        btn_apply.setForeground(FG_DEFAULT);
        btn_apply.setHorizontalAlignment(JLabel.CENTER);
        btn_apply.setVerticalAlignment  (JLabel.CENTER);
        btn_apply.setFont(this.getFont());
        btn_apply.setOpaque(true);
        
        btn_cancel.setBackground(BG_CANCEL_BTN);
        btn_cancel.setForeground(FG_DEFAULT);
        btn_cancel.setHorizontalAlignment(JLabel.CENTER);
        btn_cancel.setVerticalAlignment  (JLabel.CENTER);
        btn_cancel.setFont(this.getFont());
        btn_cancel.setOpaque(true);
        
        pnl_btn_apply .add(btn_applyRounded,  BorderLayout.CENTER);
        pnl_btn_cancel.add(btn_cancelRounded, BorderLayout.CENTER);
        
        JLabel[] lbl_guides = new JLabel[LBL_GUIDE_TXT.length];
        for (int i = 0; i < lbl_guides.length; i++) {
            lbl_guides[i] = new JLabel(LBL_GUIDE_TXT[i]);
            lbl_guides[i].setBackground(this.getBackground().darker());
            lbl_guides[i].setHorizontalAlignment(JLabel.CENTER);
            lbl_guides[i].setVerticalAlignment  (JLabel.CENTER);
            lbl_guides[i].setFont(new Font(this.getFont().getFontName(), this.getFont().getStyle(), 36));
            lbl_guides[i].setOpaque(true);
        }
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx     = 0;
        gbc.gridy     = 0;
        gbc.ipady     = 5;
        gbc.weightx   = 0.3;
        gbc.weighty   = 1;
        gbc.gridwidth = 1;
        gbc.fill    = GridBagConstraints.BOTH;
        gbc.insets  = new Insets(20, 10, 20, 10);
        pnl_filters.add(lbl_guides[0], gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.7;
        pnl_filters.add(pnl_ratingBar, gbc);
        
        gbc.gridy++;
        gbc.gridx--;
        gbc.weightx = 1;
        gbc.gridwidth++;
        gbc.ipady = 30;
        gbc.fill  = GridBagConstraints.HORIZONTAL;
        pnl_filters.add(lbl_guides[1], gbc);
        
        gbc.gridy++;
        pnl_filters.add(txt_locationRounded, gbc);
        
        gbc.gridy++;
        gbc.gridwidth--;
        pnl_filters.add(lbl_guides[2], gbc);
        
        gbc.gridx++;
        gbc.ipady = 5;
        pnl_filters.add(sld_price, gbc);
        
        gbc.gridx--;
        gbc.gridy++;
        gbc.gridwidth++;
        pnl_filters.add(lbl_guides[3], gbc);
        
        gbc.gridy++;
        pnl_filters.add(pnl_servicesRounded, gbc);
        
        pnl_btns.setPreferredSize(new Dimension(this.getWidth(), 80));
        pnl_btns.add(pnl_btn_apply,  BorderLayout.CENTER);
        pnl_btns.add(pnl_btn_cancel, BorderLayout.EAST);
        
        this.add(lbl_title, BorderLayout.NORTH);
        this.add(scrlPnl_filters, BorderLayout.CENTER);
        this.add(pnl_btns,  BorderLayout.SOUTH);
        
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                advancedSearch_ComponentResized(e);
            }
        });
        
        scrlPnl_filters.addMouseWheelListener((java.awt.event.MouseWheelEvent e) -> {
            scrlPnl_filters_MouseWheelMoved(e);
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the {@code AdvancedSearch} {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     * 
     * @param e the component event triggered by resizing the GUI application
     */
    private void advancedSearch_ComponentResized(java.awt.event.ComponentEvent e) {
        int[] padding = {(int) (this.getWidth() * 0.005), (int) (this.getHeight() * 0.0025)}; //0 = width; 1 = height;
        this.setBorder(BorderFactory.createEmptyBorder(padding[1], padding[0], padding[1], padding[0]));
        
        final int PADDING_BTN = (int) (this.getWidth() * 0.005);
        pnl_btn_apply .setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
        pnl_btn_cancel.setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
        
    }
    
    /**
     * Handles the {@link JScrollPane} {@link JScrollBar}.
     * 
     * @param e the component event triggered by wheel-scrolling using the mouse.
     */
    private void scrlPnl_filters_MouseWheelMoved(java.awt.event.MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        int fasterScroll = notches * 40;

        JScrollBar vertical = scrlPnl_filters.getVerticalScrollBar();
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

        setBackground(new java.awt.Color(153, 255, 102));
        setMinimumSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Consts">
    private final int ARC_PANEL            = 50;
    private final int ARC_TEXTFIELD        = 30;
    private final int ARC_BUTTON           = 20;
    private final String TITLE             = "Filters";
    private final String APPLY_FILTERS     = "Apply filters";
    private final String CANCEL            = "Cancel";
    private final int MIN_PRICE            = 5;
    private final int MAX_PRICE            = 2000;
    private final int RATINGS              = 5;
    private final String[] CHKBX_TXT       = {
        "Air conditioning", 
        "Booking essential", 
        "Booking essential - dinner", 
        "Brunch",
        "Bring your own bottle", 
        "Car park", 
        "Cash only", 
        "Cash only - lunch",
        "Counter dining", 
        "Credit cards not accepted", 
        "Foreign credit cards not accepted", 
        "Garden or park",
        "Great view", 
        "Interesting wine list", 
        "Notable sake list", 
        "Restaurant offering vegetarian menus",
        "Shoes must be removed", 
        "Terrace", 
        "parking", 
        "Wheelchair access"
    };
    private final String[] LBL_GUIDE_TXT = {
        "Rating",
        "Location",
        "Price",
        "Services"
    };
    private final Color FG_DEFAULT              = Color.BLACK;
    private final Color FG_PLACEHOLDER          = Color.GRAY;
    private final Color BG_TEXTFIELD            = new Color(255, 255, 255, 192);
    private final Color BG_APPLY_BTN            = new Color(0, 255, 0, 192);
    private final Color BG_CANCEL_BTN           = new Color(255, 64, 0, 192);
    private final Border PADDING_TEXTFIELD      = BorderFactory.createEmptyBorder(0, 10, 0, 10);
    private final Border PADDING_PANEL_SERVICES = BorderFactory.createEmptyBorder(20, 20, 20, 20);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private final PanelMain   pnl_main;
    private       JPanel      pnl_filters;
    private       JPanel      pnl_btn_apply;
    private       JPanel      pnl_btn_cancel;
    private       JPanel      pnl_ratingBar;
    private       JPanel      pnl_btns;
    private       JPanel      pnl_services;
    private       JScrollPane scrlPnl_filters;
    private       JLabel      lbl_title;
    private       JLabel[]    lbl_star;
    private       JLabel      btn_apply;
    private       JLabel      btn_cancel;
    private       JTextField  txt_location;
    private       JSlider     sld_price;
    private       JCheckBox[] chkbx_services; 
    private       RoundedComponentUI txt_layerUI;
    private       RoundedComponentUI btn_layerUI;
    private       RoundedComponentUI pnl_layerUI;
    private       JLayer<JComponent> txt_locationRounded;
    private       JLayer<JComponent> btn_applyRounded;
    private       JLayer<JComponent> btn_cancelRounded;
    private       JLayer<JComponent> pnl_servicesRounded;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
