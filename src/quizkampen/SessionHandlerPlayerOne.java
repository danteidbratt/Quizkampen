/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 *  och öppna templates :)
 */
package quizkampen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionHandlerPlayerOne extends Thread{

    protected Window w;

    public SessionHandlerPlayerOne(Window w) {
        this.w = w;
    }

    @Override
    public void run() {
        try {
            while (true) {
                w.session = (SessionQ) w.inGameServer.readObject();
                switch (w.session.getState()) {
                    case CHOOSESUBJECT: 
                        w.ls.loopAnimation = false;
//                        w.session.setState(State.PLAYGAME);
//                        w.outGameServer.writeObject(w.session); // skickar session -> server från P1
                        break;
                    case ANSWERQUESTIONS:
                        break;
                        
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SessionHandlerPlayerOne.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
