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
    private   Map<String, List<Class>> classes;
    public Button(String label, int width, String labelForm,  Map<String, List<Class>> classes) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.labelForm = labelForm;
        this.classes=classes;
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
            if (labelForm.equals("class")) {
                createMyClassFrame();
            } else if (labelForm.equals("package")) {
                createPackageFrame();
            }
        }

        private void createMyClassFrame() {
            JFrame jframe = new JFrame();
            Form form = new Form(800, 500);
            jframe.setContentPane(form);
            jframe.pack();
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setLocationRelativeTo(null);
            jframe.setVisible(true);

            int startX = 10;
            int startY = 30;

            for (Map.Entry<String, List<Class>> entry : classes.entrySet()) {
            	for (Class className : entry.getValue()) {
//                    form.add(new ClassPanel(className));
                    startX += 200;
 
				}
            }
        }

        private void createPackageFrame() {
            JFrame jframe = new JFrame();
            Form form = new Form(800, 500);
            jframe.setContentPane(form);
            jframe.pack();
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setLocationRelativeTo(null);
            jframe.setVisible(true);

            int startX = 10;
            int startY = 30;
            int height = 40;

            // Add Package instances to the Form
            for (Map.Entry<String, List<Class>> entry : classes.entrySet()) {
                form.add(new Package(startX, startY, 150, height, entry.getKey(), entry.getValue()));
                startX += 200;

                if (startX > (10 + 200 * 2)) {
                    startX = 10;
                    startY += height * 2;
                    height = 45;
                }
            }
        }
    }
}
