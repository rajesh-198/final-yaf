package com.asset.foundation.utility;

import java.util.List;

public interface EntityMapperBase<D, E> {

    E toEntity(D dto);

    E toEntity(D dto, E entity);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

}
