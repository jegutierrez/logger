package com.belatrix.logger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;

public class AppTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void formatEmptyMessage() {
    	String message = "";
    	JobLogger.formatMessage(message);
    }
    
    @Test
    public void formatValidMessage() {
    	String message = "test message";
    	String expectedMessage = DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + message;;
    	assertThat(JobLogger.formatMessage(message), is(expectedMessage));
    }
    
    @Test(expected = IllegalStateException.class)
    public void loadConfigurationWrongFile() {
    	ConfigurationLoader.getConfigurationFrom("WrongFile.properties");
    }
    
    @Test
    public void loadConfigurationValidFile() {
    	Map<String, String> config = ConfigurationLoader.getConfigurationFrom("config.properties");
    	assertThat(config.size(), is(8));
        assertThat(config, not(IsMapContaining.hasKey("wrongKey")));
        assertThat(config, IsMapContaining.hasKey("logfile"));
    }
}
