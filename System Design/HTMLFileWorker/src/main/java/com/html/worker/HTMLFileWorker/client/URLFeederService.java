package com.html.worker.HTMLFileWorker.client;

import com.html.worker.HTMLFileWorker.model.URL;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.Set;

public interface URLFeederService {

    @POST("/batch")
    public Call<Void> batchPublish(@Body Set<URL> urls);
}
