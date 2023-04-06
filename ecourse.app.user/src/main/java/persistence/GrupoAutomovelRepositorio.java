/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.GrupoAutomovel;
import java.util.List;

/**
 *
 * @author eapli
 */
public interface GrupoAutomovelRepositorio {

    /**
     * inserts an entity and commits
     *
     * @param entity
     * @return the persisted entity
     */
    public GrupoAutomovel add(GrupoAutomovel entity);

    /**
     * reads an entity given its ID
     *
     * @param id
     * @return
     */
    public GrupoAutomovel findById(Long id);

    /**
     * Returns the List of all entities in the persistence store
     *
     * @return
     */
    public List<GrupoAutomovel> findAll();
}
