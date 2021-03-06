package com.lr.mule.lesson04;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class InvoiceProcessor implements Callable
{
    public Object onCall(final MuleEventContext eventContext) throws Exception
    {
        return eventContext.getMessage().getInvocationProperty("currencyCode");
    }
}
