package br.com.lucena.recyclerview.service;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wtonl on 18/08/2018.
 */

public class GitService {

    protected Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


















}
