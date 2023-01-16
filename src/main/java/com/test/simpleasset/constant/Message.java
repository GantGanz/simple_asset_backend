package com.test.simpleasset.constant;

public enum Message {
	INSERTED("Berhasil Ditambahkan"), 
	UPDATED("Berhasil Diperbarui"),
	DELETED("Berhasil Dihapus"); 

	private String message;
	
	private Message(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
