package com.lr.mule.lesson02;

public class MessageSourcesTestCase extends AbstractConfigurationLoaderTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "05_message-sources.xml,globleconfig.xml";
    }
}

