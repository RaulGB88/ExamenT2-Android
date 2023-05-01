package com.practicas.pmdm.mybank.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loren on 08/10/15.
 */
public interface PojoDAO {

    public long add(Object obj);

    public int update(Object obj);

    public void delete(Object obj);

    public Object search(Object obj);

    public List getAll();
}
