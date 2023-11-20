package com.bkj.demo.Service;


import com.bkj.demo.Entity.Driver;
import com.bkj.demo.Entity.Rules;
import com.bkj.demo.Entity.Workorder;
//import com.bkj.demo.Utilities.DriverGenerator;
//import com.bkj.demo.Utilities.WorkOrderGenerator;
import com.bkj.demo.Repository.RulesRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class RulesServiceHelper {

    @Autowired
    RulesRepository rulesRepo;

    @Autowired
    private final RulesExecutor ruleExecutor;

    public RulesServiceHelper(RulesExecutor ruleExecutor) {
        this.ruleExecutor = ruleExecutor;

    }

    public List<Rules> getRuleScript(String entity,String org, String userAction, String ruleType)
    {
        return rulesRepo.findByEntityAndOrgAndUserActionAndRuleType(entity,org,userAction,ruleType);
    }

    public Map<Workorder, List<Driver>> executeRules(String entity,String org, String userAction, String ruleType,List<Workorder> workorders,List<Driver> drivers) {

        List<Object> inputObjects = new ArrayList<>();
        inputObjects.addAll(workorders);
        inputObjects.addAll(drivers);

        List<Rules> rules = rulesRepo.findSpecificRule(entity,org,userAction,ruleType);

        String ruleScript="";
        try{
            ruleScript += rules.get(0).getRuleSet();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(ruleScript.isEmpty()){
            System.out.println("no rule found");
        }

        Map<Workorder, List<Driver>> selectedDrivers = new HashMap<>();

        inputObjects.add(selectedDrivers);

        ruleExecutor.execute(inputObjects, ruleScript);
        return selectedDrivers;
    }



}

// String ruleScript = rulesScriptService.getRuleScript("workorder","nile","create","validation");   //rule1
//  String ruleScript  = rulesScriptService.getRuleScript("workorder2","olusa","create","validation"); //rule2
//  String ruleScript = rulesScriptService.getRuleScript("workorder3","olusa","update","validation");   //rule3
//   String ruleScript = rulesScriptService.getRuleScript("workorder4","olusa","delete","validation");   //rule4
//  String ruleScript = rulesScriptService.getRuleScript("workorder5","olusa","delete","validation");   //rule5




