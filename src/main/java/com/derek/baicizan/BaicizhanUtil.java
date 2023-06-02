package com.derek.baicizan;

import com.derek.baicizan.model.WordItem;
import com.derek.baicizan.model.WordList;
import com.google.gson.Gson;
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
            if(wordList.getList().size() > index){
                index = 0;
            }
        }
    }

    public static void saveIndex() throws IOException {
        PersistenceUtil.saveValue(key, index + "");
    }


    private static String getResourceFile(String indexFile) throws IOException {
        InputStream resourceAsStream = BaicizhanUtil.class.getClassLoader().getResourceAsStream(indexFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
        String response = null;
        try {
            response = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
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
            String word = HttpClient.doGet("https://cdn.jsdelivr.net/gh/lyc8503/baicizhan-word-meaning-API/data/words/" + item + ".json");
            if (!StringUtil.isBlank(word)) {
                //System.out.println(word);
                Gson gson = new Gson();
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

