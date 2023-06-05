package com.derek.baicizan;

import com.derek.baicizan.model.WordItem;
import com.derek.baicizan.model.WordList;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jsoup.internal.StringUtil;

import java.io.*;

/**
 * @author derek zhan
 * @date 2023/6/1 19:01:56
 */
public class BaicizhanUtil {

    /**
     * 已背诵单词索引
     */
    public static int index = 0;

    public static final String key = "wordIndex";

    public static WordList wordList = null;

    public static void readIndex() throws IOException {
        String wordIndex = PersistenceUtil.getValue(key);
        if (!StringUtil.isBlank(wordIndex)) {
            index = Integer.parseInt(wordIndex);
        }
        String response = getResourceFile("list.json");
        if (!StringUtil.isBlank(response)) {
            Gson gson = new Gson();
            wordList = gson.fromJson(response, WordList.class);
            // 超过了重置下
            if (wordList.getList().size() > index) {
                index = 0;
            }
        }
    }

    public static void saveIndex() throws IOException {
        PersistenceUtil.saveValue(key, index + "");
    }


    private static String getResourceFile(String fileName) throws IOException {
        String response = null;
        InputStream resourceAsStream = null;
        BufferedReader br = null;

        try {
            resourceAsStream = BaicizhanUtil.class.getClassLoader().getResourceAsStream(fileName);
            br = new BufferedReader(new InputStreamReader(resourceAsStream));
            response = br.readLine();
        } catch (Exception e) {

        } finally {
            if (br != null) {
                br.close();
            }
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        }
        return response;
    }

    public static void getContentAndSengNotify(Project project) throws IOException {
        if (wordList != null) {
            String item = wordList.getList().get(index);
            String fileName = "words/" + item.replace(" ", "_") + ".json";
            System.out.println(fileName);
            String word = getResourceFile(fileName);
            if (!StringUtil.isBlank(word)) {
                Gson gson = new Gson();
                try {
                    WordItem wordItem = gson.fromJson(word, WordItem.class);
                    final Notification notification = new Notification("ProjectOpenNotification", "",
                            wordItem.toString(), NotificationType.INFORMATION);
                    notification.notify(project);
                    //notification.expire();
                } catch (JsonSyntaxException jse) {
                    System.out.println(fileName + ", " + jse.getMessage());
                }
                index++;
            }
        }
    }

}

