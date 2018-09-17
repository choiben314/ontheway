package itemp2p.hackmit.org.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<DriverOffer> mDataset;

    public MyAdapter(ArrayList<DriverOffer> myDataset) {
        mDataset = myDataset;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ConstraintLayout mConstraintLayout;
        public MyViewHolder(ConstraintLayout v) {
            super(v);
            mConstraintLayout = v;
        }
    }

    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.driver_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        TextView tvName = (TextView) holder.mConstraintLayout.findViewById(R.id.driver_row_name);
        tvName.setText(mDataset.get(position).driver.name);

        TextView tvPrice = (TextView) holder.mConstraintLayout.findViewById(R.id.driver_row_price);
        tvPrice.setText("$" + Double.toString(mDataset.get(position).price));

        TextView tvTime = (TextView) holder.mConstraintLayout.findViewById(R.id.driver_row_time);
        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        tvTime.setText("Est. Arrival: " + mDataset.get(position).estimatedArrival + " minutes");

        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTimeRemaining(v, mDataset.get(position).estimatedArrival);
            }
        });
    }

    public int getItemCount() {
        return mDataset.size();
    }

    public void showTimeRemaining(View v, int date) {
        Intent intent = new Intent(v.getContext(), StatusActivity.class);

        intent.putExtra("TIME", date);
        v.getContext().startActivity(intent);
    }
}
