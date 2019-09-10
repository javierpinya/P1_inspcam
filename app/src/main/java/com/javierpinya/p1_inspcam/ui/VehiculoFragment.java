package com.javierpinya.p1_inspcam.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.javierpinya.p1_inspcam.ActualizarVehiculoDialogFragment;
import com.javierpinya.p1_inspcam.NuevoVehiculoDialogFragment;
import com.javierpinya.p1_inspcam.NuevoVehiculoDialogViewModel;
import com.javierpinya.p1_inspcam.R;
import com.javierpinya.p1_inspcam.db.entity.PrimerComponenteEntity;

import java.util.ArrayList;
import java.util.List;


public class VehiculoFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<PrimerComponenteEntity> vehiculoList;
    private MyVehiculoRecyclerViewAdapter adapterVehiculo;
    private NuevoVehiculoDialogViewModel vehiculoViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VehiculoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static VehiculoFragment newInstance(int columnCount) {
        VehiculoFragment fragment = new VehiculoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehiculo_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            vehiculoList = new ArrayList<>();

            adapterVehiculo = new MyVehiculoRecyclerViewAdapter(vehiculoList, getActivity());
            recyclerView.setAdapter(adapterVehiculo);

            lanzarViewModel();

            adapterVehiculo.setOnItemClickListener(new MyVehiculoRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(PrimerComponenteEntity tractora) {
                    int id = tractora.getId();
                    String matricula = tractora.getMatricula();
                    int tara = tractora.getTara();
                    int pesomax = tractora.getPesoMaximo();
                    int chip = tractora.getChip();
                    String tipo = tractora.getTipoComponente();
                    String nacion = tractora.getCodNacion();
                    boolean soloGasoleo = tractora.isSoloGasoleo();
                    boolean bloqueado = tractora.isBloqueado();
                    boolean queroseno = tractora.isQueroseno();


                    mostrarDialogEditarVehiculo(id, matricula, tara, pesomax, chip, tipo, nacion, soloGasoleo, bloqueado, queroseno);
                }
            });

        }
        return view;
    }


    private void lanzarViewModel() {
        vehiculoViewModel = ViewModelProviders.of(getActivity())
                .get(NuevoVehiculoDialogViewModel.class);
        vehiculoViewModel.getAllTractoras().observe(getActivity(), new Observer<List<PrimerComponenteEntity>>() {
            @Override
            public void onChanged(List<PrimerComponenteEntity> primerComponenteEntities) {
                adapterVehiculo.setNuevosVehiculos(primerComponenteEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_vehiculo_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_vehiculo:
                mostrarDialogNuevoVehiculo();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarDialogNuevoVehiculo() {
        FragmentManager f = getActivity().getSupportFragmentManager();
        NuevoVehiculoDialogFragment dialogNuevoVehiculo = new NuevoVehiculoDialogFragment();
        dialogNuevoVehiculo.show(f, "NuevoVehiculoDialogFragment");
    }

    private void mostrarDialogEditarVehiculo(int id, String matricula, int tara, int pesomax, int chip, String tipo, String nacion, boolean soloGasoleo, boolean bloqueado, boolean queroseno) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("matricula", matricula);
        bundle.putInt("tara", tara);
        bundle.putInt("pesomax", pesomax);
        bundle.putInt("chip", chip);
        bundle.putString("tipo", tipo);
        bundle.putString("nacion", nacion);
        bundle.putBoolean("soloGasoleo", soloGasoleo);
        bundle.putBoolean("bloqueado", bloqueado);
        bundle.putBoolean("queroseno", queroseno);
        FragmentManager f = getActivity().getSupportFragmentManager();
        ActualizarVehiculoDialogFragment dialogActualizarVehiculo = new ActualizarVehiculoDialogFragment();
        dialogActualizarVehiculo.setArguments(bundle);
        dialogActualizarVehiculo.show(f, "ActualizarVehiculoDialogFragment");
    }



}
