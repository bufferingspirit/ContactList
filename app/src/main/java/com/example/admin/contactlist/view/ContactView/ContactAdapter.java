package com.example.admin.contactlist.view.ContactView;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.contactlist.R;
import com.example.admin.contactlist.model.StoredContact.StoredContact;
import com.example.admin.contactlist.util.BitmapUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 10/11/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private static final String TAG = "OptionsAdapter";
    ArrayList<StoredContact> contactList;
    Context context;

    private int lastPosition = -1;

    public ContactAdapter(ArrayList<StoredContact> optionsList) {
        this.contactList = optionsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.contact_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final StoredContact entry = contactList.get(position);

        holder.tvName.setText(entry.getFirstName() + " " + entry.getLastName());

        if(entry.getThumbnail() != null)
        holder.ivThumbnail.setImageBitmap(BitmapUtil.getImage(entry.getThumbnail()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add dialogue
                Log.d(TAG, "onClick: " + entry.getFirstName() + " " + entry.getLastName());
                ContactDialogue foo = new ContactDialogue(context, entry);
                foo.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (contactList != null) {
            return contactList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.ivThumbnail)
        ImageView ivThumbnail;

        @Nullable
        @BindView(R.id.tvName)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
