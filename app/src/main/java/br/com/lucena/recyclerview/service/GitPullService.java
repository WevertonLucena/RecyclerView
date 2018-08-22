package br.com.lucena.recyclerview.service;

import android.util.Log;

import java.util.List;

import br.com.lucena.recyclerview.domain.Pull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitPullService extends GitService {

    private List<Pull> pullList;

    private LoadPullsListener listener;

    public void buscaPulls(String autor, String repositorio){
        GitApi gitApi = retrofit.create(GitApi.class);
        Call<List<Pull>> pulls = gitApi.listPulls(autor,repositorio);

        pulls.enqueue(new Callback<List<Pull>>() {
            @Override
            public void onResponse(Call<List<Pull>> call, Response<List<Pull>> response) {
                if (response.isSuccessful()){
                    pullList = response.body();
                    listener.onLoad(pullList);
                }
            }

            @Override
            public void onFailure(Call<List<Pull>> call, Throwable t) {
                Log.e("App", "Erro: " + t.getMessage() );
            }
        });
    }

    public void setListener(LoadPullsListener listener) {
        this.listener = listener;
    }

    public interface LoadPullsListener{
        void onLoad(List<Pull> pullList);
    }
}
