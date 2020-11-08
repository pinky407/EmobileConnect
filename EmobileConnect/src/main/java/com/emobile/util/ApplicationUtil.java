package com.emobile.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class ApplicationUtil {

    private static final Logger log = LoggerFactory.getLogger(ApplicationUtil.class);

    public static String convertToJSON(Object obj){
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            log.error("Error while converting to JSON {} ", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void copyProperties(Object source, Object target){
        if(source==null){
            log.warn("The source object is null while copying the properties");
            //throw back
        }
        log.debug("The source object is while copying the properties {} ", source.toString());
        BeanUtils.copyProperties(source, target);
        log.debug("The target object is while copying the properties {} ", target.toString());
    }

}
