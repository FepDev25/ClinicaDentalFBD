package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Modelos.Impuesto;
import com.cultodeportivo.Modelos.Usuario;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class GlobalValues {
    public static Usuario userApp;
    public static Impuesto iva;
    public static int numeroFactura;
    
    public static double roundToTwoDecimals(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
