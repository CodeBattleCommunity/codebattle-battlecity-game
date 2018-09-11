package com.codenjoy.dojo.battlecity.model.levels;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 - 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.codenjoy.dojo.battlecity.model.GameSettings;
import com.codenjoy.dojo.services.AdminControlService;
import com.codenjoy.dojo.services.settings.Parameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LevelSettingsApplier {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private AdminControlService adminControlService;

    public LevelSettingsApplier(AdminControlService adminControlService) {
        this.adminControlService = adminControlService;
    }

    /**
     * If property is present in Level Setting configuration it will be updated in Game Setting
     * @param settings
     * @param levelSettings
     */
    @SuppressWarnings("unchecked")
    public void applyGameSettings(GameSettings settings, LevelSettings levelSettings) {
        try {
            if (levelSettings != null) {
                PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(GameSettings.class).getPropertyDescriptors();
                Map<String, PropertyDescriptor> descriptorMap = new HashMap<>();

                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    descriptorMap.put(propertyDescriptor.getReadMethod().getName(), propertyDescriptor);
                }

                for (PropertyDescriptor propertyDescriptor :
                        Introspector.getBeanInfo(LevelSettings.class).getPropertyDescriptors()) {

                    Method readMethod = propertyDescriptor.getReadMethod();
                    Optional<?> levelPropertyValue = (Optional<?>) readMethod.invoke(levelSettings);

                    if (levelPropertyValue.isPresent() && descriptorMap.containsKey(readMethod.getName())) {
                        logger.info("Override setting by level parameter {} = {}",
                                propertyDescriptor.getName(), levelPropertyValue.get());

                        PropertyDescriptor gameSettingsProperty = descriptorMap.get(readMethod.getName());
                        Parameter parameter = (Parameter<?>) gameSettingsProperty.getReadMethod().invoke(settings);
                        parameter.update(levelPropertyValue.get());
                    }
                }

                if (levelSettings.getGameSpeed().isPresent()) {
                    logger.info("Override game speed by level setting, speed = " + levelSettings.getGameSpeed().get());

                    adminControlService.changeGameSpeed(levelSettings.getGameSpeed().get());
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Unable to load Level configuration", e);
        }
    }
}
