package com.example.Real.Time.Ticketing.System.dao;

import com.example.Real.Time.Ticketing.System.domain.Counts;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class CountDaoImpl implements CountDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Counts updateCount(Counts counts){
        try {
            em.merge(counts);
            return counts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Counts getLatestCount(){
        try {
            // Get CriteriaBuilder instance from EntityManager
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

            // Create a CriteriaQuery for SystemConfig
            CriteriaQuery<Counts> criteriaQuery = criteriaBuilder.createQuery(Counts.class);

            // Define the root of the query (SystemConfig entity)
            Root<Counts> root = criteriaQuery.from(Counts.class);

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
