package com.blockchain.block;

import java.util.logging.Logger;

import com.blockchain.encypt.SHA256;

public class Block {
	Logger logger = Logger.getLogger(Block.class.getSimpleName());
	private int index;
	private long timestamp;
	private String previous_hash;
	private String hash;
	private BlockData data;
	private int nonce;

	public Block(int index, String previous_hash, long timestamp,
			BlockData data) {
		this.index = index;
		this.timestamp = timestamp;
		this.previous_hash = previous_hash;
		this.data = data;
		this.hash = calculateHash();
		this.nonce = 0;
	}

	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getPrevious_hash() {
		return previous_hash;
	}

	public void setPrevious_hash(String previous_hash) {
		this.previous_hash = previous_hash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public BlockData getData() {
		return data;
	}

	public void setData(BlockData data) {
		this.data = data;
	}

	
	public String calculateHash(){
		String content = this.index + this.previous_hash + this.timestamp + this.data.toString() + this.nonce;
		return SHA256.sha256(content);
	}
	
	public void mineBlock(int difficulty) {
		while (!this.hash.substring(0, difficulty).equals(String.format("%0"+difficulty+"d", 0))) {
			this.nonce++;
			this.hash = calculateHash();
		}
		logger.info("BLOCK MINED: " + this.hash);
	}
	


	@Override
	public String toString() {
		return "Block [index=" + index + ", timestamp=" + timestamp
				+ ", previous_hash=" + previous_hash + ", hash=" + hash
				+ ", data=" + data + "]";
	}
}
