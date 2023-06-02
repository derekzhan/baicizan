package com.derek.baicizan;

/**
 * @author derek zhan
 * @date 2023/6/2 14:10:31
 */

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;

public class PersistenceUtil {
    public static PropertiesComponent applicationComponent = PropertiesComponent.getInstance();

    public static String getValue(String key) {
        return applicationComponent.getValue(key);
    }

    public static void saveValue(String key, String value){
        applicationComponent.setValue(key, value);
    }

    public static String getValue(Project project, String key) {
        PropertiesComponent projectComponent = PropertiesComponent.getInstance(project);
        return projectComponent.getValue(key);
    }

}

