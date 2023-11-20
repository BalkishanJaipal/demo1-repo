package com.bkj.demo.Controller;

import com.bkj.demo.Entity.Driver;
import com.bkj.demo.Entity.Rules;
import com.bkj.demo.Entity.Workorder;
import com.bkj.demo.Repository.DriverRepo;
import com.bkj.demo.Repository.RulesRepository;
import com.bkj.demo.Repository.WorkorderRepo;
import com.bkj.demo.Service.RulesExecutor;
import com.bkj.demo.Service.RulesScriptService;
import com.bkj.demo.Service.RulesServiceHelper;
import com.bkj.demo.Utilities.RequestData;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class RuleScriptController {


    @Autowired
    RulesRepository rulesRepo;
    @Autowired
    RulesScriptService ruleService;
    @Autowired
    RulesServiceHelper rulesServiceHelper;



    @PostMapping("/runRules")
    public ResponseEntity<?>  runRules(
            @RequestParam String entity,
            @RequestParam String org,
            @RequestParam String userAction,
            @RequestParam String ruleType,
            @RequestBody RequestData requestData
    ){
        List<Workorder> workorders = requestData.getWorkorders();
        List<Driver> drivers = requestData.getDrivers();

        Map<Workorder,List<Driver>> selectedDrivers = rulesServiceHelper.executeRules(entity,org,userAction,ruleType,workorders,drivers);
        return ResponseEntity.ok(selectedDrivers);
    }




    @GetMapping("/rules")
    public List<Rules> getAllRules()
    {
        return  rulesRepo.findAll();
    }

    @GetMapping("/rules/{id}")
    public ResponseEntity<Rules> getRule(@PathVariable int id) {

        try{
            Optional<Rules> rule = rulesRepo.findById(id);
            return ResponseEntity.ok(rule.get());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }


    @PostMapping("/rules/create")
    public ResponseEntity<Rules> createRule(@RequestBody Rules rule)
    {
        rulesRepo.save(rule);
        return ResponseEntity.ok(rule);

    }


    //UPDATE
    @PutMapping("rules/update/{id}")
    public ResponseEntity<Rules> updateRule(@PathVariable int id) {
        try {
            Optional<Rules> rule = rulesRepo.findById(id);
            rule.get().setRuleType("post persistence");
            rulesRepo.save(rule.get());
            return ResponseEntity.ok(rule.get());
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    //Delete
    @DeleteMapping("/rules/delete/{id}")
    public Object deleteRule(@PathVariable int id)
    {
        try
        {
            Optional<Rules> rule =  rulesRepo.findById(id);
            rulesRepo.delete(rule.get());
            return "Rule deleted";
        }

        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }




}



//    @PostMapping("/Testing")
//    public ResponseEntity<?> Rule(@RequestBody RequestData requestData) {
//
//        List<Driver> drivers = requestData.getDrivers();
//        List<Workorder> workorders = requestData.getWorkorders();
//        Map<Workorder,List<Driver>> SelectedDrivers = rulesServiceHelper.executeRules(drivers,workorders);
//        return  ResponseEntity.ok(SelectedDrivers);
//    }