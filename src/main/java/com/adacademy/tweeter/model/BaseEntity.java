package com.adacademy.tweeter.model;

/**
 * Base entity interface for all stored object in the system.
 */
public interface BaseEntity {
    /**
     * Method return ID of Entities
     * @return
     */
    Long getId();

    /**
     *Method set id value of entity from parameter id
     * @param id
     */
    void setId(Long id);

}
