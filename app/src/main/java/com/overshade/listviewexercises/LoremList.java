package com.overshade.listviewexercises;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class LoremList extends ListActivity {
    private TextView selection;
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
        setContentView(R.layout.lorem_view);
        setListAdapter(new IconicAdapter());
        selection = findViewById(R.id.selection);
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        selection.setText(items[position]);
        v.findViewById(R.id.image).setBackgroundResource(R.drawable.ic_baseline_done_24);
    }

    private class IconicAdapter extends ArrayAdapter<String> {

        private IconicAdapter() {
            super(LoremList.this, R.layout.lorem_row, R.id.text, items);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View row=super.getView(position, convertView, parent);
            ViewHolder holder=(ViewHolder)row.getTag();

            if (holder==null) {
                holder=new ViewHolder(row);
                row.setTag(holder);
            }

            if (getItem(position).length()>4) {
                holder.icon.setImageResource(R.drawable.clear);
            }
            else {
                holder.icon.setImageResource(R.drawable.ic_baseline_done_24);
            }
            return(row);
        }
    }

    private class ViewHolder  {
        ImageView icon = null;

        private ViewHolder(View base) {
            this.icon = base.findViewById(R.id.image);
        }
    }

}
