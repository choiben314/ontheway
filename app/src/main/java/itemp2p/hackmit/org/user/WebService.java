package itemp2p.hackmit.org.user;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebService {
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://something.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final WebService implementation = retrofit.create(WebService.class);
        @FormUrlEncoded
        @POST("users/request/submit")
        public Call<Integer> submitRequest(
                @Field("item") String item,
                @Field("fromAddress") String from,
                @Field("toAddress") String to,
                @Field("byTime") String by);

        @FormUrlEncoded
        @POST("users/request/{requestid}/cancel")
        public Call<Integer> submitCancel(
                @Path("requestid") int requestid
                );

        @FormUrlEncoded
        @GET("users/request/{requestid}/riders-that-selected")
        public void driversThatSelected(
                @Field("id") String id,
                @Path("requestid") int requestid
                );

        @FormUrlEncoded
        @POST("users/request/{requestid}/ride/{rideid}/confirm")
        public void confirmRide(
                @Path("requestid") int requestid,
                @Path("rideid") int rideid
                );

}
