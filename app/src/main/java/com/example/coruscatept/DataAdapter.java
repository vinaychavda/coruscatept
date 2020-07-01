package com.example.coruscatept;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 06-Jan-18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> implements Filterable, View.OnClickListener{

    private ArrayList<Pojo> mArrayList;
    private List<Pojo> mFilteredList;
    private ItemClickListner itemClickListner;
    Context cc;

    public DataAdapter(Context cc, ArrayList<Pojo> arrayList) {

        this.cc=cc;
        this.mArrayList = arrayList;
        this.mFilteredList=arrayList;
    }

    @Override
    public DataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fetch_list, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(DataAdapter.MyViewHolder holder, final int position) {

        holder.name.setText(mFilteredList.get(position).getName());

        holder.email.setText(mFilteredList.get(position).getAddress());
        final String name1=mFilteredList.get(position).getName();

holder.itemView.setOnClickListener(new View.OnClickListener() {
    private Object FetchActivity;

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(cc, Main2Activity.class);
        intent.putExtra("name",name1);
        cc.startActivity(intent);

    }
});

//        holder.phn.setText(mFilteredList.get(position).getPhn());
    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<Pojo> filteredList = new ArrayList<>();

                    for (Pojo androidVersion : mArrayList) {

                        if (androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getAddress().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Pojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onClick(View view) {
        itemClickListner.onClick(view, getAdapterPosition(), false);

    }

    public void setItemClickListner(ItemClickListner itemClickListner)
    {
        this.itemClickListner = itemClickListner;
    }
    private int getAdapterPosition() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,phn;
        CardView card;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            email = itemView.findViewById(R.id.tv_email);

            card=itemView.findViewById(R.id.card);
            phn = itemView.findViewById(R.id.tv_phn);
            new CountDownTimer(60000,300)
            {
                @Override
                public void onTick(long l) {
                    phn.setVisibility(View.GONE);
                    phn.setVisibility(View.VISIBLE);
                    phn.setText("00:"+l/1000);
                }

                @Override
                public void onFinish() {
                    card.setCardBackgroundColor(Color.rgb(248,206,204));

                }
            }.start();

        }
    }
}
