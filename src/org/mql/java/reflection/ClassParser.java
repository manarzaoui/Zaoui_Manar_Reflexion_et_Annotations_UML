package org.mql.java.reflection;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mql.java.dataStructure.Projet;
import org.mql.java.dataStructure.Package;
import org.mql.java.dataStructure.ClassModel;
import org.mql.java.dataStructure.Relation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class ClassParser {

	
	public Field[] getFields(String nomQ) {
		
		try {
			 Class<?> c3= Class.forName(nomQ);
			 Field[] fields=c3.getDeclaredFields();
              return fields;
			
		} catch (Exception e) {
			 System.out.println("Exception "+e.getMessage());
			 return null;
		}
		   
		 
	}
	public Method[] getMethods(String nomQ) {
			
			try {
				 Class<?> c3= Class.forName(nomQ);
				 Method[] methods=c3.getDeclaredMethods();
	              return methods;
				
			} catch (Exception e) {
				 System.out.println("Exception "+e.getMessage());
				 return null;
			}
			   
			 
	}
	
	
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



	



	


	public static void persistProjet(Map<String, List<Class>> classes, Map<String, List<Class>> interfaces, String outputPath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Projet");
            doc.appendChild(rootElement);

            for (Map.Entry<String, List<Class>> entry : classes.entrySet()) {
                Element packageElement = doc.createElement("Package");
                rootElement.appendChild(packageElement);

                Element packageNameElement = doc.createElement("name");
                packageNameElement.appendChild(doc.createTextNode(entry.getKey()));
                packageElement.appendChild(packageNameElement);

                for (Class<?> classA : entry.getValue()) {
                    Element classModelElement = doc.createElement("Class");
                    packageElement.appendChild(classModelElement);

                    Element classNameElement = doc.createElement("name");
                    classNameElement.appendChild(doc.createTextNode(classA.getSimpleName()));
                    classModelElement.appendChild(classNameElement);

                    Element relationshipsElement = doc.createElement("Relations");
                    classModelElement.appendChild(relationshipsElement);

                    for (Class<?> classB : entry.getValue()) {
                        if (classA != classB) {
                            String relationshipType = getRelationshipType(classA, classB);
                            if (relationshipType != null) {
                                Element relationElement = doc.createElement("relation");
                                relationshipsElement.appendChild(relationElement);

                                Element relationshipTypeElement = doc.createElement("typeRelation");
                                relationshipTypeElement.appendChild(doc.createTextNode(relationshipType));
                                relationElement.appendChild(relationshipTypeElement);

                                Element relatedClassElement = doc.createElement("classeReliee");
                                relatedClassElement.appendChild(doc.createTextNode(classB.getSimpleName()));
                                relationElement.appendChild(relatedClassElement);
                            }
                        }
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(outputPath));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static List<String> extractAnnotations(List<String> packages, String path) throws ClassNotFoundException {
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
					
                	if(externalClass.isAnnotation() ) {
                        classesInPackage.add(file.getName().replace(".class", ""));

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
	public static Map<String, List<Class>> extractInterfaces(List<String> packages, String path) throws ClassNotFoundException {
        Map<String, List<Class>> classesByPackages = new HashMap<>();
        URL binUrl;
		try {
			binUrl = new File(path).toURI().toURL();
		
    
        URLClassLoader classLoader = new URLClassLoader(new URL[]{binUrl});
		
        
        
        for (String mypackage : packages) {
            String pack = mypackage.replace('.', '\\');
            File dir = new File(path + "\\" + pack);
            File classes[] = dir.listFiles();
            List<Class> classesInPackage = new ArrayList<>();

            if (classes != null) {
                for (File file : classes) {
                	
                		Class<?> externalClass=classLoader.loadClass(mypackage+"."+file.getName().replace(".class", ""));
					
                	if(externalClass.isInterface()) {
                        classesInPackage.add(externalClass);

                	}
                }
            }

            classesByPackages.put(mypackage, classesInPackage);
        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        return classesByPackages;
    }
	public static Map<String, List<Class>>  extractClasses(List<String> packages, String path) throws ClassNotFoundException {
        Map<String, List<Class>> classesByPackages = new HashMap<>();
        URL binUrl;
		try {
			binUrl = new File(path).toURI().toURL();
		
    
        URLClassLoader classLoader = new URLClassLoader(new URL[]{binUrl});
		
        
        for (String mypackage : packages) {
            String pack = mypackage.replace('.', '\\');
            File dir = new File(path + "\\" + pack);
            File classes[] = dir.listFiles();
            List<Class> classesInPackage = new ArrayList<>();

            if (classes != null) {
                for (File file : classes) {
                	
                		Class<?> externalClass=classLoader.loadClass(mypackage+"."+file.getName().replace(".class", ""));
					
                	if(!externalClass.isAnnotation() && !externalClass.isInterface()) {
                        classesInPackage.add(externalClass);

                	}
                }
            }

            classesByPackages.put(mypackage, classesInPackage);
        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        List<String> result = new ArrayList<>();
//        for (Map.Entry<String, List<String>> entry : classesByPackages.entrySet()) {
//            StringBuilder packageInfo = new StringBuilder(entry.getKey() + ": ");
//            for (String className : entry.getValue()) {
//            	
//                packageInfo.append(className).append(", ");
//            }
//
//            result.add(packageInfo.substring(0, packageInfo.length() - 2));
//        }

        return classesByPackages;
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
	
	
	public static List<String> extractRelations(Map<String, List<Class>> classes,Map<String, List<Class>> interfaces) {
	    List<String> relationships = new ArrayList<>();

	    for (Map.Entry<String, List<Class>> entry : classes.entrySet()) {
	        StringBuilder packageInfo = new StringBuilder("");

	        for (Class<?> classA : entry.getValue()) {
                String implementsInterface = implementsInterface(classA, interfaces);
                if (implementsInterface!=null) {
                    relationships.add(classA.getSimpleName() + " implemente l'interface "+implementsInterface);
                }
	            for (Class<?> classB : entry.getValue()) {
	                if (classA != classB) {
	                	 String relationshipType = getRelationshipType(classA, classB);

	                        if (relationshipType != null) {
	                            relationships.add(classA.getSimpleName() + " a " + relationshipType + "  avec   " + classB.getSimpleName());
	                        }

	                       
	                    
	                    
                     
	                }
	            }
	        }
	        relationships.add(packageInfo.toString());
	    }

	    return relationships;
	}

	
	
	  private static String getRelationshipType(Class<?> classA, Class<?> classB) {
	        if (isAssociation(classA, classB)) {
	            return "composition normale";
	        } else if (isAgregation(classA, classB)) {
	            return "agregation";
	        } else if (isInheritance(classA, classB)) {
	            return "inheritance";
	        }
	        return null;
	    }

	    private static boolean isAssociation(Class<?> classA, Class<?> classB) {
	    	 

	           for (Field fieldd : classA.getDeclaredFields()) {
	            if (fieldd.getType().equals(classB)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    
	    private static boolean isAgregation(Class<?> classA, Class<?> classB) {
	        for (Field field : classA.getDeclaredFields()) {
	            if (isListOfClass(field, classB)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    private static boolean isListOfClass(Field field, Class<?> targetClass) {
	        if (field.getType() == List.class && field.getGenericType() instanceof ParameterizedType) {
	            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
	            Type[] typeArguments = parameterizedType.getActualTypeArguments();
	            if (typeArguments.length == 1 && typeArguments[0] instanceof Class) {
	                Class<?> listElementType = (Class<?>) typeArguments[0];
	                return listElementType.equals(targetClass);
	            }
	        }
	        return false;
	    }

	    private static boolean isComposition(Field field, Class<?> classB) {
	        if (field.getType().equals(classB)) {
	            return true;
	        }
	        return false;
	    }
	  
	    private static boolean isInheritance(Class<?> classA, Class<?> classB) {
	        return classA.isAssignableFrom(classB) || classB.isAssignableFrom(classA);
	    }
	    
	    
	    private static String implementsInterface(Class<?> myClass, Map<String, List<Class>> interfaces) {
	        Class<?>[] interfacesOfClass = myClass.getInterfaces();

	        for (Class<?> interfaceClass : interfacesOfClass) {
	        	for (Map.Entry<String, List<Class>> entry : interfaces.entrySet()) {
	    	        for (Class<?> classA : entry.getValue()) {
	    	        	 if (classA.equals(interfaceClass)) {
	    	        		 return interfaceClass.getSimpleName();
	    	        	 }	 
	    	        }
	        		              
	                   
	                }
	            }
	        

	        Class<?> superClass = myClass.getSuperclass();
	        if (superClass != null) {
	            return implementsInterface(superClass, interfaces);
	        }

	        return null;
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
