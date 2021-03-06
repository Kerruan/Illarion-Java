/*
 * This file is part of the Illarion project.
 *
 * Copyright © 2015 - Illarion e.V.
 *
 * Illarion is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Illarion is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
package illarion.client.resources.data;

import illarion.client.graphics.AvatarClothManager;
import illarion.client.graphics.AvatarClothManager.AvatarClothGroup;
import org.illarion.engine.graphic.Sprite;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Arrays;
import java.util.Optional;

/**
 * This is the template that contains the required data to create the graphical representation of a avatar cloth.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
@Immutable
@ThreadSafe
public class AvatarClothTemplate extends AbstractMultiFrameEntityTemplate {
    /**
     * The ID of the parent avatar.
     */
    private final int avatarId;

    /**
     * The ID of the cloth slot this cloth is displayed in.
     */
    @Nonnull
    private final AvatarClothGroup clothGroup;

    /**
     * The constructor of this class.
     *
     * @param id the identification number of the entity
     * @param sprite the sprite used to render the entity
     * @param frames the total amount of frames
     * @param avatarId the ID of the avatar this cloth belongs to
     * @param clothSlot the ID of the slot this cloth belongs to
     */
    public AvatarClothTemplate(int id, @Nonnull Sprite sprite, int frames, int avatarId, int clothSlot) {
        super(id, sprite, frames, 0, null, 0);

        this.avatarId = avatarId;

        Optional<AvatarClothGroup> matchingSlot;
        //noinspection ConstantConditions
        matchingSlot =
                Arrays.asList(AvatarClothGroup.values()).stream().filter(g -> g.getGroupId() == clothSlot).findAny();

        if (!matchingSlot.isPresent()) {
            throw new IllegalArgumentException("cloth slot can't be mapped to a group: " + clothSlot);
        }
        clothGroup = matchingSlot.get();
    }

    public int getAvatarId() {
        return avatarId;
    }

    @Nonnull
    public AvatarClothGroup getClothGroup() {
        return clothGroup;
    }

    @Nonnull
    @Override
    public String toString() {
        return AvatarClothManager.toString(clothGroup);
    }
}
