package com.model.interfaces;

import com.model.Furniture;

public interface Usable {

    public default void useFurniture(Furniture furniture){
    }
}
