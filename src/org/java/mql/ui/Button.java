package org.java.mql.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.mql.java.reflection.ClassParser;

public class Button extends JPanel {

    private static final long serialVersionUID = 2L;
    private String labelForm;
    private LabelTextField labelTextField;  // Reference to LabelTextField
  
  
    public Button(String label, int width, String labelForm,LabelTextField labelTextField) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.labelForm = labelForm;
		this.labelTextField=labelTextField;
       
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
        	try {
			
        		switch (labelForm) {
				case "class":
				    createMyClassFrame();
					break;
				case "package":
	                createPackageFrame();
					break;
				case "xml":
	            	persisterProjetToXml();
					break;
				default:
					break;
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
        	
        }

        private void createMyClassFrame() {
        	try {
           	 String textFieldValue = labelTextField.getTextFieldValue();

        		 JFrame jframe = new JFrame();
                 Form form = new Form(1000, 700);
                 JScrollPane scrollPane = new JScrollPane(form);
                 jframe.setContentPane(scrollPane);
                 jframe.pack();
                 jframe.setLocationRelativeTo(null);
                 jframe.setVisible(true);


                 File projectURL = new File(textFieldValue+"/bin");
                 List<String> packages =ClassParser.getAllPackages(projectURL);
                 Map<String, List<Class>>  classes=ClassParser.extractClasses(packages,textFieldValue+"/bin");
                 for (Map.Entry<String, List<Class>> entry : classes.entrySet()) {
                	 
                 	for (Class className : entry.getValue()) {
                         form.add(new ClassPanel(packages,textFieldValue+"/bin",className));
      
     				}
                 }
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
           
        }

        
        private void persisterProjetToXml() {
           try {
        	   
        		 String textFieldValue = labelTextField.getTextFieldValue();	
              	 File projectURL = new File(textFieldValue+"/bin");
            	 List<String> packages =ClassParser.getAllPackages(projectURL);
                 Map<String, List<Class>>  classes=ClassParser.extractClasses(packages,textFieldValue+"/bin");
     	        Map<String, List<Class>> interfaces =ClassParser.extractInterfaces(packages,textFieldValue+"/bin");

                 ClassParser.persistProjet(classes, interfaces, textFieldValue);
			} catch (Exception e) {
			}
          	

        }
        private void createPackageFrame() {
	      
        	try {
           	 String textFieldValue = labelTextField.getTextFieldValue();

                File projectURL = new File(textFieldValue+"/bin");
                
                List<String> Forms =ClassParser.getAllPackages(projectURL);
                Map<String, List<Class>>  classes=ClassParser.extractClasses(Forms,textFieldValue+"/bin");
        		 JFrame jframe = new JFrame();
                 Form form = new Form(800, 500);
                 jframe.setContentPane(form);
                 jframe.pack();
                 jframe.setLocationRelativeTo(null);
                 jframe.setVisible(true);

                 int startX = 10;
                 int startY = 30;
                 int height = 40;

                 // Add Package instances to the Form
                 for (Map.Entry<String, List<Class>> entry : classes.entrySet()) {
                     form.add(new Package(startX, startY, 150, height, entry.getKey(), entry.getValue()));
                     startX += 200;

//                     if (startX > (10 + 200 * 2)) {
//                         startX = 10;
//                         startY += height * 2;
//                         height = 45;
//                     }
                 }
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
           
        }
    }
}
