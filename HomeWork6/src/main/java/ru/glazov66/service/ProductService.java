package ru.glazov66.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import ru.glazov66.dto.Product;

import java.util.ArrayList;

public interface ProductService {
    @GET("products")
    Call<ArrayList<Product>> getProducts();

    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @PUT("products")
    Call<Product> putProduct(@Body Product product);

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Integer id);

    @DELETE("products/{id}")
    Call<Product> deleteProduct(@Path("id") Integer id);

    //  возможно Call<ResponseBody  > deleteProduct(@Path("id") Integer id);

}

