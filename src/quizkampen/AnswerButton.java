
package quizkampen;

import javax.swing.JButton;

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