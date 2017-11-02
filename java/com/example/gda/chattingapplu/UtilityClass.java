package com.example.gda.chattingapplu;

import android.content.Context;
import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gda on 11/2/2017.
 */

public class UtilityClass {

    public static final int ASSETS = 0;
    public static final int INTERNAL = 1;


    public static JSONObject getJSONObject(Context context, int from, String fileName) {

        BufferedReader reader = null;

        try {


            switch (from) {
                case ASSETS:

                    reader = new BufferedReader(
                            new InputStreamReader(context.getAssets().open(fileName)));
                    break;

                case INTERNAL:
                    File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                    File data = new File(storageDir.getAbsolutePath().toString() + "/" + fileName);
                    reader = new BufferedReader(new FileReader(data));

                    break;

            }


            StringBuilder stringBuilder = new StringBuilder();
            String mLine;

            while ((mLine = reader.readLine()) != null)
                stringBuilder.append(mLine);

            reader.close();


            return new JSONObject(new String(stringBuilder));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static String readFile(BufferedReader reader) {

        StringBuilder stringBuilder = new StringBuilder();
        String mLine;
        try {

            while ((mLine = reader.readLine()) != null)
                stringBuilder.append(mLine);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(stringBuilder);
    }


}
