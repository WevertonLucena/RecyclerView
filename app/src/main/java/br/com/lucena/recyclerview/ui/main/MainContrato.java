package br.com.lucena.recyclerview.ui.main;

import java.util.List;

import br.com.lucena.recyclerview.domain.Repositorio;

public interface MainContrato {

    interface  Model{
        void buscarRepositorios(int page);
    }

    interface View{
        void mostrarRepositorios(List<Repositorio> repositoriosList);
        void repositorioClicado(Repositorio repositorio);
        void mostrarBarraDeProgresso(boolean mostrar);
    }

    interface Presenter{
        void solicitarRepositorios(int page);
        void carregarRepositorios(List<Repositorio> repositoriosList);
    }
}
