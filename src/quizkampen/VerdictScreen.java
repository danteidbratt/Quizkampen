package quizkampen;

public class VerdictScreen {
    private JPanel verdictScreen = new JPanel();
    private JButton home = new JButton("Home");

    VerdictScreen() {
        //TODO hämta verdict
        JLabel verdict = new JLabel("You won!");

        verdictScreen.setLayout(new BoxLayout(verdictScreen, BoxLayout.PAGE_AXIS));
        verdictScreen.add(home);
        verdictScreen.add(verdict);
    }

    public JPanel getVerdictScreen() {
        return verdictScreen;
    }
}