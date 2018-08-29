package com.example.administrator.appfoody.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.appfoody.R;
import com.example.administrator.appfoody.model.QuanAnModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterListViewOdau extends BaseAdapter{
    Context context;
    List<QuanAnModel>quanAnModelList;
    int resource;

    public AdapterListViewOdau(Context context, List<QuanAnModel> quanAnModelList, int resource) {
        this.context = context;
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return quanAnModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("nguyenduc",position+"");
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(resource,null);
        Button btndatmonodau=convertView.findViewById(R.id.btnDatMonOdau);
        final ImageView imghinhquananodau=convertView.findViewById(R.id.imageHinhQuanAnOdau);
        if(quanAnModelList.get(position).getHinhanhquanan().size()>0){
            StorageReference storagehinhanh= FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModelList.get(position).getHinhanhquanan().get(0));
            long ONE_MEGABYTE=1024*1024;
            storagehinhanh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    imghinhquananodau.setImageBitmap(bitmap);
                }
            });

        }
        TextView txttenquananodau=convertView.findViewById(R.id.txtTenQuananOdau);
        txttenquananodau.setText(quanAnModelList.get(position).getTenquanan());
        if(quanAnModelList.get(position).isGiaohang()){
            btndatmonodau.setVisibility(View.VISIBLE);
        }
        else{
            btndatmonodau.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }
}
