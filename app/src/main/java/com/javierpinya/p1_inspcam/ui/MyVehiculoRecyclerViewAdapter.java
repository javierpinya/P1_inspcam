package com.javierpinya.p1_inspcam.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.javierpinya.p1_inspcam.ActualizarVehiculoDialogFragment;
import com.javierpinya.p1_inspcam.NuevoVehiculoDialogViewModel;
import com.javierpinya.p1_inspcam.db.entity.PrimerComponenteEntity;
import com.javierpinya.p1_inspcam.R;

import java.util.List;



public class MyVehiculoRecyclerViewAdapter extends RecyclerView.Adapter<MyVehiculoRecyclerViewAdapter.ViewHolder> {

    private List<PrimerComponenteEntity> mValues;
    private Context ctx;
    private NuevoVehiculoDialogViewModel viewModel;
    private OnItemClickListener listener;
    private int position;


    public MyVehiculoRecyclerViewAdapter(List<PrimerComponenteEntity> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
        viewModel = ViewModelProviders.of((AppCompatActivity)ctx).get(NuevoVehiculoDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_vehiculo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.tvMatricula.setText(holder.mItem.getMatricula());
        holder.tvchip.setText(String.valueOf(holder.mItem.getChip()));

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevosVehiculos(List<PrimerComponenteEntity> nuevosVehiculos){
        this.mValues = nuevosVehiculos;
        notifyDataSetChanged();
    }

    public void setRecyclerId(int position){
        this.position = position;
    }

    public int getRecyclerId(){
        return this.position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView tvMatricula;
        public TextView tvchip;
        public ImageButton btnEdit;
        public PrimerComponenteEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvMatricula = (TextView) view.findViewById(R.id.tvMatricula);
            tvchip = (TextView) view.findViewById(R.id.tvchip);
            btnEdit = view.findViewById(R.id.btnEdit);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    setRecyclerId(position);
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(mValues.get(position));
                    }
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvMatricula.getText() + "'";
        }
    }

    public interface OnItemClickListener{
        void onItemClick(PrimerComponenteEntity tractora);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
