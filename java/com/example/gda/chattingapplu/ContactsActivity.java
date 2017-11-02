package com.example.gda.chattingapplu;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {


    private RecyclerView mRv;
    private TextView name, userName;
    private ImageButton icon;

    private ArrayList<Contact> contactsList;
    private String imagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactsList = new ArrayList<Contact>();

        name = (TextView) findViewById(R.id.Contacts_name);
        userName = (TextView) findViewById(R.id.Contacts_username);
        icon = (ImageButton) findViewById(R.id.Contacts_IBtn);

        JSONObject accountObj = UtilityClass.getJSONObject(this , UtilityClass.INTERNAL , "account.json");
        JSONObject contactsObj = UtilityClass.getJSONObject(this , UtilityClass.ASSETS , "contacts.json");


        if (contactsObj != null) {
            try {

                JSONArray jArray = contactsObj.getJSONArray("Contacts");

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    contactsList.add(new Contact(json_data.getInt("id"), json_data.getString("name")));

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (accountObj != null) {

            try {

                name.setText(accountObj.getString("firstName") + " " + accountObj.getString("lastName"));
                userName.setText(accountObj.getString("username"));

                if (accountObj.has("profilePicture")) {


                    Uri imgUri = Uri.parse(accountObj.getString("profilePicture"));

                    Bitmap img = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);

                    icon.setImageBitmap(ThumbnailUtils.extractThumbnail(img, 100, 100));


                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        ItemAdapter itemAdapter = new ItemAdapter(this, contactsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(itemAdapter);


    }

}