package com.example.lxh.firstapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lxh on 2018/9/11.
 */

public class BookInfo implements Parcelable {
    private String bid;
    private String bookname;
    private String introduction;
    private String book_info;
    private String chapterid;
    private String topic;
    private String topic_first;
    private long date_updated;
    private String author;
    private String author_name;
    private String top_class;
    private String state;
    private String readCount;
    private String praiseCount;
    private String stat_name;
    private String class_name;
    private String size;
    private String book_cover;
    private String chapterid_first;
    private String chargeMode;
    private String digest;
    private String price;
    private int is_new;
    private int discountNum;
    private int quick_price;
    private String formats;
    private String audiobook_playCount;
    private String chapterNum;
    private String userid;
    private String search_heat;
    private String num_click;
    private String recommend_num;
    private String first_cate_id;
    private String first_cate_name;
    private String reason;
    private boolean isShortStory;
    private String[] tag;

    protected BookInfo(Parcel in) {
        bid = in.readString();
        bookname = in.readString();
        introduction = in.readString();
        book_info = in.readString();
        chapterid = in.readString();
        topic = in.readString();
        topic_first = in.readString();
        date_updated = in.readLong();
        author = in.readString();
        author_name = in.readString();
        top_class = in.readString();
        state = in.readString();
        readCount = in.readString();
        praiseCount = in.readString();
        stat_name = in.readString();
        class_name = in.readString();
        size = in.readString();
        book_cover = in.readString();
        chapterid_first = in.readString();
        chargeMode = in.readString();
        digest = in.readString();
        price = in.readString();
        is_new = in.readInt();
        discountNum = in.readInt();
        quick_price = in.readInt();
        formats = in.readString();
        audiobook_playCount = in.readString();
        chapterNum = in.readString();
        userid = in.readString();
        search_heat = in.readString();
        num_click = in.readString();
        recommend_num = in.readString();
        first_cate_id = in.readString();
        first_cate_name = in.readString();
        reason = in.readString();
        isShortStory = in.readByte() != 0;
        tag = in.createStringArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bid);
        dest.writeString(bookname);
        dest.writeString(introduction);
        dest.writeString(book_info);
        dest.writeString(chapterid);
        dest.writeString(topic);
        dest.writeString(topic_first);
        dest.writeLong(date_updated);
        dest.writeString(author);
        dest.writeString(author_name);
        dest.writeString(top_class);
        dest.writeString(state);
        dest.writeString(readCount);
        dest.writeString(praiseCount);
        dest.writeString(stat_name);
        dest.writeString(class_name);
        dest.writeString(size);
        dest.writeString(book_cover);
        dest.writeString(chapterid_first);
        dest.writeString(chargeMode);
        dest.writeString(digest);
        dest.writeString(price);
        dest.writeInt(is_new);
        dest.writeInt(discountNum);
        dest.writeInt(quick_price);
        dest.writeString(formats);
        dest.writeString(audiobook_playCount);
        dest.writeString(chapterNum);
        dest.writeString(userid);
        dest.writeString(search_heat);
        dest.writeString(num_click);
        dest.writeString(recommend_num);
        dest.writeString(first_cate_id);
        dest.writeString(first_cate_name);
        dest.writeString(reason);
        dest.writeByte((byte) (isShortStory ? 1 : 0));
        dest.writeStringArray(tag);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookInfo> CREATOR = new Creator<BookInfo>() {
        @Override
        public BookInfo createFromParcel(Parcel in) {
            return new BookInfo(in);
        }

        @Override
        public BookInfo[] newArray(int size) {
            return new BookInfo[size];
        }
    };

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getBook_info() {
        return book_info;
    }

    public void setBook_info(String book_info) {
        this.book_info = book_info;
    }

    public String getChapterid() {
        return chapterid;
    }

    public void setChapterid(String chapterid) {
        this.chapterid = chapterid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic_first() {
        return topic_first;
    }

    public void setTopic_first(String topic_first) {
        this.topic_first = topic_first;
    }

    public long getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(long date_updated) {
        this.date_updated = date_updated;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getTop_class() {
        return top_class;
    }

    public void setTop_class(String top_class) {
        this.top_class = top_class;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(String praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getStat_name() {
        return stat_name;
    }

    public void setStat_name(String stat_name) {
        this.stat_name = stat_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBook_cover() {
        return book_cover;
    }

    public void setBook_cover(String book_cover) {
        this.book_cover = book_cover;
    }

    public String getChapterid_first() {
        return chapterid_first;
    }

    public void setChapterid_first(String chapterid_first) {
        this.chapterid_first = chapterid_first;
    }

    public String getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(String chargeMode) {
        this.chargeMode = chargeMode;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIs_new() {
        return is_new;
    }

    public void setIs_new(int is_new) {
        this.is_new = is_new;
    }

    public int getDiscountNum() {
        return discountNum;
    }

    public void setDiscountNum(int discountNum) {
        this.discountNum = discountNum;
    }

    public int getQuick_price() {
        return quick_price;
    }

    public void setQuick_price(int quick_price) {
        this.quick_price = quick_price;
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }

    public String getAudiobook_playCount() {
        return audiobook_playCount;
    }

    public void setAudiobook_playCount(String audiobook_playCount) {
        this.audiobook_playCount = audiobook_playCount;
    }

    public String getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(String chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSearch_heat() {
        return search_heat;
    }

    public void setSearch_heat(String search_heat) {
        this.search_heat = search_heat;
    }

    public String getNum_click() {
        return num_click;
    }

    public void setNum_click(String num_click) {
        this.num_click = num_click;
    }

    public String getRecommend_num() {
        return recommend_num;
    }

    public void setRecommend_num(String recommend_num) {
        this.recommend_num = recommend_num;
    }

    public String getFirst_cate_id() {
        return first_cate_id;
    }

    public void setFirst_cate_id(String first_cate_id) {
        this.first_cate_id = first_cate_id;
    }

    public String getFirst_cate_name() {
        return first_cate_name;
    }

    public void setFirst_cate_name(String first_cate_name) {
        this.first_cate_name = first_cate_name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isShortStory() {
        return isShortStory;
    }

    public void setShortStory(boolean shortStory) {
        isShortStory = shortStory;
    }

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }
}
