package pe.adiazc.googlemap.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import pe.adiazc.googlemap.MapsActivity;
import pe.adiazc.googlemap.R;
import pe.adiazc.googlemap.entidad.Marcador;



public class MarcadorAdapter extends RecyclerView.Adapter<MarcadorAdapter.MarcadorViewHolder> {


    class MarcadorViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre, tvDescripcion;
        private Button btnMapa;
        int id = 0;
        public MarcadorViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.etNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            btnMapa = itemView.findViewById(R.id.btVerMap);

             btnMapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent = new Intent(context ,MapsActivity.class );
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                }
            });


        }
    }

    private  final LayoutInflater layoutInflater;
    private List<Marcador> listMarcador;
    public   Context context;
    public MarcadorAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context =  context;

    }

    @NonNull
    @Override
    public MarcadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.marcador_item,parent,false);
        return new MarcadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MarcadorViewHolder holder, int position) {
        if(listMarcador != null){
            final Marcador marcador = listMarcador.get(position);
            holder.tvNombre.setText(marcador.getNombre()) ;
            holder.tvDescripcion.setText(marcador.getDescripcion());
            holder.id = marcador.getId();



        }
    }

    public void setListMarcador(List<Marcador> marcadores){
        listMarcador = marcadores;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listMarcador != null) {
            return  listMarcador.size();
        }
        return 0;
    }


}
