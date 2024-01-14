package org.mql.java.reflection;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Examples {

	public Examples() {
		 exp01();
	}
     void exp01() {
		try {
			String path="\\Manar_zaoui\\MQL\\java\\p01-revision\\Zaoui_Manar_Reflexion_et_Annotations_UML\\bin";;
			String pathfile="\\Manar_zaoui\\output.xml";;

			File projectURL = new File(path);
	            List<String>packages  =ClassParser.getAllPackages(projectURL);
	            Map<String, List<Class>>  classes =ClassParser.extractClasses(packages,path);
	            
	            
	          List<String> myclasses = new ArrayList<>();
	          for (Map.Entry<String, List<Class>> entry : classes.entrySet()) {
	              StringBuilder packageInfo = new StringBuilder(entry.getKey() + ": ");
	              for (Class className : entry.getValue()) {
	              	
	                  packageInfo.append(className.getSimpleName()).append(", ");
	              }
	  
	              myclasses.add(packageInfo.substring(0, packageInfo.length() - 2));
	          }
	            Map<String, List<Class>> interfaces =ClassParser.extractInterfaces(packages,path);

	        List<String> resultinterfaces = new ArrayList<>();
	        for (Map.Entry<String, List<Class>> entry : interfaces.entrySet()) {
	            StringBuilder packageInfo = new StringBuilder(entry.getKey() + ": ");
	            for (Class className : entry.getValue()) {
	            	
	                packageInfo.append(className.getSimpleName()).append(", ");
	            }
	            resultinterfaces.add(packageInfo.substring(0, packageInfo.length() - 2));
	        }
	          
	            List<String> annotations =ClassParser.extractAnnotations(packages,path);
	            List<String> relationShips =ClassParser.extractRelations(classes,interfaces);

                System.out.println("mes packages :====>");
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


	             ClassParser.persistProjet(classes, interfaces, pathfile);
		     
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
