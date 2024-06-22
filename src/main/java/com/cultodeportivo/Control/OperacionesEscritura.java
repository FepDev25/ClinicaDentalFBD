package com.cultodeportivo.Control;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.cultodeportivo.Modelos.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperacionesEscritura {
    private ConexionOracle conexion;
    private PreparedStatement myStatement;
    
    public OperacionesEscritura(){
        this.conexion = ConexionOracle.getInstance();
    }
}
