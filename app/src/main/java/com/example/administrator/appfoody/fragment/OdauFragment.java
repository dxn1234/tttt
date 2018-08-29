package com.example.administrator.appfoody.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.administrator.appfoody.R;
import com.example.administrator.appfoody.adapter.AdapterListViewOdau;
import com.example.administrator.appfoody.interfaces.OdauInterface;
import com.example.administrator.appfoody.model.QuanAnModel;

import java.util.ArrayList;
import java.util.List;

public class OdauFragment extends Fragment{
    List<QuanAnModel>quanAnModelList;
    QuanAnModel quanAnModel;
    ListView lvodau;
    TabLayout tabLayoutodau;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_fragment_odau,container,false);
        anhXa();
        taoGiaoDien();
        final AdapterListViewOdau adapterListViewOdau=new AdapterListViewOdau(getActivity(),quanAnModelList,R.layout.custom_layout_listview_odau);
        lvodau.setAdapter(adapterListViewOdau);
        setListViewHeightBasedOnChildren(lvodau);
        OdauInterface odauInterface=new OdauInterface() {
            @Override
            public void getDanhSaachQuanAnModel(QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);
                adapterListViewOdau.notifyDataSetChanged();
                Log.d("kiemtrasizelistview",lvodau.getAdapter().getCount()+"");
            }
        };
        Log.d("kiemtra","kila");
        quanAnModel.getDanhSachQuanAn(odauInterface);
        return view;
    }

    private void taoGiaoDien() {
        tabLayoutodau.addTab(tabLayoutodau.newTab().setText("Mới nhất"));
        tabLayoutodau.addTab(tabLayoutodau.newTab().setText("Danh mục"));
        tabLayoutodau.addTab(tabLayoutodau.newTab().setText("Hồ Chí Minh"));
        tabLayoutodau.getTabAt(0).setIcon(R.drawable.quarter);
        tabLayoutodau.getTabAt(1).setIcon(R.drawable.quarter);
        tabLayoutodau.getTabAt(2).setIcon(R.drawable.quarter);
    }

    private void anhXa() {
        quanAnModelList=new ArrayList<>();
        quanAnModel=new QuanAnModel();
        lvodau=view.findViewById(R.id.lv_odau);
        tabLayoutodau=view.findViewById(R.id.tablayout_odau);
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
