/**
 * Copyright (C) 2013-2014 all@code-story.net
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
package net.codestory.http;

import net.codestory.http.payload.*;
import net.codestory.http.testhelpers.*;

import org.junit.*;

public class RedirectTest extends AbstractProdWebServerTest {
  @Test
  public void seeOther() {
    configure(routes -> routes
        .get("/", Payload.seeOther("/login"))
        .get("/login", "LOGIN")
    );

    get("/").should().contain("LOGIN");
  }

  @Test
  public void default_to_route_without_slash() {
    configure(routes -> routes
        .get("/dynamic/", "Dynamic")
    );

    get("/dynamic/").should().haveType("text/html").contain("Dynamic");
    get("/dynamic").should().haveType("text/html").contain("Dynamic");
  }

  @Test
  public void redirect_to_index_if_it_exists() {
    get("/section/").should().haveType("text/plain").contain("Hello index");
    get("/section").should().haveType("text/plain").contain("Hello index");
  }
}
