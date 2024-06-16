package com.cultodeportivo.Control;

import java.util.ArrayList;
import com.cultodeportivo.Modelos.*;
import java.sql.SQLException;

public class Control {
    private Operaciones operaciones;

    public Control() {
        this.operaciones = new Operaciones();
    }

    public ArrayList<Usuario> obtenerUsuarios(){
        return operaciones.obtenerUsuarios();
    }
}