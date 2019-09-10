package com.javierpinya.p1_inspcam;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.javierpinya.p1_inspcam.db.entity.PrimerComponenteEntity;

public class ActualizarVehiculoDialogFragment extends DialogFragment {

    private EditText etmatricula, etnacion, ettara, etpesomax, etchip, ettipo;
    private CheckBox etgasoleo, etbloqueado, etjeta;

    private int id, tara, pesomax, chip;
    private String matricula, tipo, nacion;
    private boolean soloGasoleo, bloqueado, queroseno;
    private View view;


    public static ActualizarVehiculoDialogFragment newInstance() {
        return new ActualizarVehiculoDialogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nuevo_vehiculo_dialog_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());





        builder.setTitle("Actualizar Vehículo");
        builder.setMessage("Introduzca los datos del vehículo a actualizar")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String  matricula = etmatricula.getText().toString();
                        int tara = Integer.valueOf(ettara.getText().toString());
                        int pesomax = Integer.valueOf(etpesomax.getText().toString());
                        int chip = Integer.valueOf(etchip.getText().toString());
                        String tipo = ettipo.getText().toString();
                        String nacion = etnacion.getText().toString();
                        boolean gasoleo = etgasoleo.isChecked();
                        boolean bloqueado = etbloqueado.isChecked();
                        boolean jeta = etjeta.isChecked();


                        NuevoVehiculoDialogViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NuevoVehiculoDialogViewModel.class);
                        PrimerComponenteEntity tractora = new PrimerComponenteEntity(matricula, tara, pesomax, chip, tipo, nacion, gasoleo, bloqueado, jeta);
                        //PrimerComponenteEntity tractora = new PrimerComponenteEntity();
                        tractora.setId(id);
                        mViewModel.updateTractora(tractora);

                        dialogInterface.dismiss();
                    }


                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });


        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.nuevo_vehiculo_dialog_fragment, null);


        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        matricula = bundle.getString("matricula");
        tara = bundle.getInt("tara");
        pesomax = bundle.getInt("pesomax");
        chip = bundle.getInt("chip");
        tipo = bundle.getString("tipo");
        nacion = bundle.getString("nacion");
        soloGasoleo = bundle.getBoolean("soloGasoleo");
        bloqueado = bundle.getBoolean("bloqueado");
        queroseno = bundle.getBoolean("queroseno");
        Toast.makeText(getActivity(), "matricula - " + matricula, Toast.LENGTH_SHORT).show();

        if (id < 0){
            Toast.makeText(getActivity(), "Error al obtener el id del vehículo. Id = " + id, Toast.LENGTH_SHORT).show();
        }

        etmatricula = view.findViewById(R.id.etMatricula);
        ettara = view.findViewById(R.id.ettara);
        etpesomax = view.findViewById(R.id.etpesomax);
        etchip = view.findViewById(R.id.etchip);
        ettipo = view.findViewById(R.id.ettipo);
        etnacion = view.findViewById(R.id.etnacion);
        etgasoleo = view.findViewById(R.id.cbgasoleo);
        etbloqueado = view.findViewById(R.id.cbbloqueo);
        etjeta = view.findViewById(R.id.cbqueroseno);

        etmatricula.setText(matricula);
        ettara.setText(String.valueOf(tara));
        etpesomax.setText(String.valueOf(pesomax));
        etchip.setText(String.valueOf(chip));
        ettipo.setText(tipo);
        etnacion.setText(nacion);
        etgasoleo.setChecked(soloGasoleo);
        etbloqueado.setChecked(bloqueado);
        etjeta.setChecked(queroseno);

        builder.setView(view);

        return builder.create();
    }
}
