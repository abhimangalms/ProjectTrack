package com.abhimangalms.projecttrack;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList project_name, project_id, project_created;

    public CustomAdapter(Context context, Activity activity, ArrayList project_name, ArrayList project_id, ArrayList project_created) {
        this.context = context;
        this.activity = activity;
        this.project_name = project_name;
        this.project_id = project_id;
        this.project_created = project_created;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.mProjectName.setText(String.valueOf(project_name.get(position)));
        holder.mProjectId.setText(String.valueOf(project_id.get(position)));
        holder.mProjectCreated.setText(String.valueOf(project_created.get(position)));


    }

    @Override
    public int getItemCount() {
        return project_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView mProjectName, mProjectId, mProjectCreated;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mProjectName = itemView.findViewById(R.id.textViewProjectName);
            mProjectId = itemView.findViewById(R.id.textViewProjectId);
            mProjectCreated = itemView.findViewById(R.id.textViewProjectCreated);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
