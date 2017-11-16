package quizkampen;

import java.awt.Color;
import java.awt.event.ActionListener;

public interface IPanel {
    
    void setPanel();
    
    void setActionListener(ActionListener al);
    
    void setCustomColor(Color backgroundColor, Color logoColor, Color infoTextColor);
    
}