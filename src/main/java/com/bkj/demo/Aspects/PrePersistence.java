package com.bkj.demo.Aspects;

import com.bkj.demo.Entity.Rules;
import com.bkj.demo.Repository.RulesRepository;
import com.bkj.demo.Service.RulesExecutor;
import com.bkj.demo.Service.RulesScriptService;
import com.bkj.demo.Service.RulesServiceHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class PrePersistence {

    @Autowired
    private RulesExecutor rulesExecutor;
    @Autowired
    private RulesServiceHelper rulesServiceHelper;
    @Autowired
    private RulesRepository rulesRepo;

    @Before("@annotation(com.bkj.demo.Aspects.PrePersistenceAnotation)")
    public void prePersistenceAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        PrePersistenceAnotation annotation = signature.getMethod().getAnnotation(PrePersistenceAnotation.class);

        System.out.println(Arrays.toString(joinPoint.getArgs()));
        System.out.println("Joinpoint Args");
        String org = joinPoint.getArgs()[1].toString();
        String entity = joinPoint.getArgs()[0].toString();
        String userAction = "create";

        List<Rules> rules = rulesServiceHelper.getRuleScript(entity,org, userAction, "Pre Persistence");

        List inputObjects = new ArrayList(Arrays.asList(joinPoint.getArgs()));

        //adding rulesServiceHelper object to inputObjects
//        inputObjects.add(ruleService);
        for (Rules rule : rules) {
            rulesExecutor.execute(inputObjects, rule.getRuleSet());
        }
    }
}

