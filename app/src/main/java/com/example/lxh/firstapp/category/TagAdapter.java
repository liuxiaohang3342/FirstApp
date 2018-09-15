package com.example.lxh.firstapp.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.bean.CategoryInfo;
import com.example.lxh.firstapp.view.AutoLayout;

import java.util.List;

public class TagAdapter implements AutoLayout.AutoAdapter {

    private Context mContext;
    private List<CategoryInfo> mInfoList;

    public TagAdapter(Context mContext, List<CategoryInfo> mInfoList) {
        this.mContext = mContext;
        this.mInfoList = mInfoList;
    }

    @Override
    public int getCount() {
        return mInfoList.size();
    }

    @Override
    public View getView(ViewGroup viewGroup, int position) {
        TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tag_layout, viewGroup, false);
        CategoryInfo info = mInfoList.get(position);
        textView.setText(info.getName());
        return textView;
    }
}
