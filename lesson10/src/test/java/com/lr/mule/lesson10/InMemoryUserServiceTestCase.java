package com.lr.mule.lesson10;


public class InMemoryUserServiceTestCase extends AbstractConfigurationLoaderTestCase 
{

	@Override
	protected String getConfigResources() 
	{
		return "1.in-memory-user-service.xml";
	}

}
