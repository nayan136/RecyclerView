package com.example.nayan.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nayan on 9/1/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> values;

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_header;
        TextView tv_footer;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_header = itemView.findViewById(R.id.tv_header);
            tv_footer = itemView.findViewById(R.id.tv_footer);
        }
    }

    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        Log.d("position: ", values.toString());
        values.remove(position);

        /*
            public final void notifyItemRemoved (int position)
                Notify any registered observers that the item previously located at position
                has been removed from the data set. The items previously located at and
                after position may now be found at oldPosition - 1.

                This is a structural change event. Representations of other existing items
                in the data set are still considered up to date and will not be rebound,
                though their positions may be altered.

            Parameters
                position : Position of the item that has now been removed
        */
        notifyItemRemoved(position);

        /*
            public final void notifyItemRangeChanged (int positionStart, int itemCount)
                Notify any registered observers that the itemCount items starting at
                position positionStart have changed. Equivalent to calling
                notifyItemRangeChanged(position, itemCount, null);.

                This is an item change event, not a structural change event. It indicates
                that any reflection of the data in the given position range is out of date
                and should be updated. The items in the given range retain the same identity.

            Parameters
                positionStart : Position of the first item that has changed
                itemCount : Number of items that have changed
          */
        notifyItemRangeChanged(position,values.size());
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<String> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_contact,parent,false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = values.get(position);
        holder.tv_header.setText(name);
        holder.tv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("position: ", String.valueOf(position));
                Log.d("position: ", values.get(position));
                remove(position);
            }
        });

        holder.tv_footer.setText("Footer: "+name);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }


}
