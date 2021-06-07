/*-
 * ========================LICENSE_START=================================
 * AEM Permission Management
 * %%
 * Copyright (C) 2013 Wunderman Thompson Technology
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
package com.cognifide.apm.checks.actions.permissions;

import com.cognifide.apm.api.actions.Action;
import com.cognifide.apm.api.actions.annotations.Mapper;
import com.cognifide.apm.api.actions.annotations.Mapping;
import com.cognifide.apm.api.actions.annotations.Named;
import com.cognifide.apm.checks.actions.ActionGroup;
import java.util.List;

@Mapper(value = "CHECK-ALLOW", group = ActionGroup.CHECKS)
public final class CheckAllowMapper {

  public static final String REFERENCE = "Check that specific permissions are allowed for current authorizable"
      + " on specified path.";

  @Mapping(
      reference = REFERENCE
  )
  public Action mapAction(String id, String path, List<String> permissions, @Named("glob") String glob) {
    return new CheckPermissions(id, path, glob, permissions, true);
  }
}
