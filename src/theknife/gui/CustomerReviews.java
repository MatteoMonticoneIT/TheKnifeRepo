package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import theknife.Controller;
import theknife.obj.lists.ListReview;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.review.Review;

/**
 * {@code CustomerReviews} is a panel used to display all reviews made by a {@link Customer}.
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class CustomerReviews extends javax.swing.JPanel {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code CustomerReviews} panel and initializes its components.
     * <p>
     * This constructor also sets the main panel to control the screen transitions using {@link PanelMain#showCard}.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     */
    public CustomerReviews(Controller controller) {
        initComponents();
        this.controller = controller;
        getCustomerReviews();
        initGUI();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code CustomerReviews} page.
     */
    private void initGUI() {
        initFields();
        initCustomerReviews();
        initEvents();
        SwingUtilities.invokeLater(() -> {
            scrlPnl_reviews.getVerticalScrollBar().setValue(0);
        });
    }

    /**
     * Initializes the basic fields of the {@code Home} panel.
     */
    private void initFields() {
        pnl_btn_edit           = new JPanel(new BorderLayout());
        pnl_btn_remove         = new JPanel(new BorderLayout());
        pnl_btn_back           = new JPanel(new BorderLayout());
        pnl_btns               = new JPanel(new GridLayout(1, 3, 10, 10));
        pnl_reviews            = new JPanel(new GridLayout(reviews.size() == 0 ? 1 : reviews.size(), 1));
        scrlPnl_reviews        = new JScrollPane(pnl_reviews, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        layout_reviews         = (GridLayout) pnl_reviews.getLayout();
        lbl_title              = new JLabel(TITLE);
        btn_edit               = new JLabel(EDIT);
        btn_remove             = new JLabel(REMOVE);
        btn_back               = new JLabel(BACK);
        scrlPnl_reviewsRounded = new JLayer<>(scrlPnl_reviews, PNL_LAYER_UI);
        btn_editRounded        = new JLayer<>(btn_edit,        BTN_LAYER_UI);
        btn_removeRounded      = new JLayer<>(btn_remove,      BTN_LAYER_UI);
        btn_backRounded        = new JLayer<>(btn_back,        BTN_LAYER_UI);
    }
    
    /**
     * Initializes the layout and appearance of the {@code CustomerReviews} page.
     */
    private void initCustomerReviews() {
        this.setLayout(new BorderLayout());
        
        pnl_reviews.setBackground(BG_CUSTOMER_REVIEWS);
        
        pnl_btns    .setBackground(this.getBackground());

        pnl_btn_edit  .setBackground(pnl_btns.getBackground());
        pnl_btn_remove.setBackground(pnl_btns.getBackground());
        pnl_btn_back  .setBackground(pnl_btns.getBackground());
        
        lbl_title.setBackground           (this.getBackground());
        lbl_title.setForeground           (FG_DEFAULT);
        lbl_title.setHorizontalAlignment  (JLabel.CENTER);
        lbl_title.setVerticalAlignment    (JLabel.CENTER);
        lbl_title.setFont                 (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 48));
        lbl_title.setOpaque               (true);
        
        scrlPnl_reviews .getVerticalScrollBar()      .setUI(new CustomJScrollBar());
        scrlPnl_reviews .getHorizontalScrollBar()    .setUI(new CustomJScrollBar());
        scrlPnl_reviews .setBorder                   (BorderFactory.createEmptyBorder());
        
        btn_edit.setBackground           (BG_EDIT_BTN);
        btn_edit.setForeground           (FG_DEFAULT);
        btn_edit.setHorizontalAlignment  (JLabel.CENTER);
        btn_edit.setVerticalAlignment    (JLabel.CENTER);
        btn_edit.setFont                 (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
        btn_edit.setOpaque               (true);

        btn_remove.setBackground          (BG_BACK_BTN);
        btn_remove.setForeground          (FG_DEFAULT);
        btn_remove.setHorizontalAlignment (JLabel.CENTER);
        btn_remove.setVerticalAlignment   (JLabel.CENTER);
        btn_remove.setFont                (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
        btn_remove.setOpaque              (true);

        btn_back.setBackground          (BG_BACK_BTN);
        btn_back.setForeground          (FG_DEFAULT);
        btn_back.setHorizontalAlignment (JLabel.CENTER);
        btn_back.setVerticalAlignment   (JLabel.CENTER);
        btn_back.setFont                (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
        btn_back.setOpaque              (true);

        pnl_btn_edit  .add(btn_editRounded,   BorderLayout.CENTER);
        pnl_btn_remove.add(btn_removeRounded, BorderLayout.CENTER);
        pnl_btn_back  .add(btn_backRounded,   BorderLayout.CENTER);
      
        pnl_btns.setPreferredSize (new Dimension(this.getWidth(), PNL_BTNS_HEIGHT));
        pnl_btns.add              (pnl_btn_edit);
        pnl_btns.add              (pnl_btn_remove);
        pnl_btns.add              (pnl_btn_back);
        
        updatePanelReviews();
        
        this.add(lbl_title, BorderLayout.NORTH);
        this.add(scrlPnl_reviewsRounded, BorderLayout.CENTER);
        this.add(pnl_btns,  BorderLayout.SOUTH);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                customerReviews_ComponentResized(e);
            }
        });
        
        scrlPnl_reviews.addMouseWheelListener((java.awt.event.MouseWheelEvent e) -> {
            scrlPnl_reviews_MouseWheelMoved(e);
        });
        
        btn_back.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_back_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_back_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_back_MouseExited(e);
            }
        });
        
        btn_remove.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_remove_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_remove_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_remove_MouseExited(e);
            }
        });
        
        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_edit_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_edit_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_edit_MouseExited(e);
            }
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
    private void customerReviews_ComponentResized(java.awt.event.ComponentEvent e) {
        int[] padding = {(int) (this.getWidth() * 0.005), (int) (this.getHeight() * 0.0025)};
        this.setBorder(BorderFactory.createEmptyBorder(padding[1], padding[0], padding[1], padding[0]));
        
        final int PADDING_BTN = (int) (this.getWidth() * 0.005);
        pnl_btn_edit  .setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
        pnl_btn_remove.setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
        pnl_btn_back  .setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
    }
    
    /**
     * Handles the {@link JScrollPane} {@link JScrollBar}.
     * 
     * @param e the component event triggered by wheel-scrolling using the mouse.
     */
    private void scrlPnl_reviews_MouseWheelMoved(java.awt.event.MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        int fasterScroll = notches * 40;

        JScrollBar vertical = scrlPnl_reviews.getVerticalScrollBar();
        vertical.setValue(vertical.getValue() + fasterScroll);
    }
    
    /**
     * Handles the click event on the cancel button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code Home} screen canceling the login procedure.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_back_MouseClicked(java.awt.event.MouseEvent e) {
        controller.getPanelMain().showCard(Page.HOME);
        controller.getPanelMain().getPanel().remove(this);
    }
    
    /**
     * Handles the hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_back_MouseEntered(java.awt.event.MouseEvent e) {
        btn_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_back.setBackground(btn_back.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_back_MouseExited(java.awt.event.MouseEvent e) {
        btn_back.setBackground(BG_BACK_BTN);
    }
    
    /**
     * Handles the click event on the remove button {@link JLabel}.
     * <p>
     * When the button is clicked, the review selected will be removed from both customer review list and restaurant review list.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_remove_MouseClicked(java.awt.event.MouseEvent e) {
        if (controller.getSelectedReview() == null) 
            JOptionPane.showMessageDialog(null, "You must select a review to edit!", "No review is selected!", JOptionPane.WARNING_MESSAGE);
        else {
            int restaurantIDIndex = controller.getSelectedReview().getRestaurantID() - 1;
            int reviewIDIndex     = controller.getSelectedReview().getID() - 1;
            Review review         = controller.getRestaurants   ().getList().get(restaurantIDIndex).getListReview().getList().get(reviewIDIndex);
            controller.getRestaurants().getList().get(restaurantIDIndex).getListReview().remove(review);
            reviews.remove(review);
            updatePanelReviews();
        }
    }
    
    /**
     * Handles the hover event on the remove button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_remove_MouseEntered(java.awt.event.MouseEvent e) {
        btn_remove.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_remove.setBackground(btn_remove.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the remove button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_remove_MouseExited(java.awt.event.MouseEvent e) {
        btn_remove.setBackground(BG_BACK_BTN);
    }
    
    /**
     * Handles the click event on the apply button {@link JLabel}.
     * <p>
     * When the button is clicked, the filters will be applied and the view switches to the {@code Home} screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_edit_MouseClicked(java.awt.event.MouseEvent e) 
    {
        if (controller.getSelectedReview() == null) 
            JOptionPane.showMessageDialog(null, "You must select a review to edit!", "No review is selected!", JOptionPane.WARNING_MESSAGE);
        else {
            
        }
    }
    
    /**
     * Handles the hover event on the apply button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_edit_MouseEntered(java.awt.event.MouseEvent e) {
        btn_edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_edit.setBackground(btn_edit.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the apply button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_edit_MouseExited(java.awt.event.MouseEvent e) {
        btn_edit.setBackground(BG_EDIT_BTN);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Method">
    /**
     * Gets all the {@link Review} created by the {@link Customer}.
     */
    private void getCustomerReviews() {
        reviews = new ListReview();
        for (Restaurant restaurant : controller.getRestaurants().getList()) {
            for (Review review : restaurant.getListReview().getList()) {
                if (review.getUsername().equals(controller.getLoggedUser().getUsername()))
                    reviews.add(review);
            }
        }
    } 
    
    /**
     * Updates the {@link ListReview} {@code JPanel} of the customer.
     */
    private void updatePanelReviews() {
        pnl_reviews.removeAll();
        getCustomerReviews();
        layout_reviews.setRows(reviews.size() == 0 ? 1 : reviews.size());
        int i = 0;
        for (Review review : reviews.getList()) {
            i++;
            CustomerReview reviewGUI = new CustomerReview(controller, review, i % 2 == 0 ? BG_CUSTOMER_REVIEW_ODD : BG_CUSTOMER_REVIEW_EVEN);
            pnl_reviews.add(reviewGUI);
        }
        pnl_reviews.revalidate();
        pnl_reviews.repaint();
    }
    //</editor-fold>
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(111, 168, 119));
        setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N

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
    private final Color              FG_DEFAULT              = Color.BLACK;
    private final Color              BG_CUSTOMER_REVIEWS     = new Color(87, 158, 97);
    private final Color              BG_EDIT_BTN             = new Color(0, 255, 0, 192);
    private final Color              BG_BACK_BTN             = new Color(255, 64, 0, 192);
    private final Color              BG_CUSTOMER_REVIEW_ODD  = new Color(86, 143, 94);
    private final Color              BG_CUSTOMER_REVIEW_EVEN = new Color(89, 158, 98);
    private final String             TITLE                   = "Your Reviews";
    private final String             EDIT                    = "Edit";
    private final String             REMOVE                  = "Remove";
    private final String             BACK                    = "Back";
    private final int                ARC_BUTTON              = 50;
    private final int                ARC_PANEL               = 30;
    private final int                PNL_BTNS_HEIGHT         = 80;
    private final RoundedComponentUI BTN_LAYER_UI            = new RoundedComponentUI(ARC_BUTTON);
    private final RoundedComponentUI PNL_LAYER_UI            = new RoundedComponentUI(ARC_PANEL);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private JPanel             pnl_btn_edit;
    private JPanel             pnl_btn_remove;
    private JPanel             pnl_btn_back;
    private JPanel             pnl_btns;
    private JPanel             pnl_reviews;
    private JScrollPane        scrlPnl_reviews;
    private GridLayout         layout_reviews;
    private JLabel             lbl_title;
    private JLabel             btn_edit;
    private JLabel             btn_remove;
    private JLabel             btn_back;
    private JLayer<JComponent> scrlPnl_reviewsRounded;
    private JLayer<JComponent> btn_editRounded;
    private JLayer<JComponent> btn_removeRounded;
    private JLayer<JComponent> btn_backRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller controller;
    private       ListReview reviews;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
