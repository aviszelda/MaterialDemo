package com.mobtion.materialdemo.com.mobtion.materialdemo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mobtion.materialdemo.R;
import com.mobtion.materialdemo.com.mobtion.materialdemo.resources.Person;
import java.util.ArrayList;

/**
 * Created by agvenegas on 7/1/16.
 */
public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> implements View.OnClickListener {


    private ArrayList<Person> datos;
    private View.OnClickListener listener;


    public listAdapter(ArrayList<Person> list) {
        super();
        datos = list;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        itemView.setOnClickListener(this);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        Person item = datos.get(pos);

        viewHolder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {

        if(listener != null)
            listener.onClick(v);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtAge;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView)itemView.findViewById(R.id.name);
            txtAge = (TextView)itemView.findViewById(R.id.age);

        }

        public void bindTitular(Person t) {
            txtName.setText(t.getName());
            txtAge.setText(t.getAge());
        }
    }

}
