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
import org.java.mql.ui.LabelTextField;
import org.java.mql.ui.Package;
import org.mql.java.dataStructure.ClassModel;


public class Examples {
    private LabelTextField labelTextField;  // Reference to LabelTextField

	public Examples() {
		 exp01();
	}
     void exp01() {
		try {
			
			String path="\\Manar_zaoui\\MQL\\java\\p01-revision\\Zaoui_Manar_Reflexion_et_Annotations_UML\\bin";;
			String pathfile="\\output.xml";;

			File projectURL = new File(path);
	         List<String> packages  =ClassParser.getAllPackages(projectURL);
	        Map<String, List<Class>>  classes =ClassParser.extractClasses(packages,path);
	            
	            
	          List<String> myclasses = new ArrayList<>();
	          for (Map.Entry<String, List<Class>> entry : classes.entrySet()) {
	              StringBuilder FormInfo = new StringBuilder(entry.getKey() + ": ");
	              for (Class className : entry.getValue()) {
	              	
	                  FormInfo.append(className.getSimpleName()).append(", ");
	              }
	  
	              myclasses.add(FormInfo.substring(0, FormInfo.length() - 2));
	          }
	        Map<String, List<Class>> interfaces =ClassParser.extractInterfaces(packages,path);

	        List<String> resultinterfaces = new ArrayList<>();
	        for (Map.Entry<String, List<Class>> entry : interfaces.entrySet()) {
	            StringBuilder FormInfo = new StringBuilder(entry.getKey() + ": ");
	            for (Class className : entry.getValue()) {
	            	
	                FormInfo.append(className.getSimpleName()).append(", ");
	            }
	            resultinterfaces.add(FormInfo.substring(0, FormInfo.length() - 2));
	        }
	          
	            List<String> annotations =ClassParser.extractAnnotations(packages,path);
	            List<String> relationShips =ClassParser.extractRelations(classes,interfaces);

                System.out.println("mes Packages :====>");
	            packages.forEach(System.out::println);
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
                Form form = new Form(500, 150);

                // Set up the frame with the scroll pane as content
                jframe.setContentPane(form);
               form.addField("url projet", 30, "projet");
               form.addButton("Diagramme de classe", 200, "class");
               form.addButton("Diagramme de package", 200, "package");
               form.addButton("persister à xml", 200, "xml");

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
