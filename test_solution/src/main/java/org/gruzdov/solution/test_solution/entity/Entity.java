package org.gruzdov.solution.test_solution.entity;

import java.io.Serializable;

/**
 * @author Vladislav Gruzdov
 */
public interface Entity<T> extends Serializable {
    T getId();
}
