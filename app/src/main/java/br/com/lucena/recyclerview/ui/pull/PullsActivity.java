package br.com.lucena.recyclerview.ui.pull;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.com.lucena.recyclerview.R;
import br.com.lucena.recyclerview.adapter.PullAdapter;
import br.com.lucena.recyclerview.domain.Pull;


public class PullsActivity extends AppCompatActivity implements PullsContrato.View{

    private PullAdapter adapter;
    private ProgressBar progressBar;
    private List<Pull> pullList;
    private String autor;
    private String repositorio;
    private PullsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulls);

        repositorio = getIntent().getStringExtra("repositorio");
        autor = getIntent().getStringExtra("autor");

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        progressBar = findViewById(R.id.progressBar);
        presenter = new PullsPresenter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        pullList = new ArrayList<>();

        if (savedInstanceState != null){
            pullList = savedInstanceState.getParcelableArrayList("pullList");
        }

        adapter = new PullAdapter(pullList, this, presenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("pullList", (ArrayList<? extends Parcelable>) pullList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pullList != null && !(pullList.size() > 0))
            presenter.buscarPulls(autor,repositorio);
    }

    @Override
    public void mostrarPulls(List<Pull> pulls) {
        pullList.clear();
        pullList.addAll(pulls);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void pullClicado(Pull pull) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pull.url));
        startActivity(browserIntent);
    }

    @Override
    public void mostrarBarraDeProgresso(Boolean mostrar) {
        if (mostrar)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }
}
