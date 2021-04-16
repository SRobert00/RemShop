package org.sci.myshop.services.interfaces;

import org.sci.myshop.dao.ShoppingCart;

public interface ShoppingCartService {
    public void findCartById(Long id);

    void save(ShoppingCart product);
}
