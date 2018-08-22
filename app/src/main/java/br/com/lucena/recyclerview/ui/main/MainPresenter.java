package br.com.lucena.recyclerview.ui.main;

import java.util.List;

import br.com.lucena.recyclerview.adapter.RepositorioAdapter;
import br.com.lucena.recyclerview.domain.Repositorio;
import br.com.lucena.recyclerview.model.RepositorioModel;

public class MainPresenter implements MainContrato.Presenter,
        RepositorioAdapter.OnItemClickListener{

    private MainContrato.View view;
    private MainContrato.Model model;

    public MainPresenter(MainContrato.View view){
        this.view = view;
        model = new RepositorioModel(this);
    }

    @Override
    public void solicitarRepositorios(int page) {
        view.mostrarBarraDeProgresso(true);
        model.buscarRepositorios(page);
    }

    @Override
    public void onItemClick(Repositorio repositorio) {
        view.repositorioClicado(repositorio);
    }

    @Override
    public void carregarRepositorios(List<Repositorio> repositoriosList) {
        view.mostrarRepositorios(repositoriosList);
        view.mostrarBarraDeProgresso(false);
    }
}
