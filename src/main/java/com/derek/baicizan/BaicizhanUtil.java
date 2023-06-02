package com.derek.baicizan;

import com.derek.baicizan.model.WordItem;
import com.derek.baicizan.model.WordList;
import com.google.gson.Gson;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jsoup.internal.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author derek zhan
 * @date 2023/6/1 19:01:56
 */
public class BaicizhanUtil {

    /**
     * 已背诵单词索引
     */
    public static int index = 0;

    public static void getContentAndSengNotify(Project project) {
        InputStream is = BaicizhanUtil.class.getClassLoader().getResourceAsStream("list.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String response = null;
        try {
            response = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!StringUtil.isBlank(response)) {
            Gson gson = new Gson();
            WordList wordList = gson.fromJson(response, WordList.class);
            if (wordList != null) {
                String item = wordList.getList().get(index);
                String word = HttpClient.doGet("https://cdn.jsdelivr.net/gh/lyc8503/baicizhan-word-meaning-API/data/words/" + item + ".json");
                if (!StringUtil.isBlank(word)) {
                    System.out.println(word);
                    WordItem wordItem = gson.fromJson(word, WordItem.class);
                    final Notification notification = new Notification("ProjectOpenNotification", "",
                            wordItem.toString(), NotificationType.INFORMATION);
                    notification.notify(project);
                    //notification.expire();
                    index++;
                }
            }
        }
    }
}

