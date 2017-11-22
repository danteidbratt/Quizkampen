package quizkampen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionHandler {

    private SessionQ sessionQ;
    protected Window w;
    

    public SessionHandler(Window w) {
        this.w = w;
    }

    public void checkGame(SessionQ session){
        this.sessionQ = session;
                    try {               
                switch (session.getState()) {
                    case WAITING4P1USERNAME: // Servern skapas. UserOne skriver in UserName
                        session.setUserNameOne(w.user);
                        w.setPlayerNumber(1);
                        w.rs.setResultScreen(session.getTotalQsInRound(), session.getTotalRounds(), "Pronut", "David");
                        w.rs.setPanel();
                        w.rs.setActionListener(w.ah);
//
                        w.ls.subjectButtons[0].setText(w.session.getSubject().getName());
                        w.ls.subjectButtons[1].setText(w.session.getSubject().getName());   
                        w.ls.subjectButtons[2].setText(w.session.getSubject().getName());

                        w.session.setState(State.WAITING4P2USERNAME);
                        w.outGameServer.writeObject(session);
                        w.revalidate();
                        w.repaint();
                        break;

                    case WAITING4P2USERNAME: // UserTwo skriver in UserName
                        session.setUserNameTwo(w.user);
                        w.setPlayerNumber(2);
                        w.outGameServer.writeObject(session);
                        w.session.setState(State.WAITINGFORCHOICE);
                        break;
                        
                    case WAITINGFORCHOICE: 
                        
                        w.session.setState(State.CHOICESENT);
                        break;
                        
                        
                        
                        
                        
                    case CHOICESENT:
                        
                        w.session.setState(State.PLAYGAME);
                        break;
                        
                        
                        
                        
                        
                        
                    case PLAYGAME://Chosing Subject, Talar om vems tur de är- väljer ämne.UserTwo får info om ämne.
                        if (session.getUserChosing() == w.user) {
                                
                            
                            
                            session.ChangeUserChosing();
                        }
                        break;
                }

                
                
                
                
                
                
                
                
                
                
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    
    }
}
