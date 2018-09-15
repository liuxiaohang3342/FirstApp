package com.example.lxh.firstapp.Behaivor;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class CategoryBehavior extends AppBarLayout.Behavior {

    public CategoryBehavior() {
    }

    public CategoryBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        if (child.getTotalScrollRange() + child.getTop() == 0) {
            return false;
        }
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
    }


}
