package org.java.mql.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.mql.java.reflection.ClassParser;

public class ClassPanel extends JPanel {
    private Class<?> classModel;
    
    public ClassPanel(Class<?> classModel) {
        this.classModel = classModel;
        setBackground(Color.white);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 

        add(createClassNamePanel());
        add(createFieldsPanel());
        add(createMethodsPanel());
    }

    private JPanel createClassNamePanel() {
        JPanel panel = new JPanel();
        JLabel label= new JLabel( classModel.getSimpleName());
        if(classModel.isInterface()) {
            label = new JLabel("<<interface>>  \n "+ classModel.getSimpleName());
            panel.setPreferredSize(new Dimension(200, 50)); 

        }
        panel.setPreferredSize(new Dimension(200, 30)); 
         panel.setBackground(Color.white);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setBorder(new EmptyBorder(5, 5, 5, 5)); 

        panel.add(label);
        return panel;
    }

    private JPanel createFieldsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.white);

        Field[] fields = ClassParser.getFields(classModel.getName());
       if(fields.length==0) {
    	   System.out.println(classModel.getName());
    	   JLabel label = new JLabel("");
           label.setPreferredSize(new Dimension(200, 20)); 
           label.setBorder(new EmptyBorder(5, 5, 5, 5)); 
           panel.setBorder(BorderFactory.createLineBorder(Color.black));
           panel.add(label);
       }else {
    	     for (Field field : fields) {
            String fieldInfo = Modifier.toString(field.getModifiers())=="public"?"+":"-"+" "+simplifyTypeName(field.getGenericType(), field) + " " + field.getName();
            JLabel label = new JLabel(fieldInfo);
            label.setPreferredSize(new Dimension(200, 20)); 

            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            label.setBorder(new EmptyBorder(5, 5, 5, 5)); 

            panel.add(label);
        }
       }
      

        return panel;
    }
    @Override
    protected void paintComponent(Graphics g) {

      
    }

    private JPanel createMethodsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.white);

        Method[] methods = ClassParser.getMethods(classModel.getName());
       java.util.List<String> params=new ArrayList<>();
       if(methods.length==0) {
    	   System.out.println(classModel.getName());
    	   JLabel label = new JLabel("");
           label.setPreferredSize(new Dimension(200, 20)); 
           label.setBorder(new EmptyBorder(5, 5, 5, 5)); 
           panel.setBorder(BorderFactory.createLineBorder(Color.black));
           panel.add(label);
       }else {
       for (Method method : methods) {
           if (method.getParameterCount() != 0) {
               for (int i = 0; i < method.getParameters().length; i++) {
                   params.add(method.getParameters()[i].getType().getSimpleName() + " "
                           + method.getParameters()[i].getName());
               }
           }
           JLabel label = new JLabel( (Modifier.toString(method.getModifiers()).equals("private") ? "-" : "+") +" "+(method.getReturnType().getSimpleName() + " " + " " + method.getName()
           + (params.isEmpty() ? "()" : "(" + String.join(", ", params) + ")")));

           params.clear();

           label.setPreferredSize(new Dimension(200, 20));

            // Customize appearance
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            label.setBorder(new EmptyBorder(5, 5, 5, 5)); 

            panel.add(label);
        }
       }
        return panel;
    }

    private String simplifyTypeName(Type type, Field field) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            String rawType = parameterizedType.getRawType().getTypeName();
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            StringBuilder simplifiedType = new StringBuilder(rawType);

            simplifiedType.append("<");
            for (int i = 0; i < typeArguments.length; i++) {
                if (i > 0) {
                    simplifiedType.append(", ");
                }
                simplifiedType.append(simplifyTypeName(typeArguments[i], field));
            }
            simplifiedType.append(">");

            return simplifiedType.toString().replaceAll("java\\.util\\.", "");
        } else if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            if (clazz.isArray()) {
                return simplifyTypeName(clazz.getComponentType(), field) + "[]";
            } else {
                return clazz.getSimpleName().replaceAll("java\\.lang\\.", "");
            }
        } else {
            return type.getTypeName().replaceAll("java\\.lang\\.", "");
        }
    }


   
}
