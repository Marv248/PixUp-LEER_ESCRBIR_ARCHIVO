package org.gerdoc.pixup.jdbc;

import org.gerdoc.pixup.model.Estado;

import java.util.List;

public interface Jdbc<T>
{
    List<T> findAll( );
}
