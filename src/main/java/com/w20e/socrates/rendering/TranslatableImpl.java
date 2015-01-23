package com.w20e.socrates.rendering;

public class TranslatableImpl implements Translatable {

	public static final TranslatableImpl EMPTY = new TranslatableImpl("");

	String msgid;
	String msgctx;
	String text;

	public TranslatableImpl() {
	}
	
	public TranslatableImpl(String text) {
		this.text = text;
	}
	
	public String getMsgid() {
		if (this.msgid != null && !"".equals(this.msgid)) {
			return this.msgid;
		} else {
			return this.text;
		}
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	
	public String getMsgctx() {
		return this.msgctx;
	}

	public void setMsgctx(String msgctx) {
		this.msgctx = msgctx;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public String toString() {
		return this.text;
	}
}
