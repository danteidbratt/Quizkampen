package quizkampen.client;

import quizkampen.server.Window;

public class Client {

    Window w;

    public Client() {
        Window w = new Window();
        w.setFrame();
    }
    
    public static void main(String[] args) {
        Client c = new Client();
    }
}