
package quizkampen;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

abstract public class MasterPanel extends JPanel implements IPanel{
    
    protected JLabel logo = new JLabel("QuizFights");;
    protected Color logoColor = Color.YELLOW;
    protected Font logoFont = new Font("SansSarif", 2, 80);
    protected Font buttonFont = new Font("SansSarif", Font.BOLD, 20);
    protected Font buttonFontSmall = new Font("SansSarif", Font.BOLD, 12);
    protected Color backgroundColor = new Color(20, 0, 190);
    protected Color infoTextColor = Color.WHITE;
    protected Font infoTextFontBig = new Font("SansSerif", 3, 30);
    protected Font infoTextFontSmall = new Font("SansSerif", 1, 20);
    protected Color nuance = new Color(20, 0, 150);
    
    protected final JLabel topSpace = new JLabel("");
    protected final JLabel leftSpace = new JLabel("");
    protected final JLabel rightSpace = new JLabel("");
    protected final JLabel bottomSpace = new JLabel("");
    
    public MasterPanel(){
    }
    
    @Override
    public void setCustomColor(Color backgroundColor, Color logoColor, Color infoTextColor, Color nuance) {
        this.backgroundColor = backgroundColor;
        this.logoColor = logoColor;
        this.infoTextColor = infoTextColor;
        this.nuance = nuance;
        removeAll();
        setPanel();
        revalidate();
        repaint();
    }
}