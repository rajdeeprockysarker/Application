package com.raj.application.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.raj.application.R;
import com.raj.application.db.User;
import com.raj.application.OnRecyclerItemClickListener;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private List<User> usersList;

    private OnRecyclerItemClickListener minter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);

        }
    }


    public UserListAdapter(OnRecyclerItemClickListener minter, List<User> usersList) {
        this.usersList = usersList;
        this.minter=minter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        User user = usersList.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("pos","poos"+position);
                minter.onClickItem(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

}
