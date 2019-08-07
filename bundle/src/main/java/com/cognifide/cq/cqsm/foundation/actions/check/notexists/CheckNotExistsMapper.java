/*-
 * ========================LICENSE_START=================================
 * AEM Permission Management
 * %%
 * Copyright (C) 2013 Cognifide Limited
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */
package com.cognifide.cq.cqsm.foundation.actions.check.notexists;

import com.cognifide.cq.cqsm.api.actions.Action;
import com.cognifide.cq.cqsm.api.actions.annotations.Mapper;
import com.cognifide.cq.cqsm.api.actions.annotations.Mapping;
import java.util.Collections;
import java.util.List;

@Mapper("check-not-exists")
public final class CheckNotExistsMapper {

  public static final String REFERENCE = "Verify that specific authorizables do not exist.";

  @Mapping(
      value = "CHECK-NOT-EXISTS",
      args = {"id"},
      reference = REFERENCE
  )
  public Action mapAction(String id) {
    return mapAction(Collections.singletonList(id));
  }

  @Mapping(
      value = "CHECK-NOT-EXISTS",
      args = {"ids"},
      reference = REFERENCE
  )
  public Action mapAction(List<String> ids) {
    return new CheckNotExists(ids);
  }
}
