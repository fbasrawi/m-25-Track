package com.example.android.hajjteck;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class CostumListeView extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<Cordonne> modellist;
    ArrayList<Cordonne> arrayList;

    public CostumListeView(Context context, List<Cordonne> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Cordonne>();
        this.arrayList.addAll(modellist);
    }

    @NonNull
    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override

    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent){
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){

            r= inflater.inflate(R.layout.layout_type,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();

        }

        viewHolder.tvw1.setText(" خط الطول :"+modellist.get(position).getCordE());
        viewHolder.tvw2.setText( " خط العرض :"+modellist.get(position).getCordN());
       viewHolder.tvw3.setText(" المكان : "+modellist.get(position).getName());

        return r;
    }

    class ViewHolder{

        TextView tvw1;
        TextView tvw2;
        TextView tvw3;

        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.cordE);
            tvw2=(TextView)v.findViewById(R.id.cordN);
            tvw3=(TextView)v.findViewById(R.id.name);

        }

    }


    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayList);
        }
        else {
            for (Cordonne model : arrayList){
                if (model.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }


}
