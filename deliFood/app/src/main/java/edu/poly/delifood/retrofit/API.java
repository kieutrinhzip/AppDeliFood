package edu.poly.delifood.retrofit;


import edu.poly.delifood.model.DonHangModel;
import edu.poly.delifood.model.NewProductModel;
import edu.poly.delifood.model.UserModel;
import edu.poly.delifood.model.productTypeModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @GET("getloaisp.php")
    Observable<productTypeModel> getLoaiSp();
    @GET("getspnew.php")
    Observable<NewProductModel> getNewProduct();

    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<NewProductModel> getSanPham(
            @Field("page") int page,
            @Field("newType") int newType
    );

    @POST("dangky.php")
    @FormUrlEncoded
    Observable<UserModel> dangky(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangnhap(
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
    Observable<UserModel> createOder(
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
            @Field("iduser") int id
    );

    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<NewProductModel> search(
            @Field("search") String search
    );

}
