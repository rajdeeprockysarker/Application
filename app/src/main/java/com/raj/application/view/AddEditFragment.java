package com.raj.application.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.raj.application.R;
import com.raj.application.ViewModel.AddEditFragmentViewModel;
import com.raj.application.databinding.FragmentAddEditBinding;
import com.raj.application.db.AppDatabase;
import com.raj.application.db.User;


public class AddEditFragment extends Fragment {

    private AddEditFragmentViewModel listFragmentViewModel;
    private EditText edt_name,edt_email;
    private Button btn_add_edit;
    private boolean update=false;
    private boolean forADD=false;
    private boolean forUpdate=false;
    private int value;
    private User mUser;
    private boolean edit=false;
    FragmentAddEditBinding binding;

    private AppDatabase db;

    public AddEditFragment(AppDatabase db) {
        this.db=db;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         value = getArguments().getInt("position");
        if(value!=-999){
            edit=true;
        }
        mUser=new User();
        mUser.setEmail("");
        mUser.setName("");
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_edit, container, false);
        View view = binding.getRoot();
        binding.setUser(mUser);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listFragmentViewModel=new ViewModelProvider(getActivity()).get(AddEditFragmentViewModel.class);

        edt_name =(EditText)getActivity().findViewById(R.id.edt_name);
        edt_email =(EditText)getActivity().findViewById(R.id.edt_email);
        btn_add_edit=(Button)getActivity().findViewById(R.id.btn_add_edit);

        if(edit){
            btn_add_edit.setText("Update");
        }
        else{
            btn_add_edit.setText("Add");
        }


        listFragmentViewModel.userList.observe(this, new Observer() {
            @Override
            public void onChanged(Object userList) {
                Log.v("","");
                mUser=new User();

                if(update)
               ((MainActivity)getActivity()).jumpListFragment();

            }
        });

        listFragmentViewModel.user.observe(this, new Observer() {
            @Override
            public void onChanged(Object userObj) {
                Log.v("","");
                if(edit) {
                    mUser = (User) userObj;
                    binding.setUser(mUser);
                }
            }
        });

        btn_add_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUser.setName(edt_name.getText().toString());
                mUser.setEmail(edt_email.getText().toString());
                update=true;
                listFragmentViewModel.setUser(mUser,db);
            }
        });

        if(edit){
            listFragmentViewModel.setUserIdForEditUser(value,db);
        }

    }
}