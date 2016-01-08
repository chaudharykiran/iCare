package com.example.icare;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ItemListActivityFragment extends Fragment {

    /* private instance variable. */
//    private List<Item> items;

    /* private instance variable for adapter */
//    private ItemAdapter itemAdapter;


    public ItemListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);

        // RecyclerView
        RecyclerView categoryRecyclerView = (RecyclerView) rootView.findViewById(R.id.category_recycler_view);
        // use this settings to improve performance
        categoryRecyclerView.setHasFixedSize(true);

        // use LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        categoryRecyclerView.setLayoutManager(linearLayoutManager);
//
//        // Create some data
//        initializeDate();
//
//        itemAdapter = new ItemAdapter(items);
//        cardView.setAdapter(itemAdapter);

        return rootView;
    }

/*    private class Item {
        private String itemName;
        private int itemPhotoId;

        Item(String itemName, int itemPhotoId) {
            this.itemName = itemName;
            this.itemPhotoId = itemPhotoId;
        }
    }

    private void initializeDate() {
        items = new ArrayList<>();
        items.add(new Item("Item 1", R.drawable.item1));
        items.add(new Item("Item 2", R.drawable.item2));
        items.add(new Item("Item 3", R.drawable.item4));
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView itemName;
        ImageView itemPhotoId;

        ViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.item_card_view);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemPhotoId = (ImageView) itemView.findViewById(R.id.item_photo);
        }
    }

    *//* Creating an Adapter *//*
    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private List<Item> items;

        // Provide a suitable constructor
        public ItemAdapter (List<Item> items) {
            this.items = items;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                         int i) {
            // Create a new view
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_item_list, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(v);

            return viewHolder;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Item ci = items.get(position);

            holder.itemName.setText(ci.itemName);
            holder.itemPhotoId.setImageResource(ci.itemPhotoId);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return items.size();
        }
    }*/

}
