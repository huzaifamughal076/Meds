package com.MP.meds.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MP.meds.ModelClass.MyListData;
import com.MP.meds.R;

import java.util.ArrayList;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.viewHolder> {

    Context context;
    ArrayList<MyListData> arrayList;


   public MyDataAdapter(Context context,ArrayList<MyListData> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
       viewHolder viewHolder = new viewHolder(v);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.company_adapter.setText(arrayList.get(position).getRecycle_company());
        holder.Medicine_adapter.setText(arrayList.get(position).getRecycle_Medicine());
        holder.qty_adapter.setText(arrayList.get(position).getRecycle_qty());
        holder.discount_adapter.setText(arrayList.get(position).getRecycle_discount());
        holder.code_adapter.setText(arrayList.get(position).getRecycle_code());

        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Delete Item")
                        .setMessage("Are you really want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {

                                    arrayList.remove(position);
                                    notifyItemRemoved(position);


                                }catch (Exception e)
                                {

                                }



                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        });

                builder.show();

                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class  viewHolder extends RecyclerView.ViewHolder{

        public TextView company_adapter;
        public TextView Medicine_adapter;
        public TextView qty_adapter;
        public TextView discount_adapter;
        public TextView code_adapter;
        public LinearLayout linearLayout;


        public viewHolder(View itemView) {
            super(itemView);

            company_adapter=itemView.findViewById(R.id.recycle_company);
            Medicine_adapter=itemView.findViewById(R.id.recycle_Medicine);
            qty_adapter=itemView.findViewById(R.id.recycle_qty);
            discount_adapter=itemView.findViewById(R.id.recycle_discount);
            linearLayout =itemView.findViewById(R.id.linearlayout);
            code_adapter =itemView.findViewById(R.id.P_code);


        }
    }

}
