package com.javierpinya.p1_inspcam;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.javierpinya.p1_inspcam.db.entity.PrimerComponenteEntity;

public class NuevoVehiculoDialogFragment extends DialogFragment {



    private EditText etmatricula, etnacion, ettara, etpesomax, etchip, ettipo;
    private CheckBox etgasoleo, etbloqueado, etjeta;
    private View view;


    public static NuevoVehiculoDialogFragment newInstance() {
        return new NuevoVehiculoDialogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nuevo_vehiculo_dialog_fragment, container, false);
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

        builder.setTitle("Nuevo Vehículo");
        builder.setMessage("Introduzca los datos del nuevo vehículo")
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
                        mViewModel.insertTractora(new PrimerComponenteEntity(matricula, tara, pesomax, chip, tipo, nacion, gasoleo, bloqueado, jeta));

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

        etmatricula = view.findViewById(R.id.etMatricula);
        ettara = view.findViewById(R.id.ettara);
        etpesomax = view.findViewById(R.id.etpesomax);
        etchip = view.findViewById(R.id.etchip);
        ettipo = view.findViewById(R.id.ettipo);
        etnacion = view.findViewById(R.id.etnacion);
        etgasoleo = view.findViewById(R.id.cbgasoleo);
        etbloqueado = view.findViewById(R.id.cbbloqueo);
        etjeta = view.findViewById(R.id.cbqueroseno);

        builder.setView(view);

        return builder.create();
    }
}
