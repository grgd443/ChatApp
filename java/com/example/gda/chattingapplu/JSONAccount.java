package com.example.gda.chattingapplu;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gda on 10/26/2017.
 */

public class JSONAccount extends JSONObject {


    public JSONObject makeJSONObject(String name, String lname, String userName, String password, String email, String gender, String dateOfBirth, String profilPic) {

        JSONObject obj = new JSONObject();

        try {
            obj.put("firstName", name);
            obj.put("lastName", lname);


            obj.put("username", userName);
            obj.put("password", password);

            obj.put("email", email);
            obj.put("gender", gender);

            obj.put("DOB", dateOfBirth);

            if (profilPic != null)
                obj.put("profilePicture", profilPic);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }


}

