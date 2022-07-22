package org.gruzdov.solution.test_solution.entity;

import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * @author Vladislav Gruzdov
 */
@MappedSuperclass
public abstract class BaseGenericIdEntity<T> implements Entity<T> {

    private static final long serialVersionUID = -8400641366148656527L;

    public abstract void setId(T id);

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (other == null || getClass() != other.getClass())
            return false;

        //noinspection unchecked
        return Objects.equals(getId(), ((BaseGenericIdEntity<T>) other).getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
