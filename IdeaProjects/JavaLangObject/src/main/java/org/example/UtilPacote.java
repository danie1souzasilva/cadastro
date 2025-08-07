package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UtilPacote {
    public static void main(String[] args) {
        ArrayList lista = new ArrayList();
        int o;
        lista.add(5);
        lista.add(4);
        lista.add(1, 8);

        System.out.println(lista.isEmpty());
        System.out.println(lista);
        for (int i = 0; i < lista.size(); i++){
            Object oref = lista.get(i);
            System.out.println(oref);
        }
        for (Object oref : lista){
            System.out.println(oref);

        }

        List<Integer> inteiro = new ArrayList<>();
        inteiro.add(10);
        System.out.println(inteiro);
        Integer idadeRef = Integer.valueOf(27);
        idadeRef.intValue();
        System.out.println(idadeRef);
        System.out.println(idadeRef.doubleValue());
        System.out.println(idadeRef.byteValue());
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.BYTES);
        System.out.println(lista);



    }
}
