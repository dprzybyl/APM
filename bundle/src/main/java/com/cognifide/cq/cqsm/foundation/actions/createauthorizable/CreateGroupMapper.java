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
package com.cognifide.cq.cqsm.foundation.actions.createauthorizable;

import static com.cognifide.cq.cqsm.foundation.actions.CommonFlags.IF_NOT_EXISTS;
import static com.cognifide.cq.cqsm.foundation.actions.createauthorizable.CreateAuthorizableStrategy.GROUP;

import com.cognifide.cq.cqsm.api.actions.Action;
import com.cognifide.cq.cqsm.api.actions.annotations.Flags;
import com.cognifide.cq.cqsm.api.actions.annotations.Mapper;
import com.cognifide.cq.cqsm.api.actions.annotations.Mapping;
import com.cognifide.cq.cqsm.api.actions.annotations.Named;
import java.util.List;

@Mapper("create-group")
public class CreateGroupMapper {

	public static final String REFERENCE = "Create a group.";

	@Mapping(
      value = "CREATE-GROUP",
			args = {"groupId", "path"},
			reference = REFERENCE
	)
  public Action mapAction(String groupId, @Named("path") String path, @Flags List<String> flags) {
    return new CreateAuthorizable(groupId, null, path, flags.contains(IF_NOT_EXISTS), GROUP);
	}

}
