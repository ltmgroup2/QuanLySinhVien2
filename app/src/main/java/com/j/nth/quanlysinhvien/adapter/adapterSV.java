package com.j.nth.quanlysinhvien.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.j.nth.quanlysinhvien.R;
import com.j.nth.quanlysinhvien.classes.STAFF;

import java.util.ArrayList;

public class adapterSV extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<STAFF> STAFFArrayList;
    ArrayList<STAFF> filterList;
    CustomFilter filter;

    public adapterSV(Context context, int layout, ArrayList<STAFF> STAFFArrayList) {
        this.context = context;
        this.layout = layout;
        this.STAFFArrayList = STAFFArrayList;
        filterList = STAFFArrayList;
    }

    @Override
    public int getCount() {
        return STAFFArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return STAFFArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class viewHolder
    {
        ImageView imageViewHinh;
        TextView txtTenSV;
        TextView txtLop;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new viewHolder();
            holder.imageViewHinh = convertView.findViewById(R.id.imageViewLstHinhSV);
            holder.txtTenSV = convertView.findViewById(R.id.txtLstTenSV);
            holder.txtLop = convertView.findViewById(R.id.txtLstLop);
            convertView.setTag(holder);
        }
        else
        {
            holder = (viewHolder) convertView.getTag();
        }
        STAFF STAFF = STAFFArrayList.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(STAFF.getImage(),0, STAFF.getImage().length);
        holder.imageViewHinh.setImageBitmap(bitmap);
        holder.txtTenSV.setText(STAFF.getName());
        holder.txtLop.setText(STAFF.getAddress());
        return convertView;
    }
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toLowerCase();
                ArrayList<STAFF> filters = new ArrayList<>();
                for (STAFF sv : filterList) {
                    if (sv.getName().toLowerCase().contains(constraint) || sv.getAddress().toLowerCase().contains(constraint)) {
                        filters.add(sv);
                    }
                }

                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            STAFFArrayList = (ArrayList<STAFF>) results.values;
            notifyDataSetChanged();
        }
    }
}
