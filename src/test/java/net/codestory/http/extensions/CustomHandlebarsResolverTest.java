/**
 * Copyright (C) 2013 all@code-story.net
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
 * limitations under the License
 */
package net.codestory.http.extensions;

import java.util.function.*;

import net.codestory.http.compilers.CompilerFacade;
import net.codestory.http.misc.Env;
import net.codestory.http.templating.BasicResolver;
import net.codestory.http.testhelpers.AbstractProdWebServerTest;
import org.junit.Test;

public class CustomHandlebarsResolverTest extends AbstractProdWebServerTest {
  @Test
  public void add_resolver() {
    server.configure(routes -> routes.setExtensions(new Extensions() {
      @Override
      public void configureCompilers(Supplier<CompilerFacade> compilers, Env env) {
        compilers.get().addHandlebarResolver(new HelloWorldResolver());
      }
    }));

    get("/extensions/custom_resolver").produces("Hello World");
  }

  static class HelloWorldResolver implements BasicResolver {
    @Override
    public String tag() {
      return "greeting";
    }

    @Override
    public Object resolve(Object context) {
      return "Hello World";
    }
  }
}
