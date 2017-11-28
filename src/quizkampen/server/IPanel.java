package quizkampen.server;

import java.awt.Color;
import java.awt.event.ActionListener;

public interface IPanel {
    
    public void setPanel();
    
    public void setActionListener(ActionListener al);
    
    public void setCustomColor(Color backgroundColor, Color logoColor, Color infoTextColor, Color nuance);
    
}