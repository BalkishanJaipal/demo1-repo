package com.bkj.demo.Config;

import com.bkj.demo.Entity.Driver;
import com.bkj.demo.Entity.Workorder;
import com.bkj.demo.Service.RulesScriptService;
import com.bkj.demo.Utilities.DateHelper;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class DroolsConfig {


    private static final KieServices kieServices = KieServices.Factory.get();

    private static KieFileSystem getKieFileSystem() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("Rules.drl"));
        return kieFileSystem;
    }

    @Bean
    public static KieContainer getKieContainer() {
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public KieSession getKieSession() {
        return getKieContainer().newKieSession();
    }


    public static Map<Workorder, List<Driver>> applyRules(String drlContent, List<Workorder> wo, List<Driver> drivers) throws IOException {
        if (drlContent == null || wo == null || drivers == null) {
            throw new IllegalArgumentException("Drl content or object cannot be null");
        }
        Map<Workorder, List<Driver>> SelectedDriver = new HashMap<>();


        KieSession kiesession = null;
        kiesession = getKieContainer().newKieSession();


        for (Workorder workorder : wo) {
            kiesession.insert(workorder);
        }

        for (Driver driver : drivers) {
            kiesession.insert(driver);
        }
        kiesession.insert(SelectedDriver);
        kiesession.insert(new DateHelper(new Date()));
        kiesession.fireAllRules();

        return SelectedDriver;

    }
}