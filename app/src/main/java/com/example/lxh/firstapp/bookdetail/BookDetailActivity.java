package com.example.lxh.firstapp.bookdetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.base.core.imageloader.config.ImageConfig;
import com.example.lxh.firstapp.bean.BookInfo;
import com.example.lxh.firstapp.bean.SimpleBookInfo;
import com.example.lxh.firstapp.home.book.Constants;
import com.example.lxh.firstapp.utils.StringUtil;

import java.util.List;

/**
 * Created by lxh on 2018/9/11.
 */

public class BookDetailActivity extends AppCompatActivity implements IBookDetailView {

    private BookDetailPresenter mPresenter;

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private BookInfo mBookInfo;
    private ImageView mHeaderBgView;
    private ImageView mBookImgView;
    private TextView mAuthorView;
    private TextView mStateView;
    private TextView mTagView;
    private TextView mDescView;
    private TextView mAuthorDescViwe;

    private View mAuthorTipView;
    private View mOtherBookTipView;
    private View mSimilarTipView;

    private RecyclerView mSimilarRecyclerView;
    private RecyclerView mOtherBookRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar();
        initBookInfo();
        initView();
        mPresenter = new BookDetailPresenter(this);
        mPresenter.requestData(mBookInfo);
        initializeView();
    }

    private void initializeView() {
        ImageLoader.getInstance().load(mBookImgView, mBookInfo.getBook_cover(),
                new ImageConfig.Builder().setFailureDrawable(R.drawable.img_default_book).setLoadingRes(R.drawable.img_default_book).create());
        mAuthorView.setText(String.format(getResources().getString(R.string.book_detail_author), mBookInfo.getAuthor()));
        mStateView.setText(String.format(getResources().getString(R.string.book_detail_state), mBookInfo.getStat_name()));
        mTagView.setText(StringUtil.arryToString(mBookInfo.getTag(), "|"));
        mDescView.setText(mBookInfo.getBook_info());
        ImageLoader.getInstance().load(mHeaderBgView, mBookInfo.getBook_cover(), 30, 4);
        mToolbar.setTitle(mBookInfo.getBookname());
    }

    private void initView() {
        mHeaderBgView = (ImageView) findViewById(R.id.iv_header_background);
        mBookImgView = (ImageView) findViewById(R.id.iv_book_img);
        mAuthorView = (TextView) findViewById(R.id.tv_author);
        mStateView = (TextView) findViewById(R.id.tv_state);
        mTagView = (TextView) findViewById(R.id.tv_tags);
        mDescView = (TextView) findViewById(R.id.tv_desc);
        mAuthorDescViwe = (TextView) findViewById(R.id.tv_author_desc);

        mAuthorTipView = findViewById(R.id.tv_author_tip);
        mOtherBookTipView = findViewById(R.id.tv_other_book_tip);
        mSimilarTipView = findViewById(R.id.tv_similar_tip);

        mOtherBookRecyclerView = (RecyclerView) findViewById(R.id.rlv_author_other_book);
        mSimilarRecyclerView = (RecyclerView) findViewById(R.id.rlv_similar_book);
    }

    protected void setToolBar() {
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            //去除默认Title显示
            mActionBar.setDisplayShowTitleEnabled(false);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initBookInfo() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mBookInfo = bundle.getParcelable(Constants.KEY_BOOK_INFO);
        }
    }

    public static void start(Activity context, ImageView imageView, BookInfo bookInfo) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(Constants.KEY_BOOK_INFO, bookInfo);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        imageView, context.getString(R.string.transition_book_img));//与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    @Override
    public void showSimilarView(List<SimpleBookInfo> bookInfoList) {
        mSimilarRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mSimilarRecyclerView.setAdapter(new BookAdapter(R.layout.book_header_item_layout, bookInfoList));
    }

    @Override
    public void showAuthorInfo(String authorInfo) {
        mAuthorDescViwe.setText(authorInfo);
    }

    @Override
    public void showOtherBookView(List<SimpleBookInfo> bookInfoList) {
        mOtherBookRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mOtherBookRecyclerView.setAdapter(new BookAdapter(R.layout.book_header_item_layout, bookInfoList));
    }

    @Override
    public void hideSimilarView() {
        mSimilarTipView.setVisibility(View.GONE);
        mSimilarRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideAuthorView() {
        mAuthorDescViwe.setVisibility(View.GONE);
        mAuthorTipView.setVisibility(View.GONE);
    }

    @Override
    public void hideOtherBookView() {
        mOtherBookTipView.setVisibility(View.GONE);
        mOtherBookRecyclerView.setVisibility(View.GONE);
    }
}
