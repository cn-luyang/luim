package cn.luim.boot.starter.idgen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ID 号段
 *
 * @author yang.lu
 */
@Getter
@Setter
@AllArgsConstructor
public class Segment {

	/** 号段起始值（含） */
	private final long startValue;
	/** 号段结束值（含） */
	private final long endValue;
	/** 当前发号游标（原子自增指针） */
	private final AtomicLong cursor;

	/**
	 * 获取下一个 ID
	 */
	public long nextId() {
		long currentVal = cursor.getAndIncrement();
		return currentVal <= endValue ? currentVal : -1;
	}

	/**
	 * 获取当前号段剩余可用 ID 数量
	 */
	public long availableQuantity() {
		long currentVal = cursor.get();
		return currentVal > endValue ? 0 : endValue - currentVal + 1;
	}

	/**
	 * 获取本段号段总容量大小
	 */
	public long capacity() {
		return endValue - startValue + 1;
	}
}
