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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import theknife.Controller;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.review.Review;

/**
 * {@code EditReview} is a panel used to edit a review made by a {@link Customer}.
 * <p>
 * This panel is present in {@link CustomerReviews}, it is used to display the {@link Review} itself and able to change some values.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class EditReview extends javax.swing.JPanel {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code EditReview} panel and initializes its components.
     * <p>
     * This constructor also sets the review to insert the data needed to have a graphical interface of the review itself.
     * </p>
     *
     * @param restaurant the {@link Restaurant} class that represents the restaurant
     * @param controller the {@link Controller} class that manages the screen layout
     * @param review the {@link Review} class that represents the review
     */
    public EditReview(Controller controller, Restaurant restaurant, Review review) {
        initComponents();
        this.controller = controller;
        this.restaurant = restaurant;
        this.review     = review;
        initGUI();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code EditReview} page.
     */
    private void initGUI() 
    {
      initFields    ();
      initEditReview();
      initEvents    ();
    }
    
    /**
     * Initializes the basic fields of the {@code EditReview} panel.
     */
    private void initFields() 
    {
        pnl_btns            = new JPanel(new GridLayout(1, 2, 10, 10));
        pnl_btn_edit        = new JPanel(new BorderLayout());
        pnl_btn_cancel      = new JPanel(new BorderLayout());
        pnl_ratingBar       = new JPanel(new GridLayout(1, RATINGS));
        pnl_content         = new JPanel(new BorderLayout());
        pnl_nameRating      = new JPanel(new GridLayout(1, 2));
        lbl_title           = new JLabel(TITLE);
        lbl_stars           = new CustomJLabel[RATINGS];
        lbl_restaurantName  = new JLabel(restaurant.getName());
        btn_edit            = new JLabel(EDIT_REVIEW);
        btn_cancel          = new JLabel(CANCEL);
        txt_content         = new JTextArea(review.getContent());
        btn_editRounded     = new JLayer<>(btn_edit,   BTN_LAYERUI);
        btn_cancelRounded   = new JLayer<>(btn_cancel, BTN_LAYERUI);
        scrlPnl_content     = new JScrollPane(txt_content, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    /**
     * Initializes the layout and appearance of the home page.
     */
    private void initEditReview() 
    {
        this.setLayout(new BorderLayout());
        
        pnl_btns.setBackground(BG_PNL_BTNS);
        pnl_btns.setBorder    (BorderFactory.createEmptyBorder());
        
        pnl_nameRating.setPreferredSize(new Dimension(0, PNL_NAMERATING_HEIGHT));
        
        pnl_ratingBar .setBackground(BG_STAR_LBL);
        
        pnl_btn_edit   .setBackground(pnl_btns.getBackground());
        pnl_btn_cancel.setBackground(pnl_btns.getBackground());
        
        lbl_title.setBackground         (BG_TITLE);
        lbl_title.setForeground         (FG_DEFAULT);
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setVerticalAlignment  (JLabel.CENTER);
        lbl_title.setFont               (this.getFont());
        lbl_title.setBorder             (PADDING_LBL);
        lbl_title.setOpaque             (true);
        lbl_title.setPreferredSize      (new Dimension(0, LBL_TITLE_HEIGHT));
        
        lbl_restaurantName.setBackground         (this.getBackground());
        lbl_restaurantName.setHorizontalAlignment(JLabel.CENTER);
        lbl_restaurantName.setVerticalAlignment  (JLabel.CENTER);
        lbl_restaurantName.setFont               (this.getFont());
        lbl_restaurantName.setBorder             (BorderFactory.createCompoundBorder(BORDER_LBL, PADDING_LBL));
        lbl_restaurantName.setOpaque             (true);
        
        for (int i = 0; i < lbl_stars.length; i++) 
        {
            lbl_stars[i] = new CustomJLabel("", EMPTY_STAR);
            lbl_stars[i].setBackground          (BG_STAR_LBL);
            lbl_stars[i].setForeground          (FG_DEFAULT);
            lbl_stars[i].setCharacterColor      (BG_STAR_CHAR);
            lbl_stars[i].setHorizontalAlignment (JLabel.CENTER);
            lbl_stars[i].setVerticalAlignment   (JLabel.CENTER);
            lbl_stars[i].setFont                (this.getFont());
            lbl_stars[i].setCustomFontSize      (64f);
            lbl_stars[i].setOpaque              (true);
            pnl_ratingBar.add                   (lbl_stars[i]);
        }
        
        starClicked = true;
        starRating  = review.getRating();
        indexStar   = (int) starRating;
        for (int i = 0; i < (int) starRating; i++)
            lbl_stars[i].setCharacter(FULL_STAR);
        if (starRating - (int) starRating == 0.5)
            lbl_stars[(int) starRating].setCharacter(starRating - (int) starRating == 0.5 ? HALF_STAR : EMPTY_STAR);
        
        txt_content.setBackground   (this.getBackground());
        txt_content.setBorder       (PADDING_TXT);
        txt_content.setFont         (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 22));
        txt_content.setLineWrap     (true);
        txt_content.setWrapStyleWord(true);
        
        scrlPnl_content.setBackground                 (this.getBackground());
        scrlPnl_content.setBorder                     (BORDER_PNL);
        scrlPnl_content.getVerticalScrollBar()  .setUI(new CustomJScrollBar());
        scrlPnl_content.getHorizontalScrollBar().setUI(new CustomJScrollBar());
        
        btn_edit.setBackground         (BG_ADDRESPONSE_BTN);
        btn_edit.setForeground         (FG_DEFAULT);
        btn_edit.setHorizontalAlignment(JLabel.CENTER);
        btn_edit.setVerticalAlignment  (JLabel.CENTER);
        btn_edit.setFont               (this.getFont());
        btn_edit.setOpaque             (true);
        
        btn_cancel.setBackground         (BG_BACK_BTN);
        btn_cancel.setForeground         (FG_DEFAULT);
        btn_cancel.setHorizontalAlignment(JLabel.CENTER);
        btn_cancel.setVerticalAlignment  (JLabel.CENTER);
        btn_cancel.setFont               (this.getFont());
        btn_cancel.setOpaque             (true);
        
        pnl_btn_edit .add(btn_editRounded,      BorderLayout.CENTER);
        pnl_btn_cancel.add(btn_cancelRounded, BorderLayout.CENTER);
        
        pnl_btns.setPreferredSize(new Dimension(this.getWidth(), PNL_BTNS_HEIGHT));
        pnl_btns.add(pnl_btn_edit,    BorderLayout.CENTER);
        pnl_btns.add(pnl_btn_cancel, BorderLayout.EAST);
        
        pnl_nameRating.add(lbl_restaurantName);
        pnl_nameRating.add(pnl_ratingBar);
        
        pnl_content.add(pnl_nameRating,  BorderLayout.NORTH);
        pnl_content.add(scrlPnl_content, BorderLayout.CENTER);
        
        this.add(lbl_title,       BorderLayout.NORTH);
        this.add(pnl_content,     BorderLayout.CENTER);
        this.add(pnl_btns,        BorderLayout.SOUTH);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() 
    {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                editReview_ComponentResized(e);
            }
        });
        
        btn_cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_cancel_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_cancel_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_cancel_MouseExited(e);
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
        
        for (JLabel lbl : lbl_stars) {
            lbl.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    lbl_star_MouseClicked(e);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    lbl_star_MouseExited(e);
                }
            
            });
            
            lbl.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                @Override
                public void mouseMoved(java.awt.event.MouseEvent e) {
                    lbl_star_MouseMoved(e);
                }
            });
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the {@code EditReview} {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     * 
     * @param e the component event triggered by resizing the GUI application
     */
    private void editReview_ComponentResized(java.awt.event.ComponentEvent e) 
    {
        final int PADDING_BTN = (int) (this.getWidth() * 0.01);
        pnl_btn_edit   .setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
        pnl_btn_cancel.setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
    }
    
    /**
     * Handles the click event on the cancel button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code Home} screen canceling the edit procedure.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_cancel_MouseClicked(java.awt.event.MouseEvent e)
    {
      CustomerReviews customerReviews = new CustomerReviews(controller);
      controller.getPanelMain().getPanel().add(customerReviews, Page.CUSTOMER_REVIEWS);
      controller.getPanelMain().showCard(Page.CUSTOMER_REVIEWS);
      controller.getPanelMain().getPanel().remove(this);
    }
    
    /**
     * Handles the hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_cancel_MouseEntered(java.awt.event.MouseEvent e) 
    {
      btn_cancel.setCursor      (new Cursor(Cursor.HAND_CURSOR));
      btn_cancel.setBackground  (btn_cancel.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_cancel_MouseExited(java.awt.event.MouseEvent e) 
    {
      btn_cancel.setBackground(BG_BACK_BTN);
    }
    
    /**
     * Handles the click event on the edit button {@link JLabel}.
     * <p>
     * When the button is clicked, the {@link Review} will be edited and the view switches to the {@code CustoerReviews} screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_edit_MouseClicked(java.awt.event.MouseEvent e) 
    {
      if(starRating!=0 && !txt_content.getText().trim().isEmpty())
      {

        Review newReview = new Review(review.getID(),
                                      restaurant.getId(),
                                      controller.getLoggedUser().getUsername(),
                                      txt_content.getText(),
                                      starRating);   
        
        restaurant.getListReview().getList().remove(newReview.getID() - 1);
        restaurant.getListReview().getList().add   (newReview.getID() - 1, newReview);
        controller.restaurantRatingAverage(restaurant);
        
        CustomerReviews customerReviews = new CustomerReviews(controller);
        controller.getPanelMain().getPanel().add(customerReviews, Page.CUSTOMER_REVIEWS);
        controller.getPanelMain().showCard(Page.CUSTOMER_REVIEWS);
        controller.getPanelMain().getPanel().remove(this);
      }

    }
    
    /**
     * Handles the hover event on the edit button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_edit_MouseEntered(java.awt.event.MouseEvent e)
    {
      btn_edit.setCursor       (new Cursor(Cursor.HAND_CURSOR));
      btn_edit.setBackground   (btn_edit.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the edit button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_edit_MouseExited(java.awt.event.MouseEvent e) 
    {
      btn_edit.setBackground(BG_ADDRESPONSE_BTN);
    }
    
    /**
     * Handles the click event on the star {@link JLabel}.
     * <p>
     * When the label is clicked, it checks if the rating given has a half star or not.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the label 
     */
    private void lbl_star_MouseClicked(java.awt.event.MouseEvent e) 
    {
      starClicked = true;
      for (int i = 0; i < lbl_stars.length; i++) 
      {
        if (lbl_stars[i].equals(e.getSource())) 
        {
          indexStar = i;
          lbl_stars[i].setCharacter(e.getX() <= lbl_stars[i].getWidth() / 2 ? HALF_STAR : FULL_STAR);
          starRating = e.getX() <= lbl_stars[i].getWidth() / 2 ? i + 0.5 : i + 1;
           break;
        }
        lbl_stars[i].setCharacter(FULL_STAR);
      }
    }
    
    /**
     * Handles the hover event on the stars {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the label
     */
    private void lbl_star_MouseMoved(java.awt.event.MouseEvent e) 
    {
      for (CustomJLabel lbl : lbl_stars) 
        lbl.setCharacter(EMPTY_STAR);
            
      for (CustomJLabel lbl : lbl_stars) 
      {
        if (lbl.equals(e.getSource())) 
        {
          lbl.setCharacter(e.getX() <= lbl.getWidth() / 2 ? HALF_STAR : FULL_STAR);
          break;
        }
        lbl.setCharacter(FULL_STAR);
      }
    }
    
    /**
     * Handles the exit hover event on the stars {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the label
     */
    private void lbl_star_MouseExited(java.awt.event.MouseEvent e) 
    {
      for (CustomJLabel lbl : lbl_stars) 
       lbl.setCharacter(EMPTY_STAR);
        
      if (starClicked) 
      {
        for (int i = 0; i < indexStar; i++) 
          lbl_stars[i].setCharacter(FULL_STAR);
        lbl_stars[indexStar].setCharacter(starRating - indexStar == 0.5 ? HALF_STAR : FULL_STAR);
      }
    }
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(119, 186, 71));
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
    private final Color              FG_DEFAULT            = Color.BLACK;
    private final Color              BG_TITLE              = new Color(157, 204, 49);
    private final Color              BG_ADDRESPONSE_BTN    = new Color(0, 255, 0, 192);
    private final Color              BG_BACK_BTN           = new Color(255, 64, 0, 192);
    private final Color              BG_PNL_BTNS           = new Color(94, 168, 69);
    private final Color              BG_STAR_LBL           = new Color(85, 191, 33);
    private final Color              BG_STAR_CHAR          = new Color(255, 215, 0);
    private final Border             PADDING_LBL           = BorderFactory.createEmptyBorder(0, 5, 0, 5);
    private final Border             PADDING_TXT           = BorderFactory.createEmptyBorder(3, 3, 3, 3);
    private final Border             BORDER_LBL            = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
    private final Border             BORDER_PNL            = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
    private final String             TITLE                 = "Edit Review";
    private final String             EDIT_REVIEW           = "Edit";
    private final String             CANCEL                = "Cancel";
    private final char               EMPTY_STAR            = 'A';
    private final char               HALF_STAR             = 'B';
    private final char               FULL_STAR             = 'C';
    private final int                LBL_TITLE_HEIGHT      = 80;
    private final int                ARC                   = 50;
    private final int                PNL_BTNS_HEIGHT       = 80;
    private final int                RATINGS               = 5;
    private final int                PNL_NAMERATING_HEIGHT = 80;
    private final RoundedComponentUI BTN_LAYERUI           = new RoundedComponentUI(ARC);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private JPanel             pnl_content;
    private JPanel             pnl_nameRating;
    private JPanel             pnl_ratingBar;
    private JPanel             pnl_btns;
    private JPanel             pnl_btn_edit;
    private JPanel             pnl_btn_cancel;
    private JScrollPane        scrlPnl_content;
    private CustomJLabel[]     lbl_stars;
    private JLabel             lbl_title;
    private JLabel             lbl_restaurantName;
    private JLabel             btn_edit;
    private JLabel             btn_cancel;
    private JTextArea          txt_content;
    private JLayer<JComponent> btn_editRounded;
    private JLayer<JComponent> btn_cancelRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller controller;
    private final Restaurant restaurant;
    private final Review     review;
    private       boolean    starClicked;
    private       double     starRating;
    private       int        indexStar;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
