package quizkampen;

import java.net.Socket;

public class Client {

    Socket bridge;
    SessionQ session;
    Window w;

    public Client() {
        Window w = new Window();

        w.setFrame();
        w.ws.setPanel();
        w.ws.setActionListener(w);

    }

    public static void main(String[] args) {
        Client c = new Client();
    }
}
