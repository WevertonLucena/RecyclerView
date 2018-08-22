package br.com.lucena.recyclerview.ui.main;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.com.lucena.recyclerview.R;
import br.com.lucena.recyclerview.ui.pull.PullsActivity;
import br.com.lucena.recyclerview.adapter.RepositorioAdapter;
import br.com.lucena.recyclerview.domain.Repositorio;

public class MainActivity extends AppCompatActivity implements MainContrato.View {

    private LinearLayoutManager manager;
    private RepositorioAdapter adapter;
    private Boolean isScrolling = false, isSaved = false;
    private int currentItems, totalItems, scrollOutItems;
    private ProgressBar progressBar;
    private List<Repositorio> repositorios;
    private int page;
    private final int PULL_ACTIVITY = 1;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        progressBar = findViewById(R.id.progressBar);
        manager = new LinearLayoutManager(this);
        repositorios = new ArrayList<>();
        page = 1;

        presenter = new MainPresenter(this);

        if (savedInstanceState != null){
            repositorios = savedInstanceState.getParcelableArrayList("repositorios");
            page = savedInstanceState.getInt("page");
            isSaved = true;
        }

        adapter = new RepositorioAdapter(repositorios, this, presenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false;
                    fetchData();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("page", page);
        outState.putParcelableArrayList("repositorios", (ArrayList<? extends Parcelable>) repositorios);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (! isSaved) {
            presenter.solicitarRepositorios(page);
        }
    }

    private void fetchData() {
        page++;
        presenter.solicitarRepositorios(page);
    }

    @Override
    public void mostrarRepositorios(List<Repositorio> repositoriosList) {
        repositorios.addAll(repositoriosList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void repositorioClicado(Repositorio repositorio) {
        Intent i = new Intent(this,PullsActivity.class);
        i.putExtra("repositorio",repositorio.name);
        i.putExtra("autor",repositorio.autor.login);
        startActivityForResult(i, PULL_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PULL_ACTIVITY){
            isSaved = true;
        }
    }

    @Override
    public void mostrarBarraDeProgresso(boolean mostrar) {
        if (mostrar)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }
}
