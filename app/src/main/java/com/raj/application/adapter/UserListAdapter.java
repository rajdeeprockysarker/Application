package com.raj.application.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.AdapterViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.raj.application.R;
import com.raj.application.databinding.ListRowBinding;
import com.raj.application.db.User;
import com.raj.application.contract.OnRecyclerItemClickListener;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private List<User> usersList;

    private OnRecyclerItemClickListener minter;

    ListRowBinding listRowBinding;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email;
        public LinearLayout lin_container;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            lin_container = (LinearLayout) view.findViewById(R.id.lin_container);

        }
    }


    public UserListAdapter(OnRecyclerItemClickListener minter, List<User> usersList) {
        this.usersList = usersList;
        this.minter=minter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        listRowBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_row, parent, false);
        View view = listRowBinding.getRoot();
        listRowBinding=DataBindingUtil.bind(view);
        listRowBinding.setUser(new User());
        listRowBinding.setUserListAdapter(this);
        listRowBinding.setView(view);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final User user = usersList.get(position);

        listRowBinding.setUser(user);
//        holder.lin_container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.v("pos","poos"+position);
//                minter.onClickItem(user.getUid());
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void onContainerClick(View view,User mUser){
        minter.onClickItem(mUser.getUid());
    }

}
