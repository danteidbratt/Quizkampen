package quizkampen;

public class SessionHandler {

    private SessionQ sessionQ;
    private int totalRonds;
    private int totalQuestionsinRond;
    private PropertiesReader p;

//    private enum States {
//        CONNECTED, WAITINGFOROPPONENTTOCONNECT,
//        PLAYINGGAME, WAITINGFORSERVER, IDLE
//    }

    public SessionHandler(SessionQ sessionQ) {
        this.sessionQ = sessionQ;
        p = new PropertiesReader();
        this.totalRonds = p.getRonds();
        this.totalQuestionsinRond = p.getQuestionsInRond();
        this.sessionQ.setTotalRonds(totalRonds);
        this.sessionQ.setTotalQsInRond(totalQuestionsinRond);
    }

//    public void setState(States state) {
//        this.state = state;
//    }
//    public States getState(SessionQ session) {
//        return state; 
//    }
}
