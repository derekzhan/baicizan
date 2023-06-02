package com.derek.baicizan;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author derek zhan
 * @date 2023/6/2 11:46:27
 */
public class ProjectStatusListener implements ProjectManagerListener {

    @Override
    public void projectClosingBeforeSave(@NotNull Project project) {
        //System.out.println("projectClosingBeforeSave");
        ProjectManagerListener.super.projectClosingBeforeSave(project);
        try {
            BaicizhanUtil.saveIndex();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void projectOpened(@NotNull Project project) {
        ProjectManagerListener.super.projectOpened(project);
        try {
            BaicizhanUtil.readIndex();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
