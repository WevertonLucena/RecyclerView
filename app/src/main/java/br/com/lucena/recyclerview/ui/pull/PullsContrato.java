package br.com.lucena.recyclerview.ui.pull;

import java.util.List;

import br.com.lucena.recyclerview.domain.Pull;

public interface PullsContrato {

    interface Model{
        void buscarPulls(String autor, String repositorio);
    }

    interface View{
        void mostrarPulls(List<Pull> pullList);
        void pullClicado(Pull pull);
        void mostrarBarraDeProgresso(Boolean mostrar);
    }

    interface Presenter{
        void buscarPulls(String autor, String repositorio);
        void carregarPulls(List<Pull> pullList);
    }
}
