package br.com.lucena.recyclerview.service;

import java.util.List;

import br.com.lucena.recyclerview.domain.GitHub;
import br.com.lucena.recyclerview.domain.Pull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wtonl on 18/08/2018.
 */

public interface GitApi {

    public static final String BASE_URL = "https://api.github.com/";

    @GET("search/repositories")
    Call<GitHub> listReposotories(@Query(value = "q", encoded = true) String q, @Query("sort") String sort, @Query("page") int page);

    @GET("repos/{autor}/{repositorio}/pulls")
    Call<List<Pull>> listPulls(@Path("autor") String autor, @Path("repositorio") String repositorio);
}
