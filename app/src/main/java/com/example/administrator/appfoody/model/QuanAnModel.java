package com.example.administrator.appfoody.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.appfoody.interfaces.OdauInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanAnModel {
    boolean giaohang;
    String giodongcua,giomocua,tenquanan,videogioithieu,maquanan;
    List<String> tienich;

    public List<String> getHinhanhquanan() {
        return hinhanhquanan;
    }

    public void setHinhanhquanan(List<String> hinhanhquanan) {
        this.hinhanhquanan = hinhanhquanan;
    }

    List<String> hinhanhquanan;
    long luotthich;
    DatabaseReference nodeRoot;

    public QuanAnModel() {
        nodeRoot= FirebaseDatabase.getInstance().getReference();
    }

    public QuanAnModel(boolean giaohang, String giodongcua, String giomocua, String tenquanan, String videogioithieu, String maquanan, List<String> tienich, long luotthich) {
        this.giaohang = giaohang;
        this.giodongcua = giodongcua;
        this.giomocua = giomocua;
        this.tenquanan = tenquanan;
        this.videogioithieu = videogioithieu;
        this.maquanan = maquanan;
        this.tienich = tienich;
        this.luotthich = luotthich;
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }

    public List<String> getTienich() {
        return tienich;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public void getDanhSachQuanAn(final OdauInterface odauInterface){
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshotquanan=dataSnapshot.child("quanans");
                Log.d("kiemtra",dataSnapshotquanan+"");
                for(DataSnapshot valuequanan:dataSnapshotquanan.getChildren()){
                    QuanAnModel quanAnModel=valuequanan.getValue(QuanAnModel.class);
                    quanAnModel.setMaquanan(valuequanan.getKey());
                    DataSnapshot dataSnapshothinhquanan=dataSnapshot.child("hinhanhquanans").child(valuequanan.getKey());
                    List<String>hinhanhlist=new ArrayList<>();
                    for(DataSnapshot valuehinhquanan:dataSnapshothinhquanan.getChildren()){
                        hinhanhlist.add(valuehinhquanan.getValue(String.class));
                    }
                    quanAnModel.setHinhanhquanan(hinhanhlist);
                    odauInterface.getDanhSaachQuanAnModel(quanAnModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }
}
