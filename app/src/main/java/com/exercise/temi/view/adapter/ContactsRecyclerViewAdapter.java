package com.exercise.temi.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.exercise.temi.R;
import com.exercise.temi.network.model.Contact;
import com.exercise.temi.util.ImageUtils;
import com.exercise.temi.util.TextUtils;
import com.exercise.temi.view.fragment.BaseFragment;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Contact} and makes a call to the
 * specified {@link BaseFragment.OnItemClickListener}.
 */
public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactViewHolder> {

    private final List<Contact> mValues;
    private final BaseFragment.OnItemClickListener mListener;

    public ContactsRecyclerViewAdapter(List<Contact> items, BaseFragment.OnItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_content_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder viewHolder, int position) {
        viewHolder.mItem = mValues.get(position);
        Context c = viewHolder.itemView.getContext();
        Glide.with(c)
                    .load(viewHolder.mItem.getAvatar())
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ImageUtils.getCircleImageView(viewHolder.itemView.getContext(), viewHolder.mAvatarView));

        viewHolder.mNameView.setText(viewHolder.mItem.getFirstName() + " " + viewHolder.mItem.getLastName());
        if (mListener == null){
            viewHolder.mDateView.setVisibility(View.VISIBLE);
            if (viewHolder.mItem.getLastMsgDate() != null)
                viewHolder.mDateView.setText(TextUtils.convertDateToLocalDate(viewHolder.mItem.getLastMsgDate(), "GMT", "yyyy-MM-dd HH:mm:ss", "HH:mm:ss  EE, dd MMM"));
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener{
        final View mView;
        final ImageView mAvatarView;
        final TextView mNameView;
        final TextView mDateView;
        Contact mItem;

        public ContactViewHolder(View view) {
            super(view);
            mView = view;
            mAvatarView = view.findViewById(R.id.pic);
            mNameView = view.findViewById(R.id.name);
            mDateView = view.findViewById(R.id.date);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null)
                mListener.onItemClick(mValues.get(getAdapterPosition()), getAdapterPosition());
        }
    }
}
