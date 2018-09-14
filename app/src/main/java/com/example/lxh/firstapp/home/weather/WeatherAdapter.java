package com.example.lxh.firstapp.home.weather;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.bean.WeatherDayInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/3.
 */

public class WeatherAdapter extends PagerAdapter {

    private ColorMatrix mMatrix;
    private int mColor;

    private Context mContext;
    private List<WeatherDayInfo> mList;

    public WeatherAdapter(Context context, List<WeatherDayInfo> mList) {
        mContext = context;
        this.mList = mList;
        mColor = context.getColor(R.color.colorTheme);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_item_layout, container, false);
        initView(view, position);
        container.addView(view);
        return view;
    }

    private void initView(View view, int position) {
        WeatherDayInfo info = mList.get(position);
        TextView wendu = (TextView) view.findViewById(R.id.tv_wendu);
        TextView notice = (TextView) view.findViewById(R.id.tv_notice);
        TextView sunrise = (TextView) view.findViewById(R.id.tv_sunrise);
        TextView sunset = (TextView) view.findViewById(R.id.tv_sunset);
        TextView qiya = (TextView) view.findViewById(R.id.tv_qiya);
        TextView fengx = (TextView) view.findViewById(R.id.tv_fengx);
        TextView fengji = (TextView) view.findViewById(R.id.tv_fengji);
        ImageView weather = (ImageView) view.findViewById(R.id.iv_weather);
        String low = info.getLow().substring(3);
        String high = info.getHigh().substring(3);
        wendu.setText(String.format(mContext.getString(R.string.weather_wendu), info.getType(), low, high));
        notice.setText(info.getNotice());
        sunrise.setText(info.getSunrise());
        sunset.setText(info.getSunset());
        qiya.setText(String.valueOf(info.getAqi()));
        fengx.setText(info.getFx());
        fengji.setText(info.getFl());
        setWeatherType(weather, info.getType());
    }

    public ColorMatrixColorFilter getColorFilter() {
        if (mMatrix == null) {
            mMatrix = new ColorMatrix();
        }
        mMatrix.set(new float[]{
                0, 0, 0, 0, Color.red(mColor),
                0, 0, 0, 0, Color.green(mColor),
                0, 0, 0, 0, Color.blue(mColor),
                0, 0, 0, 1, 0
        });
        return new ColorMatrixColorFilter(mMatrix);
    }

    private Drawable getDrawableWithType(String type) {
        if (type.contains("沙")) {
            return mContext.getDrawable(R.drawable.sand);
        }
        if (type.contains("雾")) {
            return mContext.getDrawable(R.drawable.fog);
        }
        if (type.contains("冰")) {
            return mContext.getDrawable(R.drawable.icecream);
        }
        if (type.contains("雪")) {
            return mContext.getDrawable(R.drawable.snow);
        }
        if (type.contains("雨")) {
            return mContext.getDrawable(R.drawable.bigest_rain);
        }
        if (type.contains("云")) {
            return mContext.getDrawable(R.drawable.cloudy);
        }
        return mContext.getDrawable(R.drawable.sunny);
    }

    private void setWeatherType(ImageView weather, String type) {
        Drawable drawable = getDrawableWithType(type);
        drawable.getCurrent().setColorFilter(getColorFilter());
        weather.setBackground(drawable);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getDate().substring(3);
    }
}
