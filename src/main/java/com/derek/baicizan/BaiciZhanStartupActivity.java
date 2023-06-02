package com.derek.baicizan;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author derek zhan
 * @date 2023/6/1 18:51:56
 */
public class BaiciZhanStartupActivity implements StartupActivity {
    @Override
    public void runActivity(@NotNull Project project) {

        try {
            BaicizhanUtil.getContentAndSengNotify(project);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
