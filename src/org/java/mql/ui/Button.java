package org.java.mql.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Button extends JPanel {

    private static final long serialVersionUID = 2L;
    private String labelForm;
    public Button(String label, int width, String labelForm) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.labelForm = labelForm;
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(width, button.getPreferredSize().height));
        button.addActionListener(new ItemListener());
       
        add(button);
    }

    private class ItemListener implements ActionListener {

        public ItemListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Open a new JFrame with the corresponding content
          
        }

       
    }
}
