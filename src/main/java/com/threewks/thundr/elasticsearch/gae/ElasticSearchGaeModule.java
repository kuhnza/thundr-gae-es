/*
 * This file is a component of thundr, a software library from 3wks.
 * Read more: http://www.3wks.com.au/thundr
 * Copyright (C) 2013 3wks, <thundr@3wks.com.au>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.threewks.thundr.elasticsearch.gae;

import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.threewks.thundr.gae.GaeModule;
import com.threewks.thundr.http.service.HttpService;
import com.threewks.thundr.http.service.gae.HttpServiceImpl;
import com.threewks.thundr.injection.BaseModule;
import com.threewks.thundr.injection.UpdatableInjectionContext;
import com.threewks.thundr.module.DependencyRegistry;

public class ElasticSearchGaeModule extends BaseModule {
	@Override
	public void requires(DependencyRegistry dependencyRegistry) {
		super.requires(dependencyRegistry);
		dependencyRegistry.addDependency(GaeModule.class);
	}

	@Override
	public void configure(UpdatableInjectionContext injectionContext) {
		super.configure(injectionContext);
		injectionContext.inject(URLFetchServiceFactory.getURLFetchService()).as(URLFetchService.class);
		injectionContext.inject(HttpServiceImpl.class).as(HttpService.class);

		ElasticSearchConfig config = new ElasticSearchConfig();
		config.setUrl(injectionContext.get(String.class, "elasticSearchUrl"));
		injectionContext.inject(config).as(ElasticSearchConfig.class);

		injectionContext.inject(ElasticSearchClient.class).as(ElasticSearchClient.class);
	}
}
