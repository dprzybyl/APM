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
package com.cognifide.apm.core.scripts;

import com.cognifide.apm.api.scripts.LaunchEnvironment;
import com.cognifide.apm.api.scripts.LaunchMode;
import com.cognifide.apm.api.scripts.Script;
import java.util.Date;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.settings.SlingSettingsService;

/**
 * Due to the ResourceResolver dependency these filters should not be used lazy
 * i.e. in a context where the resolver passed as an argument is no longer alive.
 */
public class ScriptFilters {

  public static Predicate<Script> onInstall(LaunchEnvironment environment, SlingSettingsService slingSettings, String currentHook) {
    return enabled()
        .and(withLaunchMode(LaunchMode.ON_INSTALL))
        .and(withLaunchEnvironment(environment))
        .and(withLaunchRunModes(slingSettings.getRunModes()))
        .and(withLaunchHook(currentHook));
  }

  public static Predicate<Script> onInstallIfModified(LaunchEnvironment environment, SlingSettingsService slingSettings, String currentHook) {
    return enabled()
        .and(withLaunchMode(LaunchMode.ON_INSTALL_IF_MODIFIED))
        .and(withLaunchEnvironment(environment))
        .and(withLaunchRunModes(slingSettings.getRunModes()))
        .and(withLaunchHook(currentHook));
  }

  public static Predicate<Script> onSchedule(LaunchEnvironment environment, SlingSettingsService slingSettings, Date date) {
    return enabled()
        .and(withLaunchMode(LaunchMode.ON_SCHEDULE))
        .and(withLaunchEnvironment(environment))
        .and(withLaunchRunModes(slingSettings.getRunModes()))
        .and(script -> script.getLastExecuted() == null && script.getLaunchSchedule().before(date));
  }

  public static Predicate<Script> onStartup(LaunchEnvironment environment, SlingSettingsService slingSettings) {
    return enabled()
        .and(withLaunchMode(LaunchMode.ON_STARTUP))
        .and(withLaunchEnvironment(environment))
        .and(withLaunchRunModes(slingSettings.getRunModes()));
  }

  public static Predicate<Script> onStartupIfModified(LaunchEnvironment environment, SlingSettingsService slingSettings) {
    return enabled()
        .and(withLaunchMode(LaunchMode.ON_STARTUP_IF_MODIFIED))
        .and(withLaunchEnvironment(environment))
        .and(withLaunchRunModes(slingSettings.getRunModes()));
  }

  private static Predicate<Script> withLaunchHook(String currentHook) {
    return script -> StringUtils.equals(currentHook, script.getLaunchHook());
  }

  private static Predicate<Script> withLaunchEnvironment(LaunchEnvironment environment) {
    return script -> script.getLaunchEnvironment() == LaunchEnvironment.ALL
        || environment == script.getLaunchEnvironment();
  }

  private static Predicate<? super Script> withLaunchRunModes(Set<String> runModes) {
    return script -> script.getLaunchRunModes() == null
        || runModes.containsAll(script.getLaunchRunModes());
  }

  private static Predicate<Script> withLaunchMode(final LaunchMode mode) {
    return script -> script.getLaunchMode() == mode;
  }

  private static Predicate<Script> enabled() {
    return script -> script.isLaunchEnabled();
  }
}
