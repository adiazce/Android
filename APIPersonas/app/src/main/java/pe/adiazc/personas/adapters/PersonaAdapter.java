package pe.adiazc.personas.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

import pe.adiazc.personas.R;
import pe.adiazc.personas.dto.Persona;


public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.LayoutPersona> {

    List<Persona> personas;

    public PersonaAdapter(List<Persona> personas) {
        this.personas = personas;
    }

    @NonNull
    @Override
    public LayoutPersona onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup,false);
        LayoutPersona layoutPersona = new LayoutPersona(view);
        return layoutPersona;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutPersona layoutPersona, int i) {
        Persona persona = this.personas.get(i);
        layoutPersona.txtName.setText(persona.getName());
        layoutPersona.txtEmail.setText(persona.getEmail());
        layoutPersona.txtPhone.setText(persona.getPhone());
        Glide.with(layoutPersona.itemView.getContext()).load(persona.getPhoto()).into(layoutPersona.tvPhoto);

    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public class LayoutPersona extends RecyclerView.ViewHolder{
        TextView txtName , txtPhone,txtEmail;
        ImageView tvPhoto;

        public LayoutPersona(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvName);
            txtPhone = itemView.findViewById(R.id.tvTelefono);
            txtEmail = itemView.findViewById(R.id.tvEmail);
            tvPhoto = itemView.findViewById(R.id.ivPhoto);

        }
    }
}
