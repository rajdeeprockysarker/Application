package com.raj.application;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.raj.application.ViewModel.ListFragmentViewModel;
import com.raj.application.adapter.UserListAdapter;
import com.raj.application.db.User;

import java.util.ArrayList;


public class AddEditFragment extends Fragment {

    private ListFragmentViewModel listFragmentViewModel;
    private EditText edt_name,edt_email;
    private Button btn_add_edit;
    private boolean update=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int value = getArguments().getInt("YourKey");
        return inflater.inflate(R.layout.fragment_add_edit, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listFragmentViewModel=new ViewModelProvider(getActivity()).get(ListFragmentViewModel.class);

        edt_name =(EditText)getActivity().findViewById(R.id.edt_name);
        edt_email =(EditText)getActivity().findViewById(R.id.edt_email);
        btn_add_edit=(Button)getActivity().findViewById(R.id.btn_add_edit);


        listFragmentViewModel.userList.observe(this, new Observer() {
            @Override
            public void onChanged(Object userList) {
                Log.v("","");
                if(update)
               ((MainActivity)getActivity()).jumpListFragment();


            }
        });

        btn_add_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(edt_name.getText().toString());
                user.setEmail(edt_email.getText().toString());
                update=true;
                listFragmentViewModel.setUserId(user);
            }
        });

    }
}