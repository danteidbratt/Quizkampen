package quizkampen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;

public class WelcomeScreen extends MasterPanel {

    private final JPanel loginPanel = new JPanel();
    private final JPanel centerPanel = new JPanel();
    private final JPanel exitPanel = new JPanel();

    private final JLabel loginText = new JLabel("Login");
    private final JLabel centerSpace = new JLabel("");

    JTextField userNameInput = new JTextField("Enter username to start");
    JButton okButton = new JButton("OK");
    JButton exitButton = new JButton("EXIT");

    @Override
    public void setPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setPreferredSize(new Dimension(0, 200));
        logo.setForeground(logoColor);
        logo.setFont(logoFont);

        leftSpace.setPreferredSize(new Dimension(100, 0));
        rightSpace.setPreferredSize(new Dimension(100, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 150));

        loginPanel.setLayout(new GridLayout(3, 1, 0, 5));
        loginPanel.setBackground(backgroundColor);
        loginText.setHorizontalAlignment(SwingConstants.CENTER);
        loginText.setFont(infoTextFontBig);
        loginText.setForeground(infoTextColor);
        userNameInput.setHorizontalAlignment(SwingConstants.CENTER);
        userNameInput.setFont(new Font("SansSerif", 1, 16));
        okButton.setFont(buttonFont);
        loginPanel.add(loginText);
        loginPanel.add(userNameInput);
        loginPanel.add(okButton);

        exitPanel.setLayout(new GridLayout(3, 1));
        exitPanel.setBackground(backgroundColor);
        exitButton.setFont(buttonFont);
        exitPanel.add(exitButton);
        exitPanel.revalidate();
        exitPanel.repaint();

        centerPanel.setLayout(new GridLayout(3, 1));
        centerPanel.setBackground(backgroundColor);
        centerPanel.add(loginPanel);
        centerPanel.add(centerSpace);
        centerPanel.add(exitPanel);

        add(logo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);

        FocusAdapter a = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                userNameInput.setText("");
            }
        };
        userNameInput.addFocusListener(a);
        okButton.requestFocusInWindow();

        revalidate();
        repaint();
    }

    @Override
    public void setActionListener(ActionListener al) {
        userNameInput.addActionListener(al);
        okButton.addActionListener(al);
        exitButton.addActionListener(al);
    }
}