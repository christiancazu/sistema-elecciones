package utils;

import entidades.Ciudadano;

public class VerificarRol {
    public static boolean esAdmin(Ciudadano ciudadano) {
        return ciudadano.getId().equals(1);        
    }
}
