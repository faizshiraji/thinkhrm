package com.hrm.thinkerhouse.zkt.command;

import com.hrm.thinkerhouse.zkt.Enum.CommandReplyCodeEnum;

public class ZKCommandReply {

	private final CommandReplyCodeEnum code;
    private final int sessionId;
    private final int replyId;
    private final int[] payloads;

    public ZKCommandReply(CommandReplyCodeEnum code, int sessionId, int replyId, int[] payloads) {
        this.code = code;
        this.sessionId = sessionId;
        this.replyId = replyId;
        this.payloads = payloads;
    }

    /**
     * @return the code
     */
    public CommandReplyCodeEnum getCode() {
        return code;
    }

    /**
     * @return the sessionId
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * @return the replyId
     */
    public int getReplyId() {
        return replyId;
    }

    /**
     * @return the payloads
     */
    public int[] getPayloads() {
        return payloads;
    }
	
}
