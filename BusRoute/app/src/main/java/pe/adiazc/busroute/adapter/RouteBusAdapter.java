package pe.adiazc.busroute.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.adiazc.busroute.R;
import pe.adiazc.busroute.entity.Route;

public class RouteBusAdapter extends RecyclerView.Adapter<RouteBusAdapter.RouteBusViewHoder> {
    public class RouteBusViewHoder extends RecyclerView.ViewHolder{
        TextView tvVehicleID, tvBlockNumber,tvDireccionText;

        public RouteBusViewHoder(@NonNull View itemView) {
            super(itemView);
                tvVehicleID = itemView.findViewById(R.id.tvVehiculoid);

                tvBlockNumber = itemView.findViewById(R.id.tvBlockNumber);

                tvDireccionText = itemView.findViewById(R.id.tvDireccionText);

        }
    }

    private  final LayoutInflater layoutInflater;
    private List<Route> listRoute;
    public Context context;

    public RouteBusAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(this.context);

    }

    @NonNull
    @Override
    public RouteBusViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = this.layoutInflater.inflate(R.layout.bus_item_rv,viewGroup,false);
        return new RouteBusViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteBusViewHoder routeBusViewHoder, int i) {
        if(listRoute != null){
            Route route = listRoute.get(i);
            routeBusViewHoder.tvVehicleID.setText("VehicleID : "+route.getVehicleID()+"");
            routeBusViewHoder.tvBlockNumber.setText("BlockNumber : "+route.getBlockNumber());
            routeBusViewHoder.tvDireccionText.setText("DireccionText : "+route.getDirectionText());
        }
    }

    @Override
    public int getItemCount() {
        if(listRoute != null){
            return listRoute.size();
        }
        return 0;
    }
    public void setLisRoute(List<Route> routers){
        listRoute = routers;
        notifyDataSetChanged();
    }

}
