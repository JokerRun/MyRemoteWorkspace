package com.lr.mule.lesson04.model.json;

import java.util.List;

public class Provider
{
    private String name;

    private List<PhoneNumber> phoneNumbers;  //<co id="lst_04_provider_phonenumbers"/> 

    private List<EmailAddress> emailAddresses; //<co id="lst_04_provider_email_addresses"/>

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(List<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

}
