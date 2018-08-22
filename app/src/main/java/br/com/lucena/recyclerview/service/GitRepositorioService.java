package br.com.lucena.recyclerview.service;

import android.util.Log;
import java.util.List;

import br.com.lucena.recyclerview.domain.GitHub;
import br.com.lucena.recyclerview.domain.Repositorio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitRepositorioService extends GitService {

    List<Repositorio> repositorioList;

    LoadRepositoriosListener listener;

    public void buscaRepositorios(int page){
        GitApi gitApi = retrofit.create(GitApi.class);
        Call<GitHub> repositorios = gitApi.listReposotories("language:Java","stars",page);

        repositorios.enqueue(new Callback<GitHub>() {
            @Override
            public void onResponse(Call<GitHub> call, Response<GitHub> response) {
                if (response.isSuccessful()){
                    repositorioList = response.body().items;
                    listener.onLoad(repositorioList);
                }
            }

            @Override
            public void onFailure(Call<GitHub> call, Throwable t) {
                Log.e("App", "Erro: " + t.getMessage() );
            }
        });


    }

    public void setListener(LoadRepositoriosListener listener) {
        this.listener = listener;
    }


    public interface LoadRepositoriosListener {
        void onLoad(List<Repositorio> repositoriosList);
    }
}
