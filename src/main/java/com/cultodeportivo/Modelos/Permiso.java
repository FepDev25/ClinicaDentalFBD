package com.cultodeportivo.Modelos;

public class Permiso {
    private int prmId;
    private String prmTipo;

    public Permiso(int prmId, String prmTipo) {
        this.prmId = prmId;
        this.prmTipo = prmTipo;
    }

    public int getPrmId() {
        return prmId;
    }

    public void setPrmId(int prmId) {
        this.prmId = prmId;
    }

    public String getPrmTipo() {
        return prmTipo;
    }

    public void setPrmTipo(String prmTipo) {
        this.prmTipo = prmTipo;
    }

    @Override
    public String toString() {
        return "Permiso{" +
                "prmId=" + prmId +
                ", prmTipo='" + prmTipo + '\'' +
                '}';
    }
}
