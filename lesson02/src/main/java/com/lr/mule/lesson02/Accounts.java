package com.lr.mule.lesson02;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/accounts")
public class Accounts
{
	@POST
	@Produces("application/text")
	public String returnString() {
		return "implicit";
	}
    // STUB
}
