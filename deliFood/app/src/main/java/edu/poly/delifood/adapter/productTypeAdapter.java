package edu.poly.delifood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.poly.delifood.R;
import edu.poly.delifood.model.productType;

public class productTypeAdapter extends BaseAdapter {
    List<productType> array;
    Context context;

    public productTypeAdapter(Context context, List<productType> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        TextView textensp;
        ImageView imghinhanh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_product, null);
            viewHolder.textensp = view.findViewById(R.id.txtProductName);

            viewHolder.imghinhanh = view.findViewById(R.id.imgImage);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textensp.setText(array.get(i).getNameProduct());
        Glide.with(context).load(array.get(i).getImage()).into(viewHolder.imghinhanh);
        return view;
    }
}
