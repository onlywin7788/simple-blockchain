package com.navana.blockchain.core;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Block {

	private String hash;
	private String previousHash;
	private String timestamp;
	private String data;

	public Block(Block block, String data) throws Exception{

		this.data = data;
		if(block != null)
			this.previousHash = block.getHash();
		
		this.timestamp = getTimeStamp();
		this.hash = generateHash();

	}

	public String generateHash() throws Exception
	{
		String resultHash = getBlockHash( 
				previousHash +
				timestamp +
				data
				);

		return resultHash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getBlockHash(String inputData) throws Exception {

		StringBuffer sb = null;

		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

		// double sha-256
		byte[] blockHash = messageDigest.digest(inputData.getBytes("UTF-8"));
		blockHash = messageDigest.digest(blockHash);

		sb = new StringBuffer(); 
		for(int i = 0 ; i < blockHash.length ; i++){
			sb.append(Integer.toString((blockHash[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	public String getTimeStamp()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf.format(timestamp);
	}

	public String getPreviousBlockHash() {
		return previousHash;
	}

	public void setPreviousBlockHash(String previousBlockHash) {
		this.previousHash = previousBlockHash;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}