package com.api.service;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.api.entity.SmsPojo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.ultils.Constants;

@Component
public class SmsService {

	public void send(SmsPojo sms) {
		try {
			Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);

			Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(Constants.FROM_NUMBER), sms.getMessage()).create();
			System.out.println("Here is my id:" + message.getSid());// Unique resource ID created to manage this transaction
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}