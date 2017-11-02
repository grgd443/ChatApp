package com.example.gda.chattingapplu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import layout.SignUpAccountInformationFragment;

public class SignUpActivity extends AppCompatActivity {

    private final int REQUST_CAPTURE = 1;
    private final int REQUST_GALLERY = 2;

    public final static int PICREQUIRED = 1;
    public final static int PICNOTREQUIRED = 0;

    private final int pxCropWidth = 400, pxCropHeight = 400;

    private String imagePath = null;

    private ImageButton captureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        if (savedInstanceState == null) {

            SignUpAccountInformationFragment signeUpFragment = new SignUpAccountInformationFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.add(R.id.signUpFrameLayout, signeUpFragment);
            ft.commit();


        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case REQUST_CAPTURE:
                if (resultCode == RESULT_OK) {

                    try {
                        captureBtn = (ImageButton) findViewById(R.id.icon_IBtn);


                        Uri imgUri = Uri.parse(imagePath);

                        Bitmap image = processImage(imgUri);

                        captureBtn.setImageBitmap(image);

                        saveProcessedImage(image, imgUri.getPath());


                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                break;


            case REQUST_GALLERY:
                if (resultCode == RESULT_OK && data != null) {

                    Uri imgUri = data.getData();
                    try {

                        captureBtn = (ImageButton) findViewById(R.id.icon_IBtn);

                        InputStream is = getContentResolver().openInputStream(imgUri);

                        Bitmap img = BitmapFactory.decodeStream(is);


                        captureBtn.setImageBitmap(ThumbnailUtils.extractThumbnail(img, pxCropWidth, pxCropHeight));


                        imagePath = imgUri.toString();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                }


                break;

        }


    }


    public void replaceFragment(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.signUpFrameLayout, frag);
        ft.addToBackStack(null);
        ft.commit();

    }


    public void captureImage() {

        Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (capture.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {

                Uri photoUri = FileProvider.getUriForFile(this, "com.example.android.chatappfileprovider", photoFile);
                capture.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(capture, REQUST_CAPTURE);
            }

        }


    }

    public void getImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUST_GALLERY);

    }


    private void saveProcessedImage(Bitmap image, String path) throws IOException {

        FileOutputStream fout = new FileOutputStream(path);
        image.compress(Bitmap.CompressFormat.JPEG, 85, fout);
        fout.flush();
        fout.close();

        Toast.makeText(this, "Saved.", Toast.LENGTH_LONG).show();
    }

    private Bitmap processImage(Uri imageUri) throws IOException {

        Bitmap img = ThumbnailUtils.extractThumbnail(MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri), pxCropWidth, pxCropHeight);

        Matrix m = new Matrix();
        m.postRotate(-90);
        return Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), m, true);

    }


    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PP_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imagePath = "file:" + image.getAbsolutePath();

        return image;


    }

    public boolean saveToJASON(String fname, String lname, String username, String password,
                               String email, String gender, String dateOfBirth, int option) {

        switch (option) {

            case PICREQUIRED:
                if (imagePath == null)
                    return false;
                break;
            case PICNOTREQUIRED:
                if (imagePath != null)
                    imagePath = null;
                break;


        }


        JSONAccount obj = new JSONAccount();
        JSONObject jsonObject = obj.makeJSONObject(fname, lname,
                username, password, email,
                gender, dateOfBirth, imagePath);


        try {

            File storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

            String fileName = "account.json";

            FileWriter writer = new FileWriter(storageDir.getAbsolutePath().toString() + "/account.json");
            writer.write(jsonObject.toString());
            writer.flush();
            writer.close();


            Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return true;
    }
}




