package br.com.lucena.recyclerview.model;

import java.util.List;

import br.com.lucena.recyclerview.domain.Repositorio;
import br.com.lucena.recyclerview.service.GitRepositorioService;
import br.com.lucena.recyclerview.ui.main.MainContrato;

public class RepositorioModel implements MainContrato.Model, GitRepositorioService.LoadRepositoriosListener {

    private MainContrato.Presenter presenter;

    public RepositorioModel(MainContrato.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void buscarRepositorios(int page) {
        GitRepositorioService service = new GitRepositorioService();
        service.setListener(this);
        service.buscaRepositorios(page);
    }

    @Override
    public void onLoad(List<Repositorio> repositoriosList) {
        presenter.carregarRepositorios(repositoriosList);
    }
}
