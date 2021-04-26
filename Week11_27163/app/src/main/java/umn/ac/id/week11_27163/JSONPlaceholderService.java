package umn.ac.id.week11_27163;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholderService {
    @GET("posts")
    Call<ArrayList<Data>> getAllData();
}