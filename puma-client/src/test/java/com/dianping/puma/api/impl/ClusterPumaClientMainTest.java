package com.dianping.puma.api.impl;

import com.dianping.puma.api.PumaClient;
import com.dianping.puma.api.PumaClientConfig;
import com.dianping.puma.api.lock.PumaClientLock;
import com.dianping.puma.api.lock.PumaClientLockListener;
import com.dianping.puma.core.dto.BinlogMessage;
import com.dianping.puma.core.event.*;
import com.dianping.puma.core.util.sql.DMLType;
import com.google.common.collect.Lists;
import org.apache.log4j.BasicConfigurator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dianping.puma.core.event.RowChangedEvent.*;

public class ClusterPumaClientMainTest {

	public static void main(String[] args) {

		//BasicConfigurator.configure();
		PumaClient client = new PumaClientConfig()
				.setClientName("technician-client")
				.setDatabase("Profession")
				.setTables(Lists.newArrayList("Technician"))
				.setDdl(false)
				.setDml(true)
				.setTransaction(false)
				.buildClusterPumaClient();

		while (true) {
			try {
				BinlogMessage message = client.get(1, 1, TimeUnit.SECONDS);

				for (Event event: message.getBinlogEvents()) {
					System.out.println(event);
				}

				client.ack(message.getLastBinlogInfo());
			} catch (Throwable t) {

			}
		}
	}
}