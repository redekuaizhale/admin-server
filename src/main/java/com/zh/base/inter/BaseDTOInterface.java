package com.zh.base.inter;

import java.util.List;

public interface BaseDTOInterface<T,D> {

    public D newDTO(T entity, D dto);

    public List<D> newDTOs(List<T> entityList,List<D> dtoList);
}

