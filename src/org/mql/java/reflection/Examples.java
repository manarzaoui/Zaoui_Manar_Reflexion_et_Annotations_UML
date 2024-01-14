package org.mql.java.reflection;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Examples {

	public Examples() {
		 exp01();
	}
     void exp01() {
		try {
			String path="\\Manar_zaoui\\MQL\\java\\p01-revision\\p03-Annotations and Reflection\\bin";;

			File projectURL = new File(path);
	            List<String>packages  =ClassParser.getAllPackages(projectURL);
	            List<String> classes =ClassParser.extractClasses(packages,path);
	            List<String> interafaces =ClassParser.extractInterfaces(packages,path);
	            List<String> annotations =ClassParser.extractAnnotations(packages,path);
                System.out.println("mes packages :====>");
	            packages.forEach(System.out::println);
                System.out.println("\n");
	            System.out.println("mes classes :====>");
	            classes.forEach(System.out::println);
                System.out.println("\n");

	            System.out.println("mes intaerfaces :====>");
	            interafaces.forEach(System.out::println);
                System.out.println("\n");

	            System.out.println("mes annotations :====> ");
	            annotations.forEach(System.out::println);
                System.out.println("\n");




	            
		     
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
