package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import theknife.Controller;
import theknife.obj.review.Review;

/**
 * The {@code PreviewReview} class represents a graphical panel component for displaying a single {@link Review} object.
 * <p>
 * This class is part of the GUI layer of the {@link RestaurantGUI} and is typically used to have a graphical interface of the review itself.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class PreviewReview extends javax.swing.JPanel {
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code PreviewReview} panel and initializes its components.
     * <p>
     * This constructor also sets the review to insert the data needed to have a preview of the review itself.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     * @param review the {@link Review} class that represents the review
     * @param bg the {@code Color} of the background for the {@code PreviewReview}
     */
    public PreviewReview(Controller controller, Review review, Color bg) {
        initComponents();
        this.controller = controller;
        this.review     = review;
        this.bg         = bg;
        initGUI();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code PreviewReview} page.
     */
    private void initGUI() {
        initFields();
        initPreviewReview();
        initEvents();
    }
    
    /**
     * Initializes the basic fields of the {@code PreviewReview} panel.
     */
    private void initFields() 
    {
        numResponses       = review.getResponses() == null ? 0 : review.getResponses().getList().isEmpty() ? 0 : review.getResponses().getList().size();
        pnl_usernameRating = new JPanel(new BorderLayout());
        lbl_username       = new JLabel(review.getUsername());
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
     * Initializes the layout and appearance of the home page.
     */
    private void initPreviewReview() 
    {
        this.setBackground(bg);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(0, PREVIEWREVIEW_HEIGHT));
        
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
    private void initEvents() 
    {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                previewReviewGUI_MouseClicked(e);
            }
        });
        
        pnl_usernameRating.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                previewReviewGUI_MouseClicked(e);
            }
        });
        
        txt_content.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                previewReviewGUI_MouseClicked(e);
            }
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">ù
    /**
     * Handles the click event on the {@code PreviewReview} {@link JPanel}.
     * <p>
     * When the button is clicked, the {@link Review} given to this object will be passed to the {@link RestaurantGUI} page.
     * It is necessary to pass in order to handle the GUI page giving the restaurant's fields to the components.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button
     */
    private void previewReviewGUI_MouseClicked(java.awt.event.MouseEvent e) {
        reviewGUI = new ReviewGUI(controller, review, bg);
        controller.getPanelMain().getPanel().add(reviewGUI, Page.REVIEW);
        controller.getPanelMain().showCard(Page.REVIEW);
        
    }
    //</editor-fold>
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N

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
    private final Color              FG_DEFAULT           = Color.BLACK;
    private final Border             PADDING_LBL          = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private final Border             PADDING_TXT          = BorderFactory.createEmptyBorder(3, 3, 3, 3);
    private final Border             BORDER_NORTH_PNL     = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK);
    private final Color              STAR_BG_DEFAULT      = new Color(255, 215, 0);
    private final String             RESPONSES            = " users have responded to this review";
    private final char               FULL_STAR            = 'C';
    private final int                NORTH_CONTENT_HEIGHT = 30;
    private final int                MAX_CONTENT_LENGTH   = 140;
    private final int                PREVIEWREVIEW_HEIGHT = 100;
    private final int                RATING_HEIGHT        = 70;
    private final int                RESPONSES_HEIGHT     = 20;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private ReviewGUI   reviewGUI;
    private JPanel      pnl_usernameRating;
    private JLabel      lbl_username;
    private JLabel      lbl_responses;
    private CustomJLabel lbl_rating;
    private JTextArea   txt_content;
    private JLabel[]    lbls;
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
