package com.bkj.demo.Aspects;

import com.bkj.demo.Entity.Rules;
import com.bkj.demo.Repository.RulesRepository;
import com.bkj.demo.Service.RulesExecutor;
import com.bkj.demo.Service.RulesScriptService;
import com.bkj.demo.Service.RulesServiceHelper;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class PostPersistence {

    @Autowired
    RulesExecutor rulesExecutor;
    @Autowired
    private RulesServiceHelper rulesServiceHelper;
    @Autowired
    private RulesRepository rulesRepo;

    @After("@annotation(com.bkj.demo.Aspects.PostPersistenceAnotation)")
    public void postPersistenceAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        PostPersistenceAnotation annotation = signature.getMethod().getAnnotation(PostPersistenceAnotation.class);

        System.out.println(Arrays.toString(joinPoint.getArgs()));
        System.out.println("Joinpoint Args");
        String org = joinPoint.getArgs()[1].toString();
        String entity = joinPoint.getArgs()[0].toString();
        String userAction = "create";

        List<Rules> rules = rulesServiceHelper.getRuleScript(entity,org, userAction, "Post Persistence");

        List inputObjects = new ArrayList(Arrays.asList(joinPoint.getArgs()));


        for (Rules rule : rules) {
            rulesExecutor.execute(inputObjects, rule.getRuleSet());
        }
    }
}
