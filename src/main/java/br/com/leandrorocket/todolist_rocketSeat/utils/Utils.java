package br.com.leandrorocket.todolist_rocketSeat.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    
    // Atribuir todas propriedades nulas
    public static void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropetyNames(source));
    }

    // Pegar todas propriedades Nulas
    public static String[] getNullPropetyNames(Object source){

        // ACESSAR PROPRIEDADES DE UM OBJETO DENTRO DO JAVA
        final BeanWrapper src = new BeanWrapperImpl(source);

        //Obter propriedades do Objeto
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd: pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
