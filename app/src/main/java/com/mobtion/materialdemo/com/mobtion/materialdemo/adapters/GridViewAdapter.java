package com.mobtion.materialdemo.com.mobtion.materialdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobtion.materialdemo.R;
import com.mobtion.materialdemo.com.mobtion.materialdemo.resources.ImageItem;
import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<ImageItem>{

    public GridViewAdapter(Context context, ArrayList<ImageItem> imageItem) {
        super(context, 0, imageItem);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_layout, parent, false);
        }
        // Get the data item for this position
        ImageItem imageItem = getItem(position);

        if (imageItem != null) {

            // Lookup view for data population
            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            TextView title = (TextView) convertView.findViewById(R.id.text);

            // Populate the data into the template view using the data object
            if (image != null) {
                image.setImageBitmap(imageItem.getImage());
            }
            if (title != null) {
                title.setText(imageItem.getTitle());
            }
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
