package com.navana.blockchain.main;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;
import com.navana.blockchain.core.Block;

public class TestMain {

	public static void main(String[] args) throws Exception {

		ArrayList<Block> blockchain = new ArrayList<Block>();
		
		for(int i = 1 ; i <= 5 ; i++){
			
			String data = "data_" + i;
			
			if(i == 1) // genesis block
				blockchain.add(new Block(null, data));
			
			blockchain.add(new Block(blockchain.get(blockchain.size()-1), data));
			
			Thread.sleep(100);
		}

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockchainJson);
		
	}
}