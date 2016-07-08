package com.mobtion.materialdemo.com.mobtion.materialdemo.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mobtion.materialdemo.MainAbsFragment;
import com.mobtion.materialdemo.MainAbsFragmentActivity;
import com.mobtion.materialdemo.R;
import com.mobtion.materialdemo.com.mobtion.materialdemo.menu.MainMenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends MainAbsFragment {

    private EditText editTextName;
    private EditText editTextPassword;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutPassword;
    private Button loginButton;
    private TextView editPasswordLabel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        editTextName = (EditText) view.findViewById(R.id.editTextName);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
        textInputLayoutName = (TextInputLayout) view.findViewById(R.id.widgetEditTextName);
        textInputLayoutPassword = (TextInputLayout) view.findViewById(R.id.widgetEditTextPassword);
        loginButton = (Button) view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(LoginOnClickListener);

        editPasswordLabel = (TextView) view.findViewById(R.id.editPasswordLabel);

        editPasswordLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainAbsFragmentActivity)getActivity()).addFragmentToStack(new ChangePasswordFragment());
            }
        });

        return view;
    }

    View.OnClickListener LoginOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {

//            if(editTextName.getText().toString().isEmpty()) {
//
//                textInputLayoutName.setError("Usuario inválido");
//
//            } else if(editTextPassword.getText().toString().isEmpty()) {
//
//                textInputLayoutPassword.setError("Contraseña inválida");
//
//            } else {
                OpenMainContent();
//                //Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//            }
        }
    };

    private void textViewEventsListener() {
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
