package quizkampen;

public class SessionHandler {
    private SessionQ sessionQ;
    private States state;
    private enum States {CONNECTED, WAITINGFOROPPONENTTOCONNECT,
        PLAYINGGAME, WAITINGFORSERVER, IDLE
    }

    public SessionHandler(SessionQ sessionQ) {
        this.sessionQ = sessionQ;
    }

    public void setState(States state) {
        this.state = state;
    }

    public void checkState() {
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

    public static void main(String[] args) {
        
    }
}

