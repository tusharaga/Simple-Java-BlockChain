package com.blockchain.chain;

import java.util.Stack;

import com.blockchain.block.Block;
import com.blockchain.block.BlockData;

public class BlockChain {

	private Stack<Block> chain;
	private int difficulty;;

	public BlockChain() {
		this.chain = new Stack<Block>();
		this.chain.push(generateGenesisBlock());
		this.difficulty = 5;
	}

	private Block generateGenesisBlock() {
		int index = 0;
		long timestamp = System.currentTimeMillis();
		String previous_hash = "0";
		BlockData data = new BlockData("Tushar", 30);

		return new Block(index, previous_hash, timestamp, data);
	}

	public void addBlock(Block block) {
		block.setPrevious_hash(this.getLastestBlock().getHash());
		//block.setHash(block.calculateHash());
		block.mineBlock(this.difficulty);
		this.chain.push(block);

	}

	public Block getLastestBlock() {
		return this.chain.lastElement();
	}

	public boolean isChainValid() {
		for (int i = 1; i < this.chain.size(); i++) {
			Block currentBlock = this.chain.get(i);
			Block previousBlock = this.chain.get(i - 1);
			if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
				return false;
			}

			if (!currentBlock.getPrevious_hash()
					.equals(previousBlock.getHash())) {
				return false;
			}
		}
		return true;
	}

}
