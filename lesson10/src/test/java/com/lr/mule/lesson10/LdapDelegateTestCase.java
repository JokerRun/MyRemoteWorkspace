package com.lr.mule.lesson10;

public class LdapDelegateTestCase extends AbstractConfigurationLoaderTestCase 
{

	@Override
	protected String getConfigResources() 
	{
		return "2.ldap-delegate.xml";
	}

}