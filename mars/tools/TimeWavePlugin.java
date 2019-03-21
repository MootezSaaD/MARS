/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mars.tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Math.sin;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import mars.Globals;
import mars.venus.RunAssembleAction;
import mars.venus.RunBackstepAction;
import mars.venus.RunStepAction;
import mars.venus.VenusUI;

/**
 *
 * @author MOOTEZ
 */

public class TimeWavePlugin extends AbstractMarsToolAndApplication {
     private VenusUI mainUI;
     private Container painel = this.getContentPane();
     private JToolBar toolbar;
         private JButton  Assemble,  Step, runBackStep;
    private Action runAssembleAction, runStepAction, runBackstepAction;
        private static String heading =  "TimeWave - Real Registers Time Wave";
	private static String version = " Version 1.0";
        public TimeWavePlugin(String title, String heading) {
        super(title,heading);
     }
   	 
   	 /**
   	  *  Simple constructor, likely used by the MipsXray menu mechanism
   	  */
      public TimeWavePlugin() {
        super (heading+", "+version, heading);
     }
   		 
   		 
      /**
   	  *  Required method to return Tool name.  
   	  *  @return  Tool name.  MARS will display this in menu item.
   	  */
      public String getName() {
        return "MIPS TimeWave";
     }
      
            /**
    	  *  Overrides default method, to provide a Help button for this tool/app.
    	  */
        protected JComponent getHelpComponent() {
          final String helpContent = 
                             "This plugin is used to sketch the real-time time waves of the registers used \n"+
                             "This plugin is a part of the ISS296-MedTech\n"+
                             "By: Mootez Saad - Taissyr Sta Ali - Louay Rouabeh - Seif Rahali";
          JButton help = new JButton("Help");
          help.addActionListener(
                 new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      JOptionPane.showMessageDialog(theWindow, helpContent);
                   }
                });		
          return help;  
       }
        // Insert image in the panel and configure the parameters to run animation.
      protected JComponent buildMainDisplayArea() {
           mainUI = Globals.getGui();
      	   this.createActionObjects();
          toolbar= this.setUpToolBar();
          painel.add(toolbar, BorderLayout.NORTH);
          painel.add(new DrawSine());
          
          
   	     return (JComponent) painel;
    }
        //set the tool bar that controls the step in a time instruction running.
           private JToolBar setUpToolBar() {
           JToolBar toolBar = new JToolBar();
           Assemble = new JButton(runAssembleAction);
           Assemble.setText(""); 
           runBackStep = new JButton(runBackstepAction);
           runBackStep.setText(""); 
           
           Step = new JButton(runStepAction);
           Step.setText("");      	
           toolBar.add(Assemble);
           toolBar.add(Step);

           return toolBar;     
       }
       
        //set action in the menu bar.
       private void createActionObjects() {
           Toolkit tk = Toolkit.getDefaultToolkit();
           Class cs = this.getClass();
           try{
               runAssembleAction = new RunAssembleAction("Assemble",  
                       new ImageIcon(tk.getImage(cs.getResource(Globals.imagesPath+"Assemble22.png"))),
   							  "Assemble the current file and clear breakpoints", new Integer(KeyEvent.VK_A),
   							  KeyStroke.getKeyStroke( KeyEvent.VK_F3, 0), 
   							  mainUI);			

               runStepAction = new RunStepAction("Step", 
                       new ImageIcon(tk.getImage(cs.getResource(Globals.imagesPath+"StepForward22.png"))),
   							  "Run one step at a time", new Integer(KeyEvent.VK_T),
   							  KeyStroke.getKeyStroke( KeyEvent.VK_F7, 0),
   							  mainUI);	
               runBackstepAction = new RunBackstepAction("Backstep", 
                       new ImageIcon(tk.getImage(cs.getResource(Globals.imagesPath+"StepBack22.png"))),
   							  "Undo the last step", new Integer(KeyEvent.VK_B),
   							  KeyStroke.getKeyStroke( KeyEvent.VK_F8, 0), 
   							  mainUI);		
           }
           catch(Exception e){
               System.out.println("Internal Error: images folder not found, or other null pointer exception while creating Action objects");
               e.printStackTrace();
               System.exit(0);
           }
       }
       class DrawSine extends JPanel {

     public void paint(Graphics g)
    {
        g.drawLine(0,350,900,350); // x-axis
        g.drawLine(250,0,250,900); // y-axis
        
        g.setColor(Color.red);
        
        for(double x=-450;x<=450;x=x+0.5)
        {
            double y = 50 * sin(x*(3.1415926/180));
            int Y = (int)y;
            int X = (int)x;
            g.drawLine(450+X,350-Y,450+X,350-Y);
        }
    }
       }
    
}
