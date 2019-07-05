package com.example.realmassignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private RealmResults<Student> mPersonRealmResults;
    private Context mContext;
    private static final String TAG = "Experiment";
    private int count;

    public MyAdapter(RealmResults<Student> persons, Context context){
        mPersonRealmResults = persons;
        mContext = context;
        count = 0;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        Log.i(TAG, "onCreateViewHolder: Triggered And Also the Parent Id is " + parent.getId());
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: Triggered");
        Student student = mPersonRealmResults.get(position);
        holder.name.setText(student.getName());
        holder.dept.setText(student.getDept());
        holder.roll.setText(String.valueOf(student.getRoll()));
        holder.phone.setText(student.getPhone());
        if(student.isGender())
            holder.gender.setText("Male");
        else
            holder.gender.setText("Female");

        if(holder.dept.getText().toString().equalsIgnoreCase("cse")){
            holder.hold.setBackgroundColor(0xff1faa00);
        }
        else if(holder.dept.getText().toString().equalsIgnoreCase("ece")){
            holder.hold.setBackgroundColor(0xffffa000);
        }
        else if(holder.dept.getText().toString().equalsIgnoreCase("ee")){
            holder.hold.setBackgroundColor(0xffab000d);
        }
        else if(holder.dept.getText().toString().equalsIgnoreCase("it")){
            holder.hold.setBackgroundColor(0xff1565c0);
        }
    }

    @Override
    public int getItemCount() {
        return mPersonRealmResults.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView dept;
        private TextView roll;
        private TextView phone;
        private TextView gender;
        private RelativeLayout hold;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            count++;
            Log.i(TAG, "MyViewHolder: Number of Active ViewHolders:" + count);
            name = itemView.findViewById(R.id.name);
            dept = itemView.findViewById(R.id.dept);
            roll = itemView.findViewById(R.id.roll);
            phone = itemView.findViewById(R.id.phone);
            gender = itemView.findViewById(R.id.gender);
            hold = itemView.findViewById(R.id.category_image);
        }
    }
}