package br.com.lucena.recyclerview.ui.pull;

import java.util.List;

import br.com.lucena.recyclerview.adapter.PullAdapter;
import br.com.lucena.recyclerview.domain.Pull;
import br.com.lucena.recyclerview.model.PullModel;

public class PullsPresenter implements PullsContrato.Presenter
        , PullAdapter.OnItemClickListener {

    private PullsContrato.View view;
    private PullsContrato.Model model;

    public PullsPresenter(PullsContrato.View view){
        this.view = view;
        model = new PullModel(this);
    }

    @Override
    public void buscarPulls(String autor, String repositorio) {
        view.mostrarBarraDeProgresso(true);
        model.buscarPulls(autor,repositorio);
    }

    @Override
    public void carregarPulls(List<Pull> pullList) {
        view.mostrarPulls(pullList);
        view.mostrarBarraDeProgresso(false);
    }

    @Override
    public void onItemClick(Pull pull) {
        view.pullClicado(pull);
    }
}
