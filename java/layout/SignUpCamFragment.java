package layout;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.gda.chattingapplu.ContactsActivity;
import com.example.gda.chattingapplu.JSONAccount;
import com.example.gda.chattingapplu.R;
import com.example.gda.chattingapplu.SignUpActivity;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;


public class SignUpCamFragment extends Fragment implements View.OnClickListener {

    private String fname,lname,username,password , gender,dOB , email , imgPath;

    private Button skipBtn, chooseFromGalBtn , next;
    private ImageButton captureBtn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sign_up_cam, container, false);

        getData(getArguments());
//Toast.makeText(getContext(),fname,Toast.LENGTH_SHORT).show();


        skipBtn = (Button) rootView.findViewById(R.id.skip_Btn);
        captureBtn = (ImageButton) rootView.findViewById(R.id.icon_IBtn);
        chooseFromGalBtn = (Button) rootView.findViewById(R.id.chooseFromGallery_Btn);
        next = (Button) rootView.findViewById(R.id.camNext);


        next.setOnClickListener(this);
        skipBtn.setOnClickListener(this);
        captureBtn.setOnClickListener(this);
        chooseFromGalBtn.setOnClickListener(this);


        return rootView;
    }

    private void getData(Bundle bundle) {
        fname = bundle.getString(SignUpAccountInformationFragment.FIRSTNAME);
        lname = bundle.getString(SignUpAccountInformationFragment.LASTNAME);
        email = bundle.getString(SignUpAccountInformationFragment.EMAIL);
        username = bundle.getString(SignUpAccountInformationFragment.USERNAME);
        password = bundle.getString(SignUpAccountInformationFragment.PASSWORD);
        gender = bundle.getString(SignUpAccountInformationFragment.GENDER);
        dOB = bundle.getString(SignUpAccountInformationFragment.DATEOFBIRTH);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.chooseFromGallery_Btn:
                ((SignUpActivity) getContext()).getImageFromGallery();
                break;

            case R.id.icon_IBtn:

                ((SignUpActivity) getContext()).captureImage();

                break;

            case R.id.skip_Btn:

                ((SignUpActivity) getContext()).saveToJASON(fname,lname,username,password,email,gender,dOB,SignUpActivity.PICNOTREQUIRED);
                startActivity(new Intent(getActivity(), ContactsActivity.class));
                break;
            case R.id.camNext:




                    if(!((SignUpActivity) getContext()).saveToJASON(fname,lname,username,password,email,gender,dOB,SignUpActivity.PICREQUIRED)){
                        Toast.makeText(getContext(),"Please Make sure you pick a picture before proceding!",Toast.LENGTH_LONG).show();
                    }else {
                        startActivity(new Intent(getActivity(), ContactsActivity.class));
                    }


                break;




        }


    }


}
