package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.gda.chattingapplu.R;
import com.example.gda.chattingapplu.SignUpActivity;


public class SignUpAccountInformationFragment extends Fragment implements View.OnClickListener{

    final static String FIRSTNAME = "fn";
    final static String LASTNAME = "ln";
    final static String EMAIL = "e";
    final static String USERNAME = "un";
    final static String PASSWORD = "pw";
    final static String GENDER = "g";
    final static String DATEOFBIRTH = "dob";



    private Button next ;


    EditText name ,lname ,email,pass,user,date;
    RadioGroup rg;








    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sign_up_account_information, container, false);



        name = (EditText) rootView.findViewById(R.id.firstNameEt);
        lname = (EditText) rootView.findViewById(R.id.lastNameEt);
        email = (EditText) rootView.findViewById(R.id.emailEt);
        pass = (EditText) rootView.findViewById(R.id.passwordEt);
        user = (EditText) rootView.findViewById(R.id.usernameEt);
        date = (EditText) rootView.findViewById(R.id.dateOfBirthEt);

        rg = (RadioGroup) rootView.findViewById(R.id.radioGroup);




        next = rootView.findViewById(R.id.signeUpNext);

        next.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.signeUpNext:

                String gender = "";

                switch (rg.getCheckedRadioButtonId()){
                    case R.id.maleRb:
                        gender = "Male";
                        break;
                    case R.id.femaleRb:
                        gender = "Female";
                        break;

                }

                // fault control to do





                SignUpCamFragment cf = new SignUpCamFragment();
                Bundle args = new Bundle();
                args.putString(FIRSTNAME,name.getText().toString().trim());
                args.putString(LASTNAME,lname.getText().toString().trim());
                args.putString(EMAIL,email.getText().toString().trim());
                args.putString(USERNAME,user.getText().toString().trim());
                args.putString(PASSWORD,pass.getText().toString().trim());
                args.putString(DATEOFBIRTH,date.getText().toString().trim());
                args.putString(GENDER,gender);

                cf.setArguments(args);
                ((SignUpActivity) getContext()).replaceFragment(cf);


                break;

        }




    }
}
