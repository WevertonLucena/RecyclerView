package br.com.lucena.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.lucena.recyclerview.R;
import br.com.lucena.recyclerview.domain.Pull;


public class PullAdapter extends RecyclerView.Adapter<PullAdapter.VHolder> {

    private List<Pull> data;
    private final OnItemClickListener listener;
    private Context context;
    private Picasso picasso;

    public PullAdapter(List<Pull> data, Context context, OnItemClickListener listener){
        this.data = data;
        this.listener = listener;
        this.context = context;
        picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(true);
    }

    @Override
    public VHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pull_item, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder( VHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    public static class VHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo;
        TextView txtData;
        TextView txtBody;
        TextView txtAutor;
        ImageView imgAvatar;

        public VHolder(View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtBody = itemView.findViewById(R.id.txtBody);
            txtData = itemView.findViewById(R.id.txtData);
            txtAutor = itemView.findViewById(R.id.txtNome);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);

        }

        public void bind(final Pull pull, final OnItemClickListener listener) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            txtTitulo.setText(pull.title);
            txtBody.setText(pull.body);
            txtData.setText(format.format(pull.date));
            txtAutor.setText(pull.user.login);
            Picasso.with(itemView.getContext()).load(pull.user.avatar).into(imgAvatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(pull);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Pull pull);
    }
}
