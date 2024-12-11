/*
 * Copyright © 2019 The GWT Project Authors
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
package org.gwtproject.core.client;

import com.google.gwt.junit.client.GWTTestCase;
import elemental2.dom.DomGlobal;
import java.util.ArrayList;
import java.util.List;

public class GWTTest extends GWTTestCase {

  private List<Throwable> caught = new ArrayList<>();

  @Override
  public String getModuleName() {
    return "org.gwtproject.core.Core";
  }

  public void testIsScript() {
    assertTrue(GWT.isScript());
    assertTrue(org.gwtproject.core.shared.GWT.isScript());
  }

  public void testIsClient() {
    assertTrue(GWT.isClient());
    assertTrue(org.gwtproject.core.shared.GWT.isClient());
  }

  public void testReportUncaughtError() {
    GWT.setUncaughtExceptionHandler(caught::add);
    GWT.reportUncaughtException(new RuntimeException());
    DomGlobal.setTimeout(
        (ignore) -> {
          assertEquals(1, caught.size());
          assertEquals("java.lang.JsException", caught.get(0).getClass().getName());
          finishTest();
        },
        1000);
    delayTestFinish(3000);
  }

  @Override
  public boolean catchExceptions() {
    return false;
  }
}
