package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import theknife.Controller;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.review.Review;

/**
 * The {@code ReviewGUI} class represents a graphical panel component for displaying the {@link Review} object.
 * <p>
 * This class is part of the GUI layer of the {@link PreviewRestaurantGUI} and is typically used to have a graphical interface of the review itself.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class ReviewGUI extends javax.swing.JPanel {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code ReviewGUI} panel and initializes its components.
     * <p>
     * This constructor also sets the review to insert the data needed to have a graphical interface of the review itself.
     * </p>
     *
     * @param restaurant
     * @param controller the {@link Controller} class that manages the screen layout
     * @param review the {@link Review} class that represents the review
     * @param bg the {@code Color} of the background for the {@code ReviewGUI}
     */
    public ReviewGUI(Restaurant restaurant, Controller controller, Review review, Color bg) {
        initComponents();
        this.restaurant = restaurant;
        this.controller = controller;
        this.review     = review;
        this.bg         = bg;
        initGUI();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code PreviewReviewGUI} page.
     */
    private void initGUI() 
    {
      initFields            ();
      initPreviewReviewGUI  ();
      initEvents            ();
    }
    
    /**
     * Initializes the basic fields of the {@code PreviewReviewGUI} panel.
     */
    private void initFields() 
    {
        numResponses             = review.getResponses() == null ? 1 : review.getResponses().size();
        pnl_usernameRatingReview = new JPanel      (new BorderLayout());
        pnl_responses            = new JPanel      (new GridLayout(numResponses, 1));
        pnl_btns                 = new JPanel      (new GridLayout(1, 2, 10, 10));
        pnl_btn_addResponse      = new JPanel      (new BorderLayout());
        pnl_btn_back             = new JPanel      (new BorderLayout());
        lbl_usernameReview       = new JLabel      (review.getUsername());
        lbl_rating               = new CustomJLabel(String.valueOf(review.getRating()), FULL_STAR);
        txt_reviewContent        = new JTextArea   (review.getContent());
        lbls                     = new JLabel[] {
            lbl_usernameReview,
            lbl_rating
        };
        scrlPnl_reviewContent    = new JScrollPane (txt_reviewContent, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrlPnl_responses        = new JScrollPane (pnl_responses, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        btn_addResponse          = new JLabel      (ADD_RESPONSE);
        btn_back                 = new JLabel      (BACK);
        btn_addResponseRounded   = new JLayer<>    (btn_addResponse, BTN_LAYERUI);
        btn_backRounded          = new JLayer<>    (btn_back,        BTN_LAYERUI);
    }
    
    /**
     * Initializes the layout and appearance of the home page.
     */
    private void initPreviewReviewGUI() 
    {
        this.setBackground(bg);
        this.setLayout(new BorderLayout());
        
        pnl_usernameRatingReview.setBackground(this.getBackground());
        pnl_usernameRatingReview.setPreferredSize(new Dimension(0, NORTH_CONTENT_HEIGHT));
        
        pnl_responses.setBackground(this.getBackground());
        
        pnl_btns.setBackground(BG_PNL_BTNS);
        pnl_btns.setBorder    (BorderFactory.createEmptyBorder());
        
        pnl_btn_addResponse.setBackground(pnl_btns.getBackground());
        pnl_btn_back       .setBackground(pnl_btns.getBackground());
        
        for (JLabel lbl : lbls) {
            lbl.setBackground(this.getBackground());
            lbl.setForeground(FG_DEFAULT);
            lbl.setHorizontalAlignment  (JLabel.LEFT);
            lbl.setVerticalAlignment    (JLabel.CENTER);
            lbl.setFont                 (this.getFont());
            lbl.setBorder               (PADDING_LBL);
            lbl.setOpaque               (true);
        }
        lbl_rating.setPreferredSize(new Dimension(LBL_RATING_WIDTH, 0));
        lbl_rating.setCharacterColor(BG_STAR);
        lbl_rating.setCharacterSpacing(15);
        lbl_rating.setCustomFontSize(50f);
        
        scrlPnl_reviewContent.setBackground                 (this.getBackground());
        scrlPnl_reviewContent.setBorder                     (BORDER_PNL);
        scrlPnl_reviewContent.getVerticalScrollBar()  .setUI(new CustomJScrollBar());
        scrlPnl_reviewContent.getHorizontalScrollBar().setUI(new CustomJScrollBar());
        
        scrlPnl_responses.setBackground                 (this.getBackground());
        scrlPnl_responses.setBorder                     (BORDER_PNL);
        scrlPnl_responses.getVerticalScrollBar()  .setUI(new CustomJScrollBar());
        scrlPnl_responses.getHorizontalScrollBar().setUI(new CustomJScrollBar());
        
        txt_reviewContent.setBackground   (this.getBackground());
        txt_reviewContent.setBorder       (PADDING_TXT);
        txt_reviewContent.setLineWrap     (true);
        txt_reviewContent.setWrapStyleWord(true);
        txt_reviewContent.setEditable     (false);
        txt_reviewContent.setFocusable    (false);
        
        btn_addResponse.setBackground         (BG_ADDRESPONSE_BTN);
        btn_addResponse.setForeground         (FG_DEFAULT);
        btn_addResponse.setHorizontalAlignment(JLabel.CENTER);
        btn_addResponse.setVerticalAlignment  (JLabel.CENTER);
        btn_addResponse.setFont               (this.getFont());
        btn_addResponse.setOpaque             (true);
        
        btn_back.setBackground                (BG_BACK_BTN);
        btn_back.setForeground                (FG_DEFAULT);
        btn_back.setHorizontalAlignment       (JLabel.CENTER);
        btn_back.setVerticalAlignment         (JLabel.CENTER);
        btn_back.setFont                      (this.getFont());
        btn_back.setOpaque                    (true);
        
        pnl_usernameRatingReview.add(lbl_usernameReview, BorderLayout.CENTER);
        pnl_usernameRatingReview.add(lbl_rating,         BorderLayout.EAST);
        
        pnl_btn_addResponse.add(btn_addResponseRounded, BorderLayout.CENTER);
        pnl_btn_back       .add(btn_backRounded,        BorderLayout.CENTER);
        
        pnl_btns.setPreferredSize(new Dimension(this.getWidth(), PNL_BTNS_HEIGHT));
        pnl_btns.add(pnl_btn_addResponse, BorderLayout.CENTER);
        pnl_btns.add(pnl_btn_back,        BorderLayout.EAST);
        
        this.add(pnl_usernameRatingReview, BorderLayout.NORTH);
        this.add(scrlPnl_reviewContent,    BorderLayout.CENTER);
        this.add(scrlPnl_responses,        BorderLayout.EAST);
        this.add(pnl_btns,                 BorderLayout.SOUTH);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() 
    {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                reviewGUI_ComponentResized(e);
            }
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
        
        btn_addResponse.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_addResponse_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_addResponse_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_addResponse_MouseExited(e);
            }
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the {@code RestaurantGUI} {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     * 
     * @param e the component event triggered by resizing the GUI application
     */
    private void reviewGUI_ComponentResized(java.awt.event.ComponentEvent e) 
    {
        final int PADDING_BTN = (int) (this.getWidth() * 0.01);
        pnl_btn_addResponse.setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
        pnl_btn_back       .setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
        
        pnl_responses.setPreferredSize(new Dimension((int) (this.getWidth() * 0.4), 0));
    }
    
    /**
     * Handles the click event on the back button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code Home} screen canceling the login procedure.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_back_MouseClicked(java.awt.event.MouseEvent e) {
        controller.getPanelMain().showCard(Page.RESTAURANT);
        controller.getPanelMain().getPanel().remove(this);
    }
    
    /**
     * Handles the hover event on the back button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_back_MouseEntered(java.awt.event.MouseEvent e) {
        btn_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_back.setBackground(btn_back.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the back button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_back_MouseExited(java.awt.event.MouseEvent e) {
        btn_back.setBackground(BG_BACK_BTN);
    }
    
    /**
     * Handles the click event on the add response button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@link AddResponse} screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_addResponse_MouseClicked(java.awt.event.MouseEvent e) 
    {
      if(controller.getLoggedUser() != null)
      {
        if(controller.getLoggedUser().getRole().equals("restaurateur") && controller.getLoggedUser().getId() == restaurant.getOwnerId())
        {
          addResponse = new AddResponse(controller, review);
          controller.getPanelMain().getPanel().add(addResponse, Page.ADD_RESPONSE);
          controller.getPanelMain().showCard(Page.ADD_RESPONSE);
        }
      }
      else
      {
        controller.getPanelMain().showCard(Page.LOGIN_RESTAURATEUR);
        controller.getPanelMain().getPanel().remove(this);
      }
    }
    
    /**
     * Handles the hover event on the add response button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_addResponse_MouseEntered(java.awt.event.MouseEvent e) {
        btn_addResponse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_addResponse.setBackground(btn_addResponse.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the add response button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_addResponse_MouseExited(java.awt.event.MouseEvent e) {
        btn_addResponse.setBackground(BG_ADDRESPONSE_BTN);
    }
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFont(new java.awt.Font("Consolas", 0, 28)); // NOI18N

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
    private final Color              FG_DEFAULT                = Color.BLACK;
    private final Color              BG_ADDRESPONSE_BTN        = new Color(0, 255, 0, 192);
    private final Color              BG_BACK_BTN               = new Color(255, 64, 0, 192);
    private final Color              BG_STAR                   = new Color(255, 215, 0);
    private final Color              BG_PNL_BTNS               = new Color(94, 168, 69);
    private final Border             PADDING_LBL               = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private final Border             PADDING_TXT               = BorderFactory.createEmptyBorder(3, 3, 3, 3);
    private final Border             BORDER_PNL                = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
    private final String             ADD_RESPONSE              = "Add response";
    private final String             BACK                      = "Back";
    private final char               FULL_STAR                 = 'C';
    private final int                NORTH_CONTENT_HEIGHT      = 80;
    private final int                ARC                       = 50;
    private final int                PNL_BTNS_HEIGHT           = 80;
    private final int                LBL_RATING_WIDTH          = 125;
    private final RoundedComponentUI BTN_LAYERUI               = new RoundedComponentUI(ARC);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private JPanel             pnl_usernameRatingReview;
    private JPanel             pnl_responses;
    private JPanel             pnl_btns;
    private JPanel             pnl_btn_addResponse;
    private JPanel             pnl_btn_back;
    private JScrollPane        scrlPnl_reviewContent;
    private JScrollPane        scrlPnl_responses;
    private JLabel             lbl_usernameReview;
    private CustomJLabel       lbl_rating;
    private JTextArea          txt_reviewContent;
    private JLabel[]           lbls;
    private JLabel             btn_addResponse;
    private JLabel             btn_back;
    private JLayer<JComponent> btn_addResponseRounded;
    private JLayer<JComponent> btn_backRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller  controller;
    private       AddResponse addResponse;
    private final Color       bg;
    private final Review      review;
    private       int         numResponses;
    private final Restaurant  restaurant;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
