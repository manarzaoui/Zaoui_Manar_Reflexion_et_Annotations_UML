package org.mql.java.dataStructure;

import java.util.List;

public class ClassModel {

	private String className;
    private List<Relation> relationships;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Relation> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relation> relationships) {
        this.relationships = relationships;
    }
}
