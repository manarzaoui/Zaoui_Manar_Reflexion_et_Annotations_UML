package org.mql.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import javax.swing.JFrame;
import javax.xml.namespace.QName;



public class ClassParser {

	public String ClassParser(String nomQ) {
		
		String sequellet="Compiled from '"+nomQ+"' ... \n";
		
		 Class<?> c3=null;
		   try {
			  c3= Class.forName(nomQ);
		} catch (Exception e) {
			 sequellet="Exception "+e.getMessage();
			 System.out.println("Exception "+e.getMessage());
//			  Form form =new Form("Erreur", 150);
//    			form.addTextArea("le nom qualifié de classe est invalid");
//    		    JFrame jFrame =new JFrame();
//    		    jFrame.add(form);
//    		    jFrame.setContentPane(form);
//    		    jFrame.pack();
//    		    jFrame.setVisible(true);
             
		}
		   
		 if(c3==null)return null ;
		
		 sequellet+="class '"+nomQ+"' extends ";
		 
		 sequellet+=getSupperClass(c3)+ " implements  ";
		 System.out.println(getSupperClass(c3));
		 Class<?> interfecs[] = c3.getInterfaces();
			
			for (Class<?> interfec : interfecs) {
				sequellet+=interfec.getName()+" ,";


			}
			
			sequellet+=" { \n";

	
		sequellet+=" \n";
		
		   Field fields[] =c3.getDeclaredFields();
	        int numberOfFileds= fields.length;

			for (Field field : fields) {
				sequellet+="  "+Modifier.toString(field.getModifiers()) +" "+field.getType().getSimpleName()+" "+field.getName()+" \n";


			}
			sequellet+=" \n";

			   Constructor construs[] =c3.getConstructors();
				
				for (Constructor constru : construs) {
					 sequellet+="  "+constru.getName()+" (";
					 if(constru.getParameterCount()!=0) {
						  
	                	   for (int i = 0; i < constru.getParameters().length; i++) {
	                		      sequellet+=""+ constru.getParameters()[i].getType().getSimpleName()+" "+
	                		    		  constru.getParameters()[i].getName()+",";
	                	   }
	                	   
						}else {
							
							sequellet+=") \n";

						}
                     

				}
				sequellet+=") \n \n";

			   Method methods[] =c3.getMethods();
		        int numberOfMethods = methods.length;
				for (Method method : methods) {
					sequellet+="  "+Modifier.toString(method.getModifiers())+" "+ method.getReturnType().getName() +" "+method.getName();
                   if(method.getParameterCount()!=0) {
                	   for (int i = 0; i < method.getParameters().length; i++) {
                		      sequellet+=" ("+ method.getParameters()[i].getType().getSimpleName()+" "+
                	        method.getParameters()[i].getName()+"";

                	   }
                		sequellet+=") \n";
					}else {
						
						sequellet+="() \n";

					}
                	   
                   }

				
					sequellet+=" \n  classes internes :";
					Class <?> classes[]=c3.getClasses();
						
						for (Class classe : classes) {
							sequellet+=classe.getSimpleName();
		                
		                	   
		                   }
						sequellet+=" \n";
					  int x= c3.getModifiers();
						
						
							sequellet+="  "+Modifier.toString(x)+" {} ";
						

						sequellet+=" \n } \n";
						
				        sequellet+="Nombres des proprités : "+numberOfFileds+" . \n Nombres des méthodes :"+numberOfMethods;
//                Form form =new Form("Squelette de classe", 150);
//      			form.addTextArea(sequellet);
//      		    JFrame jFrame =new JFrame();
//      		    jFrame.add(form);
//      		    jFrame.setContentPane(form);
//      		    jFrame.pack();
//      		    jFrame.setVisible(true);
				        return sequellet;
				
	}
    
	public String getSupperClass(Class<?> myClass) {
		String name="";
		
		if(myClass.getSuperclass()!=null) {
			if(myClass.getSuperclass().getSuperclass()!=null) {
				name+=getSupperClass(myClass.getSuperclass())+"->";
			}
			name+=myClass.getSuperclass().getSimpleName();
		}
		return name;
				
	}
	 
	public static  void getAllPackages() {
		Package[] pa = Package.getPackages();
		for (int i = 0; i < pa.length; i++) {
		    Package p = pa[i];
		    System.out.println("\"" + p.getName() + "\", ");
		}
	}
}
