package com.derek.baicizan;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author derek zhan
 * @date 2023/6/1 19:01:02
 */
public class BaicizhanAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        try {
            BaicizhanUtil.getContentAndSengNotify(project);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
