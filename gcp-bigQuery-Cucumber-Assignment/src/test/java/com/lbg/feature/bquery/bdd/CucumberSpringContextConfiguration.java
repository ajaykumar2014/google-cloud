package com.lbg.feature.bquery.bdd;

import com.lbg.feature.bquery.GcpBgFeatureApplication;
import org.junit.Before;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.logging.Logger;

@SpringBootTest
@ContextConfiguration(classes = GcpBgFeatureApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringContextConfiguration {
    
}