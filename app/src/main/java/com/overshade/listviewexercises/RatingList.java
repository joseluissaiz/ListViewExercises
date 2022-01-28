package com.overshade.listviewexercises;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class RatingList extends ListActivity {

    private static final String[] items = {
            "lorem", "ipsum", "dolor", "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_view);
        ArrayList<RowModel> list=new ArrayList<>();

        for (String s : items) {
            list.add(new RowModel(s));
        }

        setListAdapter(new RatingAdapter(list));
    }


    private class RowModel {
        String label;
        float rating=2.0f;

        public RowModel(String label) {
            this.label=label;
        }

        public String toString() {
            if (rating>=3.0) {
                return(label.toUpperCase());
            }

            return(label);
        }
    }

    private RowModel getModel(int position) {
        return(((RatingAdapter)getListAdapter()).getItem(position));
    }


    private class RatingAdapter extends ArrayAdapter<RowModel> {

        public RatingAdapter(ArrayList<RowModel> list) {
            super(RatingList.this, R.layout.rating_row, R.id.label, list);
        }

        public View getView(int position, View convertView,
                            ViewGroup parent) {
            View row=super.getView(position, convertView, parent);
            ViewHolder holder=(ViewHolder)row.getTag();

            if (holder==null) {
                holder=new ViewHolder(row);
                row.setTag(holder);
                RatingBar.OnRatingBarChangeListener l=
                        (ratingBar, rating, fromTouch) -> {
                            Integer myPosition=(Integer)ratingBar.getTag();
                            RowModel model=getModel(myPosition);
                            model.rating=rating;
                            LinearLayout parent1 = (LinearLayout) ratingBar.getParent();
                            TextView label = parent1.findViewById(R.id.label);
                            label.setText(model.toString());
                        };
                holder.rate.setOnRatingBarChangeListener(l);
            }

            RowModel model=getModel(position);
            holder.rate.setTag(new Integer(position));
            holder.rate.setRating(model.rating);
            return(row);
        }
    }


    private class ViewHolder {
        RatingBar rate = null;

        public ViewHolder(View base) {
            this.rate = base.findViewById(R.id.rate);
        }
    }
}
