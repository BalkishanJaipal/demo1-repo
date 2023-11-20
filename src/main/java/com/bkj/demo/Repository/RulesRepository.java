package com.bkj.demo.Repository;

import com.bkj.demo.Entity.Rules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulesRepository extends JpaRepository<Rules,Integer> {
   public List<Rules> findByEntityAndOrgAndUserActionAndRuleType(String entity, String org, String userAction, String ruleType);


    @Query(value = "SELECT * FROM rules WHERE entity = :entity AND org = :org AND rule_type = :ruleType AND user_action = :userAction", nativeQuery = true)
    List<Rules> findSpecificRule(
            @Param("entity") String entity,
            @Param("org") String org,
            @Param("userAction") String userAction,
            @Param("ruleType") String ruleType
    );
}
