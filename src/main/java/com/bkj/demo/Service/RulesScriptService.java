package com.bkj.demo.Service;

import com.bkj.demo.Config.DroolsConfig;
import com.bkj.demo.Entity.Driver;
import com.bkj.demo.Entity.Rules;
import com.bkj.demo.Entity.Workorder;
import com.bkj.demo.Repository.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RulesScriptService {

    @Autowired
    public RulesRepository repos;


    public String getRuleset(String entity, String org, String userAction, String ruleType){

        List <Rules> rules= repos.findByEntityAndOrgAndUserActionAndRuleType(entity, org,  userAction, ruleType);
        String str;

        if (rules.isEmpty())
        {
            return "No rule found";
        }

        else if(rules.size()==1) {
            str = rules.get(0).getRuleSet();
            return rules.get(0).getRuleSet();
        }
        else return "other";

    }

    public ResponseEntity<?> rules(List<Workorder> workorders,List<Driver>drivers) throws IOException {

        String ruleSet =  getRuleset("workorder2", "olusa","create", "rule2");


        Map<Workorder,List<Driver>> SelectedDriver = DroolsConfig.applyRules(ruleSet,workorders,drivers);
        return ResponseEntity.ok(SelectedDriver);


    }


}
