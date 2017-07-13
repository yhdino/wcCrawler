package com.zhsz.utils.utils.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;

import com.zhsz.utils.logger.ZhszLogger;

public class MyBitisCache implements Cache {

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private ICache cache = CacheFactory.buildCacheUtil();

	/**
	 * The cache id.
	 */
	private final String id;

	/**
	 * 
	 * 
	 * @param id
	 */
	public MyBitisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	public void clear() {
		cache.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getObject(Object key) {
		try {
			ZhszLogger.info("--------开始取缓存数据--------", MyBitisCache.class);
			Object cachedElement = cache.getObject(key.toString());
			if (cachedElement == null) {
				return null;
			}
			return cachedElement;

		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSize() {
		try {
			return cache.getSize();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void putObject(Object key, Object value) {
		try {
			ZhszLogger.info("--------开始存缓存数据--------", MyBitisCache.class);
			cache.setObject(key.toString(), value);
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Object removeObject(Object key) {
		try {
			ZhszLogger.info("--------开始删除缓存数据--------", MyBitisCache.class);
			Object obj = this.getObject(key);
			cache.removeObject(key.toString());
			return obj;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Cache)) {
			return false;
		}

		Cache otherCache = (Cache) obj;
		return this.id.equals(otherCache.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "MemCache {" + this.id + "}";
	}

}
