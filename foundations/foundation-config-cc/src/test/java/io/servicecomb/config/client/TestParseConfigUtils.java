/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.config.client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.servicecomb.config.archaius.sources.ConfigCenterConfigurationSourceImpl;
import io.servicecomb.config.archaius.sources.ConfigCenterConfigurationSourceImpl.UpdateHandler;
import io.servicecomb.config.client.ParseConfigUtils;
import mockit.Deencapsulation;

/**
 * @author
 */
public class TestParseConfigUtils {

  private ConfigCenterConfigurationSourceImpl configCenterSource = new ConfigCenterConfigurationSourceImpl();

  private UpdateHandler uh = configCenterSource.new UpdateHandler();

  private ParseConfigUtils pc = new ParseConfigUtils(uh);

  @Test
  public void testNotifyItemsChangedNeedRefresh() {

    boolean status = true;
    Map<String, Object> before = new HashMap<String, Object>();
    Map<String, Object> after = new HashMap<String, Object>();
    try {
      Deencapsulation.invoke(pc, "notifyItemsChangedNeedRefresh", before, after);
    } catch (Exception e) {
      status = false;
    }
    Assert.assertTrue(status);

    // Check with valid before object
    status = true;
    before.put("test", "testValue");
    try {
      Deencapsulation.invoke(pc, "notifyItemsChangedNeedRefresh", before, after);
    } catch (Exception e) {
      status = false;
    }
    Assert.assertTrue(status);

    // Check with valid after object
    status = true;
    after.put("test", "testValue2");
    try {
      Deencapsulation.invoke(pc, "notifyItemsChangedNeedRefresh", before, after);
    } catch (Exception e) {
      status = false;
    }
    Assert.assertTrue(status);

    // Check with valid localItems object
    status = true;
    try {
      Deencapsulation.invoke(pc, "notifyItemsChangedNeedRefresh", before, after);
    } catch (Exception e) {
      status = false;
    }
    Assert.assertTrue(status);
  }
}
