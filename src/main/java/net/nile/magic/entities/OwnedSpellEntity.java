package net.nile.magic.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class OwnedSpellEntity extends BaseEntity {
   public OwnedSpellEntity(EntityType<?> type, World world) {
        super(type, world);
    }

 public abstract Entity getOwner();

    public boolean isEntityEnemy(Entity entity) {
        return 
        !entity.isSpectator() 
        && entity.collides()
        && (getOwner() == null || (entity != getOwner() && !entity.isTeammate(getOwner())));
    }
}
