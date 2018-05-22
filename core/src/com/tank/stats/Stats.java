package com.tank.stats;

import java.util.LinkedHashMap;

public class Stats {
	private LinkedHashMap<String, Integer> stats;

	public int getStatValue(String stat) {
		return stats.get(stat);
	}
	public boolean hasStat(String stat) {
		return (stats.containsKey(stat));
	}
}
