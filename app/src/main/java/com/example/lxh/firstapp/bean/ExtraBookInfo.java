package com.example.lxh.firstapp.bean;

import java.util.List;

/**
 * Created by lxh on 2018/9/12.
 */

public class ExtraBookInfo {

    private SimilarBook dk;

    private AuthorInfo at;

    public AuthorInfo getAt() {
        return at;
    }

    public void setAt(AuthorInfo at) {
        this.at = at;
    }

    public SimilarBook getDk() {
        return dk;
    }

    public void setDk(SimilarBook dk) {
        this.dk = dk;
    }

    public class SimilarBook {
        private List<SimpleBookInfo> bookinfo;

        public List<SimpleBookInfo> getBookinfo() {
            return bookinfo;
        }

        public void setBookinfo(List<SimpleBookInfo> bookinfo) {
            this.bookinfo = bookinfo;
        }
    }
}
