package org.mql.java.reflection;

import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.java.mql.ui.ClassPanel;
import org.java.mql.ui.Form;
import org.java.mql.ui.Package;
import org.mql.java.dataStructure.ClassModel;


public class Examples {

	public Examples() {
		 exp01();
	}
     void exp01() {
		try {
			String path="\\Manar_zaoui\\MQL\\java\\p01-revision\\Zaoui_Manar_Reflexion_et_Annotations_UML\\bin";;
			String pathfile="\\Manar_zaoui\\output.xml";;

			File projectURL = new File(path);
	            List<String>Forms  =ClassParser.getAllPackages(projectURL);
	            Map<String, List<Class>>  classes =ClassParser.extractClasses(Forms,path);
	            
	            
	          List<String> myclasses = new ArrayList<>();
	          for (Map.Entry<String, List<Class>> entry : classes.entrySet()) {
	              StringBuilder FormInfo = new StringBuilder(entry.getKey() + ": ");
	              for (Class className : entry.getValue()) {
	              	
	                  FormInfo.append(className.getSimpleName()).append(", ");
	              }
	  
	              myclasses.add(FormInfo.substring(0, FormInfo.length() - 2));
	          }
	            Map<String, List<Class>> interfaces =ClassParser.extractInterfaces(Forms,path);

	        List<String> resultinterfaces = new ArrayList<>();
	        for (Map.Entry<String, List<Class>> entry : interfaces.entrySet()) {
	            StringBuilder FormInfo = new StringBuilder(entry.getKey() + ": ");
	            for (Class className : entry.getValue()) {
	            	
	                FormInfo.append(className.getSimpleName()).append(", ");
	            }
	            resultinterfaces.add(FormInfo.substring(0, FormInfo.length() - 2));
	        }
	          
	            List<String> annotations =ClassParser.extractAnnotations(Forms,path);
	            List<String> relationShips =ClassParser.extractRelations(classes,interfaces);

                System.out.println("mes Forms :====>");
	            Forms.forEach(System.out::println);
                System.out.println("\n");
	            System.out.println("mes classes :====>");
	            myclasses.forEach(System.out::println);
                System.out.println("\n");

	            System.out.println("mes intaerfaces :====>");
	            resultinterfaces.forEach(System.out::println);
                System.out.println("\n");

	            System.out.println("mes annotations :====> ");
	            annotations.forEach(System.out::println);
                System.out.println("\n");

	            System.out.println("mes relation :====> ");
	            relationShips.forEach(System.out::println);
                System.out.println("\n");


//	             ClassParser.persistProjet(classes, interfaces, pathfile);
	             
	              
                JFrame jframe = new JFrame();
                Form form = new Form(800, 1500);
                JScrollPane scrollPane = new JScrollPane(form);

                // Set up the frame with the scroll pane as content
                jframe.setContentPane(scrollPane);
                int startX = 10;
                int startY = 30;
                int height = 40;
                for (Map.Entry<String, List<Class>> entry : classes.entrySet()) {
                  	for (Class className : entry.getValue()) {
                        form.add(new ClassPanel(className));
                        startX += 200;
							//if (startX > (10 + 200 * 2)) {
							//                         startX = 10;
							//                      startY += height * 2;
							//                      height = 45;
							//                  }
    				}
                }
                for (Map.Entry<String, List<Class>> entry : interfaces.entrySet()) {
                	for (Class className : entry.getValue()) {
                        form.add(new ClassPanel(className));
                        startX += 200;
							//if (startX > (10 + 200 * 2)) {
							//                         startX = 10;
							//                      startY += height * 2;
							//                      height = 45;
							//                  }
    				}
                }
                
                jframe.pack();
                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jframe.setLocationRelativeTo(null);
                jframe.setVisible(true);
               
               
	         		  

	         	
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     void exp02() {
    	
  	   
 	}
	public static void main(String[] args) {
	      new Examples();
		}

}
