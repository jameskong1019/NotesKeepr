/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author james
 */
public class DebugTag extends TagSupport {

     @Override
    public int doStartTag() throws JspException {
        String domain = pageContext.getRequest().getServerName();
        if (! (domain.contains("localhost") || domain.startsWith("test")))
            return SKIP_BODY;
        
        String debug = pageContext.getRequest().getParameter("debug");
        if (debug == null)
            return SKIP_BODY;
            
        return EVAL_BODY_INCLUDE;
    }
    
}
