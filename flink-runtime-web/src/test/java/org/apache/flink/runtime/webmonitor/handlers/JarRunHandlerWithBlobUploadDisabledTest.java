/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.webmonitor.handlers;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.WebOptions;
import org.apache.flink.runtime.jobgraph.JobGraph;
import org.apache.flink.runtime.rest.handler.HandlerRequest;

import org.junit.Assert;
import org.junit.BeforeClass;

/**
 * Tests for the parameter handling of the {@link JarRunHandler} with blob server upload configuration disabled.
 */
public class JarRunHandlerWithBlobUploadDisabledTest extends JarRunHandlerParameterTest {

	@BeforeClass
	public static void setup() throws Exception {
		Configuration config = new Configuration();
		config.setBoolean(WebOptions.BLOB_SERVER_UPLOAD_ENABLE.key(), false);

		init();
		handler = createJarRunHandler(config);
	}

	@Override
	void handleRequest(HandlerRequest<JarRunRequestBody, JarRunMessageParameters> request)
		throws Exception {
		handler.handleRequest(request, restfulGateway).get();
	}

	@Override
	void validateUserJarBlobServerUpload(JobGraph jobGraph) {
		Assert.assertEquals(jobGraph.getUserJarBlobKeys().size(), 0);
	}
}
