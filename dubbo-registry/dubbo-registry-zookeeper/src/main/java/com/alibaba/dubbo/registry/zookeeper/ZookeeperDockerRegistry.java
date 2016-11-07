/*
 * Copyright © 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.registry.zookeeper;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.remoting.zookeeper.ZookeeperTransporter;

/**
 * 
 * @author yanghe
 * @since 3.0.1
 */
public class ZookeeperDockerRegistry extends ZookeeperRegistry {

	public ZookeeperDockerRegistry(final URL url, final ZookeeperTransporter zookeeperTransporter) {
		super(url, zookeeperTransporter);
	}

	@Override
	protected void doRegister(final URL url) {
		final URL proxyURL = proxyHost(proxyPort(url));
		super.doRegister(proxyURL);
	}

	@Override
	protected void doUnregister(final URL url) {
		final URL proxyURL = proxyHost(proxyPort(url));
		super.doUnregister(proxyURL);
	}
	
	protected URL proxyPort(final URL url) {
		final String proxyPort = System.getenv("PROXY_PORT_" + url.getPort());
		if (!StringUtils.isBlank(proxyPort)) {
			try {
				return url.setPort(Integer.parseInt(proxyPort));
			} catch (final NumberFormatException e) {
				// ignore
			}
		}

		return url;
	}
	
	protected URL proxyHost(final URL url) {
		final String proxyHost = System.getenv("PROXY_HOST");
		if (!StringUtils.isBlank(proxyHost)) {
			return url.setHost(proxyHost);
		}

		return url;
	}
}
