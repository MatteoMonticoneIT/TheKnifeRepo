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
import theknife.obj.lists.ListResponse;
import theknife.obj.review.Response;
import theknife.obj.review.Review;

/**
 * The {@code AddResponse} class is a panel for adding a {@link Response}.
 * <p>
 * This class is part of the GUI layer of the {@link ReviewGUI} and it is used for adding a {@code Response} filling all parameters.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public final class AddResponse extends javax.swing.JPanel 
{
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code AddResponse} panel and initializes its components.
     * <p>
     * This constructor also sets the {@link Review} used to insert the response in it. 
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     * @param review the {@link Review} class that represents the review
     * @param reviewGUI the {@link ReviewGUI} review panel
     */
    public       AddResponse    (Controller controller, Review review, ReviewGUI reviewGUI) 
    {
      initComponents();
      this.controller = controller;
      this.review     = review;
      this.reviewGUI  = reviewGUI;
      initGUI       ();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code AddResponse} page.
     */
    private void initGUI        ()
    {
      initFields();
      initAddResponse();
      initEvents();
    }
    
    /**
     * Initializes the basic fields of the {@code AddResponse} panel.
     */
    private void initFields     () 
    {
      pnl_btns                 = new JPanel     (new GridLayout     (1, 2, 10, 10));
      pnl_btn_add              = new JPanel     (new BorderLayout   ());
      pnl_btn_cancel           = new JPanel     (new BorderLayout   ());
      lbl_title                = new JLabel     (TITLE);
      btn_add                  = new JLabel     (ADD_RESPONSE);
      btn_cancel               = new JLabel     (CANCEL);
      txt_content              = new JTextArea  ();
      btn_addRounded           = new JLayer<>   (btn_add,    BTN_LAYERUI);
      btn_cancelRounded        = new JLayer<>   (btn_cancel, BTN_LAYERUI);
      scrlPnl_content          = new JScrollPane(txt_content, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    /**
     * Initializes the layout and appearance of the home page.
     */
    private void initAddResponse() 
    {
      this.setLayout(new BorderLayout());
        
      pnl_btns          .setBackground                  (BG_PNL_BTNS);
      pnl_btns          .setBorder                      (BorderFactory.createEmptyBorder());
        
        pnl_btns.setBackground(BG_PNL_BTNS);
        pnl_btns.setBorder    (BorderFactory.createEmptyBorder());
        
      pnl_btn_add       .setBackground                  (pnl_btns.getBackground());
      pnl_btn_cancel    .setBackground                  (pnl_btns.getBackground());
        
      lbl_title         .setBackground                  (BG_TITLE);
      lbl_title         .setForeground                  (FG_DEFAULT);
      lbl_title         .setHorizontalAlignment         (JLabel.CENTER);
      lbl_title         .setVerticalAlignment           (JLabel.CENTER);
      lbl_title         .setFont                        (this.getFont());
      lbl_title         .setBorder                      (PADDING_LBL);
      lbl_title         .setOpaque                      (true);
      lbl_title         .setPreferredSize               (new Dimension(0, LBL_TITLE_HEIGHT));
        
      txt_content       .setBackground                  (this.getBackground());
      txt_content       .setBorder                      (PADDING_TXT);
      txt_content       .setFont                        (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 22));
      txt_content       .setLineWrap                    (true);
      txt_content       .setWrapStyleWord               (true);
        
      scrlPnl_content   .setBackground                  (this.getBackground());
      scrlPnl_content   .setBorder                      (BORDER_PNL);
      scrlPnl_content   .getVerticalScrollBar()  .setUI (new CustomJScrollBar());
      scrlPnl_content   .getHorizontalScrollBar().setUI (new CustomJScrollBar());
        
      btn_add           .setBackground                  (BG_ADDRESPONSE_BTN);
      btn_add           .setForeground                  (FG_DEFAULT);
      btn_add           .setHorizontalAlignment         (JLabel.CENTER);
      btn_add           .setVerticalAlignment           (JLabel.CENTER);
      btn_add           .setFont                        (this.getFont());
      btn_add           .setOpaque                      (true);
        
      btn_cancel        .setBackground                  (BG_BACK_BTN);
      btn_cancel        .setForeground                  (FG_DEFAULT);
      btn_cancel        .setHorizontalAlignment         (JLabel.CENTER);
      btn_cancel        .setVerticalAlignment           (JLabel.CENTER);
      btn_cancel        .setFont                        (this.getFont());
      btn_cancel        .setOpaque                      (true);
        
      pnl_btn_add       .add                            (btn_addRounded,    BorderLayout.CENTER);
      pnl_btn_cancel    .add                            (btn_cancelRounded, BorderLayout.CENTER);
        pnl_btn_add   .add(btn_addRounded,    BorderLayout.CENTER);
        pnl_btn_cancel.add(btn_cancelRounded, BorderLayout.CENTER);
        
      pnl_btns          .setPreferredSize               (new Dimension(this.getWidth(), PNL_BTNS_HEIGHT));
      pnl_btns          .add                            (pnl_btn_add,       BorderLayout.CENTER);
      pnl_btns          .add                            (pnl_btn_cancel,    BorderLayout.EAST);
        pnl_btns.setPreferredSize(new Dimension(this.getWidth(), PNL_BTNS_HEIGHT));
        pnl_btns.add(pnl_btn_add,    BorderLayout.CENTER);
        pnl_btns.add(pnl_btn_cancel, BorderLayout.EAST);
        
      this              .add                            (lbl_title,         BorderLayout.NORTH);
      this              .add                            (scrlPnl_content,   BorderLayout.CENTER);
      this              .add                            (pnl_btns,          BorderLayout.SOUTH);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents     () 
    {
      this      .addComponentListener   (new java.awt.event.ComponentAdapter()
      {
        @Override
        public void componentResized    (java.awt.event.ComponentEvent e)   {addResponse_ComponentResized   (e);}
      });
        
      btn_cancel.addMouseListener       (new java.awt.event.MouseAdapter    () 
      {
        @Override
        public void mouseClicked        (java.awt.event.MouseEvent e)       {btn_cancel_MouseClicked        (e);}
            
        @Override
        public void mouseEntered        (java.awt.event.MouseEvent e)       {btn_cancel_MouseEntered        (e);}
            
        @Override
        public void mouseExited         (java.awt.event.MouseEvent e)       {btn_cancel_MouseExited         (e);}
      });
        
      btn_add   .addMouseListener       (new java.awt.event.MouseAdapter    () 
      {
        @Override
        public void mouseClicked        (java.awt.event.MouseEvent e)       {btn_add_MouseClicked           (e);}
            
        @Override
        public void mouseEntered        (java.awt.event.MouseEvent e)       {btn_add_MouseEntered           (e);}
            
        @Override
        public void mouseExited         (java.awt.event.MouseEvent e)       {btn_add_MouseExited            (e);}
      });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the {@code AddResponse} {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     * 
     * @param e the component event triggered by resizing the GUI application
     */
    private void addResponse_ComponentResized   (java.awt.event.ComponentEvent e) 
    {
      final int PADDING_BTN = (int) (this.getWidth() * 0.01);
      pnl_btn_add   .setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
      pnl_btn_cancel.setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
    }
    
    /**
     * Handles the click event on the cancel button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code ReviewGUI} screen canceling the add response procedure.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_cancel_MouseClicked        (java.awt.event.MouseEvent e)
    {
      controller.getPanelMain().showCard            (Page.REVIEW);
      controller.getPanelMain().getPanel().remove   (this);
    }
    
    /**
     * Handles the hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_cancel_MouseEntered        (java.awt.event.MouseEvent e) 
    {
      btn_cancel.setCursor      (new Cursor(Cursor.HAND_CURSOR));
      btn_cancel.setBackground  (btn_cancel.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_cancel_MouseExited         (java.awt.event.MouseEvent e) 
    {
      btn_cancel.setBackground(BG_BACK_BTN);
    }
    
    /**
     * Handles the click event on the add response button {@link JLabel}.
     * <p>
     * When the button is clicked, the {@link Response} will be added and the view switches to the {@link ReviewGUI} screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_add_MouseClicked           (java.awt.event.MouseEvent e) 
    {
      if(!txt_content.getText().trim().isEmpty())
      {
        if(review.getResponses()==null)
          review.setResponses(new ListResponse());
        review.getResponses().getList().add(new Response(review     .getResponses().getList().size()+1,
                                                         review     .getID(),
                                                         controller .getLoggedUser().getUsername(),
                                                         txt_content.getText()));
        reviewGUI.reloadResponses();
        controller.getPanelMain().showCard            (Page.REVIEW);
        controller.getPanelMain().getPanel().remove   (this);
      }
    }
    
    /**
     * Handles the hover event on the add response button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_add_MouseEntered           (java.awt.event.MouseEvent e) 
    {
      btn_add.setCursor     (new Cursor(Cursor.HAND_CURSOR));
      btn_add.setBackground (btn_add.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the add response button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_add_MouseExited            (java.awt.event.MouseEvent e) 
    {
      btn_add.setBackground(BG_ADDRESPONSE_BTN);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Consts">
    private final Color              FG_DEFAULT                = Color.BLACK;
    private final Color              BG_TITLE                  = new Color(157, 204, 49);
    private final Color              BG_ADDRESPONSE_BTN        = new Color(0, 255, 0, 192);
    private final Color              BG_BACK_BTN               = new Color(255, 64, 0, 192);
    private final Color              BG_PNL_BTNS               = new Color(94, 168, 69);
    private final Border             PADDING_LBL               = BorderFactory.createEmptyBorder(0, 5, 0, 5);
    private final Border             PADDING_TXT               = BorderFactory.createEmptyBorder(3, 3, 3, 3);
    private final Border             BORDER_PNL                = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
    private final String             TITLE                     = "Your Response";
    private final String             ADD_RESPONSE              = "Add";
    private final String             CANCEL                    = "Cancel";
    private final int                LBL_TITLE_HEIGHT          = 80;
    private final int                ARC                       = 50;
    private final int                PNL_BTNS_HEIGHT           = 80;
    private final RoundedComponentUI BTN_LAYERUI               = new RoundedComponentUI(ARC);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private JPanel             pnl_btns;
    private JPanel             pnl_btn_add;
    private JPanel             pnl_btn_cancel;
    private JScrollPane        scrlPnl_content;
    private JLabel             lbl_title;
    private JLabel             btn_add;
    private JLabel             btn_cancel;
    private JTextArea          txt_content;
    private JLayer<JComponent> btn_addRounded;
    private JLayer<JComponent> btn_cancelRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller controller;
    private final ReviewGUI  reviewGUI;
    private final Review     review;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Java Swing Auto Generated Code">
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(41, 197, 87));
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
