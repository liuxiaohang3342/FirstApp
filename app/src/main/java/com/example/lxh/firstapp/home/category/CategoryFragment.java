package com.example.lxh.firstapp.home.category;

import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;

/**
 * Created by lxh on 2018/8/10.
 */

public class CategoryFragment extends BaseMVPFragment<CategoryPresenter, ICategoryView> implements ICategoryView {
    @Override
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
