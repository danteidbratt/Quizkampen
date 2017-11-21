package quizkampen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsScreen extends MasterPanel {

    private final JPanel centerPanel = new JPanel();
    final JRadioButton blue;
    final JRadioButton red;
    final JRadioButton green;
    final ButtonGroup buttonGroup;
    final JButton backButton;

    public SettingsScreen() {
        blue = new JRadioButton("Blue", true);
        red = new JRadioButton("Red", false);
        green = new JRadioButton("Green", false);
        buttonGroup = new ButtonGroup();
        backButton = new JButton("Back");
    }

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
        bottomSpace.setPreferredSize(new Dimension(0, 120));

        centerPanel.setLayout(new GridLayout(4, 1));
        centerPanel.setBackground(backgroundColor);
        backButton.setFont(buttonFont);
        blue.setFont(buttonFont);
        blue.setForeground(infoTextColor);
        blue.setOpaque(false);
        red.setFont(buttonFont);
        red.setForeground(infoTextColor);
        red.setOpaque(false);
        green.setFont(buttonFont);
        green.setForeground(infoTextColor);
        green.setOpaque(false);
        centerPanel.add(blue);
        centerPanel.add(red);
        centerPanel.add(green);
        centerPanel.add(backButton);
        buttonGroup.add(blue);
        buttonGroup.add(red);
        buttonGroup.add(green);

        add(logo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);
    }

    @Override
    public void setActionListener(ActionListener al) {
        blue.addActionListener(al);
        red.addActionListener(al);
        green.addActionListener(al);
        backButton.addActionListener(al);
    }
}