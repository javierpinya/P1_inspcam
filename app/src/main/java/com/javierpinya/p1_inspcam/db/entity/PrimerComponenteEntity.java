package com.javierpinya.p1_inspcam.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tacprco")
public class PrimerComponenteEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String matricula;
    //public Date itv;
    //public Date adr;
    public int tara;
    public int pesoMaximo;
    public int chip;
    public String tipoComponente;
    //public Date fechaBaja;
    public String codNacion;
    public boolean soloGasoleo;
    public boolean bloqueado;
    public boolean queroseno;

    public PrimerComponenteEntity(){

    }

    public PrimerComponenteEntity(String matricula, int tara, int pesoMaximo, int chip, String tipoComponente, String codNacion, boolean soloGasoleo, boolean bloqueado, boolean queroseno) {
        this.matricula = matricula;
        //this.itv = itv;
        //this.adr = adr;
        this.tara = tara;
        this.pesoMaximo = pesoMaximo;
        this.chip = chip;
        this.tipoComponente = tipoComponente;
        //this.fechaBaja = fechaBaja;
        this.codNacion = codNacion;
        this.soloGasoleo = soloGasoleo;
        this.bloqueado = bloqueado;
        this.queroseno = queroseno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){this.id = id;}

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public int getTara() {
        return tara;
    }

    public void setTara(int tara) {
        this.tara = tara;
    }

    public int getPesoMaximo() {
        return pesoMaximo;
    }

    public void setPesoMaximo(int pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public int getChip() {
        return chip;
    }

    public void setChip(int chip) {
        this.chip = chip;
    }

    public String getTipoComponente() {
        return tipoComponente;
    }

    public void setTipoComponente(String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }


    public String getCodNacion() {
        return codNacion;
    }

    public void setCodNacion(String codNacion) {
        this.codNacion = codNacion;
    }

    public boolean isSoloGasoleo() {
        return soloGasoleo;
    }

    public void setSoloGasoleo(boolean soloGasoleo) {
        this.soloGasoleo = soloGasoleo;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean isQueroseno() {
        return queroseno;
    }

    public void setQueroseno(boolean queroseno) {
        this.queroseno = queroseno;
    }
}
