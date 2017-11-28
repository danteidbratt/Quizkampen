
package quizkampen.client.panels;

import javax.swing.JButton;
import quizkampen.server.AnswerAlternative;

public class AnswerButton extends JButton {
    
    private boolean isCorrect;
    
    public void setButton(AnswerAlternative aa) {
        setText("<html><p>" + aa.getAnswer() + "</p></html>");
        isCorrect = aa.isCorrectAnswer();
    }
    
    public boolean getIsCorrect(){
        return isCorrect;
    }
}