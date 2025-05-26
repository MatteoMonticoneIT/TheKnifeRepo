package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import theknife.Controller;
import theknife.obj.review.Review;

/**
 * {@code CustomerReview} is a panel used to display a review made by a {@link Customer}.
 * <p>
 * This panel is present in {@link CustomerReviews}, it is used to display the {@link Review} itself.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class CustomerReview extends javax.swing.JPanel {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code CustomerReview} panel and initializes its components.
     * <p>
     * This constructor also sets the {@link Review} used to display.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     * @param review the {@link Controller} class that manages the screen layout
     * @param bg the {@code Color} background
     */
    public CustomerReview(Controller controller, Review review, Color bg) {
        initComponents();
        this.controller = controller;
        this.review     = review;
        this.bg         = bg;
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
    }

    /**
     * Initializes the basic fields of the {@code Home} panel.
     */
    private void initFields() {
        numResponses       = review.getResponses() == null ? 0 : review.getResponses().getList().isEmpty() ? 0 : review.getResponses().getList().size();
        pnl_usernameRating = new JPanel(new BorderLayout());
        lbl_username       = new JLabel(review.getUsername() + " - " + controller.getRestaurants().getList().get(review.getRestaurantID() - 1).getName());
        lbl_rating         = new CustomJLabel(String.valueOf(review.getRating()), FULL_STAR);
        lbl_responses      = new JLabel((numResponses == 0 ? "No" : numResponses) + RESPONSES);
        txt_content        = new JTextArea((review.getContent().length() > MAX_CONTENT_LENGTH ? review.getContent().substring(0, MAX_CONTENT_LENGTH) + "..." : review.getContent()).trim());
        lbls               = new JLabel[] {
            lbl_username,
            lbl_rating,
            lbl_responses
        };
    }
    
    /**
     * Initializes the layout and appearance of the {@code CustomerReviews} page.
     */
    private void initCustomerReviews() {
        this.setBackground(bg);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(0, CUSTOMER_REVIEW_HEIGHT));
        
        pnl_usernameRating.setBackground(bg);
        pnl_usernameRating.setBorder(BORDER_NORTH_PNL);
        pnl_usernameRating.setPreferredSize(new Dimension(0, NORTH_CONTENT_HEIGHT));
        
        for (JLabel lbl : lbls) {
            lbl.setBackground(bg);
            lbl.setForeground(FG_DEFAULT);
            lbl.setHorizontalAlignment  (JLabel.LEFT);
            lbl.setVerticalAlignment    (JLabel.CENTER);
            lbl.setFont                 (this.getFont());
            lbl.setBorder               (PADDING_LBL);
            lbl.setOpaque               (true);
        }
        lbl_rating.setPreferredSize(new Dimension(RATING_HEIGHT, 0));
        lbl_rating.setCharacterColor(STAR_BG_DEFAULT);
        lbl_rating.setCharacterSpacing(10);
        
        lbl_responses.setPreferredSize(new Dimension(0, RESPONSES_HEIGHT));
        lbl_responses.setFont(new Font(this.getFont().getFontName(), this.getFont().getStyle(), 12));
        lbl_responses.setHorizontalAlignment(JLabel.RIGHT);
        
        txt_content.setBackground   (bg);
        txt_content.setBorder       (PADDING_TXT);
        txt_content.setLineWrap     (true);
        txt_content.setWrapStyleWord(true);
        txt_content.setEditable     (false);
        txt_content.setFocusable    (false);
        
        pnl_usernameRating.add(lbl_username, BorderLayout.CENTER);
        pnl_usernameRating.add(lbl_rating,   BorderLayout.EAST);
        
        this.add(pnl_usernameRating, BorderLayout.NORTH);
        this.add(txt_content,        BorderLayout.CENTER);
        this.add(lbl_responses,      BorderLayout.SOUTH);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                customerReviewGUI_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                customerReviewGUI_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                customerReviewGUI_MouseExited(e);
            }
        });
        
        pnl_usernameRating.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                customerReviewGUI_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                customerReviewGUI_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                customerReviewGUI_MouseExited(e);
            }
        });
        
        txt_content.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                customerReviewGUI_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                customerReviewGUI_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                customerReviewGUI_MouseExited(e);
            }
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the click event on the {@code CustomerReview} {@link JPanel}.
     * <p>
     * When the button is clicked, the {@link Review} given to this object will be passed to the {@link RestaurantGUI} page.
     * It is necessary to pass in order to handle the GUI page giving the restaurant's fields to the components.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button
     */
    private void customerReviewGUI_MouseClicked(java.awt.event.MouseEvent e) {
        controller.setSelectedReview(review);
        JOptionPane.showMessageDialog(
                null, 
                "The review: " + controller.getSelectedReview().getUsername() + 
                " - " + 
                controller.getRestaurants().getList().get(controller.getSelectedReview().getRestaurantID()).getName() +
                " is now selected!",
                "Review selected!",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Handles the hover event on the {@code CustomerReview} {@link JPanel}.
     * 
     * @param e the mouse event triggered by clicking the button
     */
    private void customerReviewGUI_MouseEntered(java.awt.event.MouseEvent e) {
        this.setBackground(BG_HOVER_REVIEW);
        for (JLabel lbl : lbls)
            lbl.setBackground(BG_HOVER_REVIEW);
        txt_content.setBackground(BG_HOVER_REVIEW);
    }
    
    /**
     * Handles the exit hover event on the {@code CustomerReview} {@link JPanel}.
     * 
     * @param e the mouse event triggered by clicking the button
     */
    private void customerReviewGUI_MouseExited(java.awt.event.MouseEvent e) {
        this.setBackground(bg);
        for (JLabel lbl : lbls)
            lbl.setBackground(bg);
        txt_content.setBackground(bg);
    }
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
    private final Color              FG_DEFAULT             = Color.BLACK;
    private final Color              BG_HOVER_REVIEW     = new Color(118, 158, 89);
    private final Color              STAR_BG_DEFAULT        = new Color(255, 215, 0);
    private final Border             PADDING_LBL            = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private final Border             PADDING_TXT            = BorderFactory.createEmptyBorder(3, 3, 3, 3);
    private final Border             BORDER_NORTH_PNL       = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK);
    private final String             RESPONSES              = " responses to this review";
    private final char               FULL_STAR              = 'C';
    private final int                NORTH_CONTENT_HEIGHT   = 30;
    private final int                MAX_CONTENT_LENGTH     = 140;
    private final int                CUSTOMER_REVIEW_HEIGHT = 100;
    private final int                RATING_HEIGHT          = 70;
    private final int                RESPONSES_HEIGHT       = 20;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private JPanel       pnl_usernameRating;
    private JLabel       lbl_username;
    private JLabel       lbl_responses;
    private CustomJLabel lbl_rating;
    private JTextArea    txt_content;
    private JLabel[]     lbls;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller controller;
    private final Color      bg;
    private final Review     review;
    private       int        numResponses;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
