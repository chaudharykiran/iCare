package com.example.icare;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ItemListActivityFragment extends Fragment {

    private final static String LOG_TAG = "ItemListActivityFragment";

    /* private instance variable. */
    private List<Item> items;

    /* private instance variable for adapter */
    private ItemAdapter itemAdapter;


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


        // Create some data
        initializeDate();

        itemAdapter = new ItemAdapter(getActivity(), items);
        categoryRecyclerView.setAdapter(itemAdapter);

        return rootView;
    }

    private class Item {
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

    // Creating an Adapter
    private class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

        /* private instance variable to store Layout of each item. */
        private LayoutInflater inflater;
        /* Store data */
        List<Item> items = Collections.emptyList();

        // Constructor to inflate layout of each item in RecyclerView
        public ItemAdapter(Context context, List<Item> items) {
            inflater = LayoutInflater.from(context);
            this.items = items;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.category_item, parent,false);

            ItemViewHolder holder = new ItemViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            Item current = items.get(position);

            holder.itemName.setText(current.itemName);
            holder.itemPhoto.setImageResource(current.itemPhotoId);

            // click event handler when Item in RecyclerView is clicked
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        /* ViewHolder for this adapter */
        class ItemViewHolder extends RecyclerView.ViewHolder {

            TextView itemName;
            ImageView itemPhoto;
            android.support.v7.widget.CardView cardView;

            public ItemViewHolder(View itemView) {
                super(itemView);

                itemName = (TextView) itemView.findViewById(R.id.item_name);
                itemPhoto = (ImageView) itemView.findViewById(R.id.item_photo);
                cardView = (android.support.v7.widget.CardView) itemView.findViewById(R.id.item_card_view);
            }
        }
    }

}
