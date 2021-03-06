/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.serviceregistry.consumer;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import io.servicecomb.serviceregistry.RegistryUtils;
import mockit.Expectations;

public class TestMicroserviceVersion {
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void constructInvalid() {
    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage(Matchers.is("Invalid microserviceId invalid."));

    new Expectations(RegistryUtils.class) {
      {
        RegistryUtils.getMicroservice("invalid");
        result = null;
      }
    };
    new MicroserviceVersion("invalid");
  }

  @Test
  public void constructNormal() {
    MicroserviceVersion microserviceVersion = MicroserviceVersionTestUtils.createMicroserviceVersion("1", "1.0.0");
    Assert.assertEquals("1", microserviceVersion.getMicroservice().getServiceId());
    Assert.assertEquals("1.0.0", microserviceVersion.getVersion().getVersion());
  }
}
