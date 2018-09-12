package com.example.lxh.firstapp.api;

import com.example.lxh.firstapp.bean.BookInfo;
import com.example.lxh.firstapp.bean.ContentInfo;
import com.example.lxh.firstapp.bean.ExtraBookInfo;
import com.example.lxh.firstapp.bean.response.BookListResponse;
import com.example.lxh.firstapp.bean.response.DataResponse;
import com.example.lxh.firstapp.bean.response.BookResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lxh on 2018/8/8.
 */
interface BookApi {


    /**********
     * OPEN_API   start
     *************/

    String OPEN_API = "https://www.apiopen.top/";

    /**
     * 推荐
     *
     * @return
     */
    @GET("novelApi")
    Observable<BookResponse<List<BookInfo>>> recommendBook();


    /**********OPEN_API   end*************/


    /**********
     * BOOK_API   start
     *************/
    String BOOK_API = "http://bookapi.shuqiapi.com/";

    /**
     * 获取作者其他作品和读了该书的还读了
     *
     * @param bid
     * @param name
     * @param author
     * @param authorId
     * @param time
     * @return
     */
    @GET("?bamp=sqtgatdk&dataFrom=sm&session=&userId=1049737176&atType=click&limit=8&ver=180904&fr_pr_id=10002&sn=1536669906183831&imei=354765082962002")
    Observable<BookResponse<ExtraBookInfo>> getAuthorOtherBook(@Query("bid") String bid, @Query("bkName") String name, @Query("authName") String author, @Query("authorid") String authorId, @Query("tk") String tk, @Query("_") long time);

    /**********BOOK_API   end*************/


    /**********
     * BOOK_STORE_API   start
     *************/
    String BOOK_STORE_API = "http://bookstoreapi.shuqireader.com/";

    /**
     * 推荐，暂时用来做列表了
     *
     * @return
     */
    @GET("eva_bookstore/v1/module/query?appId=16&channelId=src1060&versionId=10.7.0.68&placeId=1060&ver=180904&userId=1049737176&imei=354765082962002&sn=1536669906183831&func_id=19&orderid=7&skinId=999&skinVersion=1.0&skinVersionPrefix=1&classId=19&lifecycle=10497371760375&utdid=V1lnOTdxU2dvRllEQUxPcWc0WUIxdERy&imagetype=1&timestamp=1536671134933&key=shuqiapi&sign=4DB4CFEB755EC9100D4502C59BCDD4AF&_=1536671134935")
    Observable<BookResponse<BookListResponse>> getRecommendBook();

    /**********BOOK_STORE_API   end*************/


    /**********
     * BOOK_INFO_API   start
     *************/

    String BOOK_INFO_API = "http://walden1.shuqireader.com/";


    /**
     * 书籍详情，暂时用不到
     *
     * @param bookId
     * @param user_id
     * @param map
     * @return
     */
    //bookId=7720148&user_id=1049737176&timestamp=1536671785625&sign=c35224f910f7def578bb71186b87fc4d&channel=1060&version=10.7.0.68&ver=180904&linkMiguServer=0
    @POST("andapi/book/info/?_=1536671225796")
    @FormUrlEncoded
    Observable<DataResponse<ContentInfo>> getBookInfo(@Field("bookId") String bookId, @Field("user_id") int user_id, @FieldMap Map<String, Object> map);

    /**********BOOK_INFO_API   end*************/
}
