package com.w20e.socrates.process;

import com.w20e.socrates.workflow.ActionExecException;
import com.w20e.socrates.workflow.ActionResult;
import com.w20e.socrates.workflow.AbstractProcessActionImpl;
import com.w20e.socrates.workflow.ProcessContext;
import com.w20e.socrates.workflow.Success;

public class ActionTestImpl extends AbstractProcessActionImpl {
    
    public ActionTestImpl(String id) {
      
      super(id);
    }
    
    public ActionResult exec(ProcessContext ctx)
      throws ActionExecException {
      
      ctx.setProperty(getId(), "ok");
      
      return new Success();         
    }
}
