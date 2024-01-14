package org.mql.java.dataStructure;

import java.util.List;

public class Package {

	private String packageName;
    private List<ClassModel> classes;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<ClassModel> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassModel> classes) {
        this.classes = classes;
    }

}
