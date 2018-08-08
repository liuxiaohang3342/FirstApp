package com.example.lxh.firstapp.home.today;

import java.util.List;

/**
 * Created by lxh on 2018/8/8.
 */

public interface IHistoryListener {
    void onHistoryBack(List<String> historys);
    void onHistoryError();
}
