package quizkampen;

public class SessionHandler {
    private SessionQ sessionQ;
    protected States state = States.CONNECTED;
    protected enum States {CONNECTED, WAITINGFOROPPONENTTOCONNECT,
        PLAYINGGAME, WAITINGFORSERVER, IDLE
    }

    public void checkStates() {
        switch(this.state) {
            case CONNECTED:

                break;

            case WAITINGFOROPPONENTTOCONNECT:

                break;

            case PLAYINGGAME:

                break;

            case WAITINGFORSERVER:

                break;

            case IDLE:

                break;
        }
    }







    public SessionHandler(SessionQ sessionQ) {
        this.sessionQ = sessionQ;
    }

    public void setSessionQ(SessionQ sessionQ) {

    }

    public static void main(String[] args) {
        //SessionHandler sessionHandler = new SessionHandler();
    }
}

