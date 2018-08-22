package br.com.lucena.recyclerview.model;

import java.util.List;

import br.com.lucena.recyclerview.domain.Pull;
import br.com.lucena.recyclerview.service.GitPullService;
import br.com.lucena.recyclerview.ui.pull.PullsContrato;

public class PullModel implements PullsContrato.Model, GitPullService.LoadPullsListener{

    private PullsContrato.Presenter presenter;

    public PullModel(PullsContrato.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void onLoad(List<Pull> pullList) {
        presenter.carregarPulls(pullList);
    }

    @Override
    public void buscarPulls(String autor, String repositorio) {
        GitPullService service = new GitPullService();
        service.setListener(this);
        service.buscaPulls(autor, repositorio);
    }
}
