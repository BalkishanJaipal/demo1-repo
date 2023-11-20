package com.bkj.demo.Service;

import com.bkj.demo.Aspects.PostPersistenceAnotation;
import com.bkj.demo.Aspects.PrePersistenceAnotation;
import com.bkj.demo.Aspects.ValidationAnotation;
import com.bkj.demo.Entity.Driver;
import com.bkj.demo.Entity.Workorder;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RulesExecutor {

    private final KieServices kieServices = KieServices.Factory.get();

    private final KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

    @ValidationAnotation
    @PrePersistenceAnotation
    @PostPersistenceAnotation

    public <T> void execute(List<?> inputObjects, String ruleScript) {

        for (Object object : inputObjects) {
              ruleScript = String.format("import%s;\n%s", object.getClass().toString().substring(5), ruleScript);
            }
       // System.out.println(ruleScript);

        kieFileSystem.write("src/main/resources/rules.drl", ResourceFactory.newReaderResource(new StringReader(ruleScript)));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();

        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            throw new IllegalStateException("Error in rules: " + results.getMessages());
        }

        KieContainer kieContainer = kieServices.newKieContainer(kieBuilder.getKieModule().getReleaseId());
        KieSession kieSession = kieContainer.newKieSession();

        // After creating the KieSession and before inserting the input objects:

        Map<Workorder, List<Driver>> selectedDrivers = new HashMap<>();


        for (Object input : inputObjects) {
            kieSession.insert(input);
        }

        kieSession.fireAllRules();
        kieSession.dispose();
    }

}



