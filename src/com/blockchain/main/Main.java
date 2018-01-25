package com.blockchain.main;

import java.util.Date;
import java.util.logging.Logger;

import com.blockchain.block.Block;
import com.blockchain.block.BlockData;
import com.blockchain.chain.BlockChain;

public class Main {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Main.class.getSimpleName());
		
		
		BlockChain blockChain = new BlockChain();
		logger.info(blockChain.getLastestBlock().toString());
		logger.info(blockChain.toString());
		
		// Validate Chain ..
		logger.info("Is Chain Valid ? " + blockChain.isChainValid());
		blockChain.getLastestBlock().setData(new BlockData("T", 25));
		logger.info("Is Chain Valid ? " + blockChain.isChainValid());
		
		
		// Testing Proof of Work ... 
		logger.info("Mining block 1...");
		blockChain.addBlock(new Block(1, "", System.currentTimeMillis(), new BlockData("S", 25)));
		logger.info("Mining block 2...");
		blockChain.addBlock(new Block(2, "", System.currentTimeMillis(), new BlockData("SA", 25)));

	}
}
