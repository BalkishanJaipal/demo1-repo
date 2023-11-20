package com.bkj.demo.Aspects;

import com.bkj.demo.Entity.Rules;
import com.bkj.demo.Entity.Workorder;
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
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Aspect
@Component
@Order(1)
public class Validation {

    @Autowired
    RulesExecutor rulesExecutor;
    @Autowired
    private RulesServiceHelper rulesServiceHelper;
    @Autowired
    private RulesRepository rulesRepo;

    @Before("@annotation(com.bkj.demo.Aspects.ValidationAnotation)")
    public void validationAdvice(JoinPoint joinPoint) {
        System.out.println("Validation Aspect is executing...");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ValidationAnotation annotation = signature.getMethod().getAnnotation(ValidationAnotation.class);

        // System.out.println(Arrays.toString(joinPoint.getArgs()));

        String org = joinPoint.getArgs()[1].toString();   //getting rule
        String entity = joinPoint.getArgs()[0].toString(); //getting workorder and driver
        String userAction = "create";

        System.out.println("org: " + org + ",entity =" + entity + ",userAction : " + userAction);
        List<Rules> rules = rulesServiceHelper.getRuleScript(entity, org, userAction, "Pre Persistence");
        System.out.println("rules" + rules);

        List inputObjects = new ArrayList(Arrays.asList(joinPoint.getArgs()));

        //adding rulesServiceHelper object to inputObjects
        // inputObjects.add(ruleService);
        for (Rules rule : rules) {
            System.out.println("hello");
            rulesExecutor.execute(inputObjects, rule.getRuleSet());
        }

    }
}



