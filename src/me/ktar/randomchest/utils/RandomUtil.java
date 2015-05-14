package me.ktar.randomchest.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

	/**
	 * Returns a freshly seeded ThreadLocalRandom instance for random usage
	 * Is much better than the Random class.
	 *
	 * @return re-seeded ThreadLocalRandom instance
	 */
	public static ThreadLocalRandom random() {
		ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
		threadLocalRandom.setSeed(System.nanoTime());
		return threadLocalRandom;
	}
}
