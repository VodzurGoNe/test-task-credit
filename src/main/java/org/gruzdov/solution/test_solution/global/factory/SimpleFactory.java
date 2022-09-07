package org.gruzdov.solution.test_solution.global.factory;

import org.gruzdov.solution.test_solution.entity.Entity;

public class SimpleFactory {

    public static <T extends Entity<?>> T create(Class<T> entityClass) {
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to create entity instance", e);
        }
    }

}
