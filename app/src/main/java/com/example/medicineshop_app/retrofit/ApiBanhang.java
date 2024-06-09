package com.example.medicineshop_app.retrofit;
import com.example.medicineshop_app.model.CommentModel;
import com.example.medicineshop_app.model.DonHangModel;
import com.example.medicineshop_app.model.LoaiSpModel;
import com.example.medicineshop_app.model.SanPhamMoiModel;
import com.example.medicineshop_app.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableSerialized;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBanhang {
//    GET DATA
    @GET("get_loai_sp.php")
    Observable<LoaiSpModel> getLoaiSP();

    @GET("getspmoi.php")
    Observable<SanPhamMoiModel> getSpMoi();
//POST DATA
    @POST("details.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> getSanPham(
          @Field("page") int page,
          @Field("loai") int loai
        );

    @POST("dangKy.php")
    @FormUrlEncoded
    Observable<UserModel> dangKy(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );
    @POST("dangNhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("email") String email,
            @Field("pass") String pass
    );
    @POST("reset.php")
    @FormUrlEncoded
    Observable<UserModel> resetPass(
            @Field("email") String email
    );
    @POST("donhang.php")
    @FormUrlEncoded
    Observable<UserModel> createOrder(
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("tongtien") String tongtien,
            @Field("iduser") int id,
            @Field("diachi") String diachi,
            @Field("soluong") int soluong,
            @Field("chitiet") String chitiet
    );

    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<DonHangModel> xemDonHang(
            @Field("iduser") int id // ở php lên đi
    );

    @POST("search.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> search(
            @Field("search") String search
    );
    @POST("comment.php")
    @FormUrlEncoded
    Observable<CommentModel> comment(
            @Field("idsp") int idsp
    );
    @POST("postComment.php")
    @FormUrlEncoded
    Observable<CommentModel> postComment(
            @Field("user_id") int user_id,
            @Field("idsp") int idsp,
            @Field("comment") String comment
    );
}
