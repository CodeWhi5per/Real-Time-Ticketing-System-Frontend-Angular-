package com.example.Real.Time.Ticketing.System.dao;

import com.example.Real.Time.Ticketing.System.domain.SystemConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class SystemConfigDaoImpl implements SystemConfigDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public SystemConfig saveSystemConfig(SystemConfig systemConfig) {
        try {
            em.persist(systemConfig);
            return systemConfig;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SystemConfig getLatestSystemConfig() {
        try {
            // Get CriteriaBuilder instance from EntityManager
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

            // Create a CriteriaQuery for SystemConfig
            CriteriaQuery<SystemConfig> criteriaQuery = criteriaBuilder.createQuery(SystemConfig.class);

            // Define the root of the query (SystemConfig entity)
            Root<SystemConfig> root = criteriaQuery.from(SystemConfig.class);

            // Create a Predicate for sorting by date or timestamp field in descending order
            // Assuming the entity has a field like "createdAt" that holds the timestamp of the config
            Predicate latestPredicate = criteriaBuilder.isNotNull(root.get("createdDate"));

            // Apply ordering by the createdAt field in descending order to get the latest config
            criteriaQuery.select(root)
                    .where(latestPredicate)
                    .orderBy(criteriaBuilder.desc(root.get("createdDate")));

            // Execute the query and get the first result
            return em.createQuery(criteriaQuery)
                    .setMaxResults(1)  // Ensure we only get the latest one
                    .getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
