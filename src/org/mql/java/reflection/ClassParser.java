package org.mql.java.reflection;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public static List<String> extractAnnotations(List<String> packages, String path) throws ClassNotFoundException {
        Map<String, List<String>> classesByPackages = new HashMap<>();
        URL binUrl;
		try {
			binUrl = new File(path).toURI().toURL();
		
    
        // Create a class loader with the bin directory URL
        URLClassLoader classLoader = new URLClassLoader(new URL[]{binUrl});
		
        // Load a class from the bin directory
        
        
        for (String mypackage : packages) {
            String pack = mypackage.replace('.', '\\');
            File dir = new File(path + "\\" + pack);
            File classes[] = dir.listFiles();
            List<String> classesInPackage = new ArrayList<>();

            if (classes != null) {
                for (File file : classes) {
                	
                		Class<?> externalClass=classLoader.loadClass(mypackage+"."+file.getName().replace(".class", ""));
					
                	if(externalClass.isAnnotation() ) {
                        classesInPackage.add(file.getName());

                	}
                }
            }

            classesByPackages.put(mypackage, classesInPackage);
        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : classesByPackages.entrySet()) {
            StringBuilder packageInfo = new StringBuilder(entry.getKey() + ": ");
            for (String className : entry.getValue()) {
            	
                packageInfo.append(className).append(", ");
            }
            // Remove the trailing ", " and add to the result list
            result.add(packageInfo.substring(0, packageInfo.length() - 2));
        }

        return result;
    }
	public static List<String> extractInterfaces(List<String> packages, String path) throws ClassNotFoundException {
        Map<String, List<String>> classesByPackages = new HashMap<>();
        URL binUrl;
		try {
			binUrl = new File(path).toURI().toURL();
		
    
        // Create a class loader with the bin directory URL
        URLClassLoader classLoader = new URLClassLoader(new URL[]{binUrl});
		
        // Load a class from the bin directory
        
        
        for (String mypackage : packages) {
            String pack = mypackage.replace('.', '\\');
            File dir = new File(path + "\\" + pack);
            File classes[] = dir.listFiles();
            List<String> classesInPackage = new ArrayList<>();

            if (classes != null) {
                for (File file : classes) {
                	
                		Class<?> externalClass=classLoader.loadClass(mypackage+"."+file.getName().replace(".class", ""));
					
                	if(externalClass.isInterface()) {
                        classesInPackage.add(file.getName());

                	}
                }
            }

            classesByPackages.put(mypackage, classesInPackage);
        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : classesByPackages.entrySet()) {
            StringBuilder packageInfo = new StringBuilder(entry.getKey() + ": ");
            for (String className : entry.getValue()) {
            	
                packageInfo.append(className).append(", ");
            }
            // Remove the trailing ", " and add to the result list
            result.add(packageInfo.substring(0, packageInfo.length() - 2));
        }

        return result;
    }
	public static List<String> extractClasses(List<String> packages, String path) throws ClassNotFoundException {
        Map<String, List<String>> classesByPackages = new HashMap<>();
        URL binUrl;
		try {
			binUrl = new File(path).toURI().toURL();
		
    
        URLClassLoader classLoader = new URLClassLoader(new URL[]{binUrl});
		
        
        for (String mypackage : packages) {
            String pack = mypackage.replace('.', '\\');
            File dir = new File(path + "\\" + pack);
            File classes[] = dir.listFiles();
            List<String> classesInPackage = new ArrayList<>();

            if (classes != null) {
                for (File file : classes) {
                	
                		Class<?> externalClass=classLoader.loadClass(mypackage+"."+file.getName().replace(".class", ""));
					
                	if(!externalClass.isAnnotation() && !externalClass.isInterface()) {
                        classesInPackage.add(file.getName());

                	}
                }
            }

            classesByPackages.put(mypackage, classesInPackage);
        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : classesByPackages.entrySet()) {
            StringBuilder packageInfo = new StringBuilder(entry.getKey() + ": ");
            for (String className : entry.getValue()) {
            	
                packageInfo.append(className).append(", ");
            }

            result.add(packageInfo.substring(0, packageInfo.length() - 2));
        }

        return result;
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
	 
	

	 public static List<String> getAllPackages(File projectURL) throws Exception {
	        String path = projectURL.getPath();
	        File projectDir = new File(path);
	        List<String> repositoriesWithFiles = new ArrayList<>();
	        scanPackages(projectDir, "", repositoriesWithFiles);

	        return repositoriesWithFiles;
	    }

	  private static void scanPackages(File directory, String parentPackage, List<String> repositoriesWithFiles) {
	        File[] files = directory.listFiles();

	        if (files != null) {
	            boolean hasFiles = false;

	            for (File file : files) {
	                if (file.isDirectory()) {
	                    hasFiles = false; 

	                    for (File subFile : file.listFiles()) {
	                        if (subFile.isFile()) {
	                            hasFiles = true;
	                            break; 
	                        }
	                    }

	                    if (hasFiles) {
	                        String repositoryPath = parentPackage + file.getName();
	                     
	                        repositoriesWithFiles.add(repositoryPath);
	                    }

	                    scanPackages(file, parentPackage + file.getName() + ".", repositoriesWithFiles);
	                }
	            }
	        }
	    }


         

}
