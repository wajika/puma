package com.dianping.puma.core.lock;

import com.dianping.puma.core.zk.ZkManager;
import com.dianping.puma.core.zk.ZkManagerLoader;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.test.TestingServer;

public class DistributedLockFactory {

	public static DistributedLock newZkDistributedLock(String lockName) {
		ZkManager zkManager = ZkManagerLoader.getLionZkManager();
		CuratorFramework zkClient = zkManager.getZkClient();
		return new ZkDistributedLock(lockName, zkClient);
	}

	public static DistributedLock newLionDistributedLock(String lockName) {
		return null;
	}

	public static DistributedLock newTestZkDistributedLock(String lockName, TestingServer server) {
		ZkManager zkManager = ZkManagerLoader.getTestZkManager(server);
		CuratorFramework zkClient = zkManager.getZkClient();
		return new ZkDistributedLock(lockName, zkClient);
	}
}