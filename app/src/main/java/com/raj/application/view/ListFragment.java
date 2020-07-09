package com.raj.application.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.raj.application.contract.OnRecyclerItemClickListener;
import com.raj.application.R;
import com.raj.application.ViewModel.ListFragmentViewModel;

import com.raj.application.adapter.UserListAdapter;
import com.raj.application.databinding.FragmentListBinding;
import com.raj.application.db.AppDatabase;
import com.raj.application.db.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListFragment extends Fragment implements OnRecyclerItemClickListener {


    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserListAdapter mAdapter;
    private ListFragmentViewModel listFragmentViewModel;
    private FloatingActionButton fab_add;
    private AppDatabase db;
    public FragmentListBinding fragmentListBinding;

    public ListFragment(AppDatabase db) {
        this.db = db;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //  return inflater.inflate(R.layout.fragment_list, container, false);

        fragmentListBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);
        View view = fragmentListBinding.getRoot();
        fragmentListBinding = DataBindingUtil.bind(view);
        fragmentListBinding.setListFragment(this);
        fragmentListBinding.setView(view);
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listFragmentViewModel = new ViewModelProvider(getActivity()).get(ListFragmentViewModel.class);


        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        fab_add = (FloatingActionButton) getActivity().findViewById(R.id.fab_add);

        mAdapter = new UserListAdapter(this, userList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        listFragmentViewModel.userList.observe(this, new Observer() {
            @Override
            public void onChanged(Object userList) {
                Log.v("", "");

                ArrayList<User> mUlerListInsideObserver = (ArrayList<User>) userList;
                mAdapter = new UserListAdapter(ListFragment.this, mUlerListInsideObserver);
                recyclerView.setAdapter(mAdapter);
            }
        });

        listFragmentViewModel.userInitialList.observe(this, new Observer() {
            @Override
            public void onChanged(Object userList) {
                Log.v("", "");

                ArrayList<User> mUlerListInsideObserver = (ArrayList<User>) userList;
                mAdapter = new UserListAdapter(ListFragment.this, mUlerListInsideObserver);
                recyclerView.setAdapter(mAdapter);
            }
        });

        listFragmentViewModel.getInitialData(true, db);



    }


    @Override
    public void onClickItem(int position) {
        ((MainActivity) getActivity()).jumpAddEditFragment(position);
    }


    public void onClickFloatingButton(View view) {
        ((MainActivity) getActivity()).jumpAddEditFragment(-999);
    }
}