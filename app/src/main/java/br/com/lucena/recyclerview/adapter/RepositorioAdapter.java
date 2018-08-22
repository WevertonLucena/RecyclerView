package br.com.lucena.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.lucena.recyclerview.R;
import br.com.lucena.recyclerview.domain.Repositorio;

public class RepositorioAdapter extends RecyclerView.Adapter<RepositorioAdapter.VHolder>  {


    List<Repositorio> data;
    private final OnItemClickListener listener;
    Context context;
    Picasso picasso;

    public RepositorioAdapter(List<Repositorio> data, Context context, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
        picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(true);
    }

    @Override
    public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.repositorio_item, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(VHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class VHolder extends RecyclerView.ViewHolder {
        TextView txtNome;
        TextView txtDescricao;
        TextView txtForks;
        TextView txtStars;
        ImageView imgAvatar;
        TextView txtAutor;

        public VHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtForks = itemView.findViewById(R.id.txtForks);
            txtStars = itemView.findViewById(R.id.txtStars);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            txtAutor = itemView.findViewById(R.id.txtAutor);
        }

        public void bind(final Repositorio repositorio, final OnItemClickListener listener) {
            txtNome.setText(repositorio.name);
            txtDescricao.setText(repositorio.description);
            txtForks.setText(String.valueOf(repositorio.forks));
            txtStars.setText(String.valueOf(repositorio.stars));
            txtAutor.setText(repositorio.autor.login);
            Picasso.with(itemView.getContext()).load(repositorio.autor.avatar).into(imgAvatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(repositorio);
                }
            });
        }


    }
    public interface OnItemClickListener {
        void onItemClick(Repositorio repositorio);
    }
}
