package cn.luim.boot.starter.base.utils;

import cn.luim.boot.starter.base.exception.UtilException;
import lombok.experimental.UtilityClass;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 网络操作工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class NetUtil {

	/**
	 * 获取本机 IP 地址对象（InetAddress）
	 * <p>优先获取公网 IPv4，其次获取局域网 IPv4，最后通过 JDK 默认机制保底</p>
	 *
	 * @return 本机 IP 地址，获取失败则返回 null
	 */
	public InetAddress getLocalhost() {
		try {
			// 遍历所有已启动且非环回（Loopback）的网卡，筛选出有效的 IPv4 地址
			var addresses = NetworkInterface.networkInterfaces()
				.filter(ni -> {
					try {
						return ni.isUp() && !ni.isLoopback();
					} catch (SocketException e) {
						return false;
					}
				})
				.flatMap(NetworkInterface::inetAddresses)
				.filter(addr -> !addr.isLoopbackAddress() && addr instanceof Inet4Address)
				.toList();

			if (!addresses.isEmpty()) {
				// 优先返回非局域网（公网）IP
				return addresses.stream()
					.filter(addr -> !addr.isSiteLocalAddress())
					.findFirst()
					// 无公网 IP 时，返回首个局域网 IP
					.orElseGet(addresses::getFirst);
			}
		} catch (SocketException ignored) {
			// 网卡读取异常时忽略，尝试保底逻辑
		}

		// JDK 原生保底机制（注意：在未配置 hosts 的机器上可能存在 DNS 解析延迟）
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return null;
		}
	}

	/**
	 * 获取本机 IP 地址字符串（如 "192.168.1.10"）
	 *
	 * @return IP 地址字符串，获取失败则返回 null
	 */
	public String getLocalhostStr() {
		InetAddress localhost = getLocalhost();
		return (localhost != null) ? localhost.getHostAddress() : null;
	}

	/**
	 * 获取指定 IP 对应的物理网卡 MAC 地址
	 *
	 * @param inetAddress 网络目标 IP 对象
	 * @return MAC 地址字节数组（6 字节），若未获取到或入参为 null 则返回 null
	 */
	public byte[] getHardwareAddress(InetAddress inetAddress) {
		if (inetAddress == null) {
			return null;
		}

		try {
			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
			return (networkInterface != null) ? networkInterface.getHardwareAddress() : null;
		} catch (SocketException e) {
			throw new UtilException(e);
		}
	}

	/**
	 * 获取本机主网卡的 MAC 地址
	 *
	 * @return 本机 MAC 地址字节数组，获取失败则返回 null
	 */
	public byte[] getLocalHardwareAddress() {
		return getHardwareAddress(getLocalhost());
	}
}
