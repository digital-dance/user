package com.digital.dance.home;

import com.alibaba.rocketmq.common.message.MessageExt;
import com.digital.dance.framework.message.handler.MQMessageHandler;

import com.digital.dance.user.commons.Log;
import com.digital.dance.user.commons.StringTools;

public class DefaultMQMessageHandler implements MQMessageHandler {
	Log log = new Log(DefaultMQMessageHandler.class);
	@Override
	public void handlerMessage(MessageExt paramMessageExt) throws Exception {
		// TODO Auto-generated method stub
		
		log.debug("Topic:" + paramMessageExt.getTopic() + ", msg id:" + paramMessageExt.getMsgId() + ", received MQMessage:" + StringTools.getStrFromBytes(paramMessageExt.getBody()));
	}

}
