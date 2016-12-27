package gu.admin.codeMentor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class MentorCtr {

    @Autowired
    private MentorSvc codeSvc;
    
    static final Logger LOGGER = LoggerFactory.getLogger(MentorCtr.class);
    

    @RequestMapping(value = "/mentorTest")
    public String codeTest() {
        
        return "codementor/test";
    }
   
}
