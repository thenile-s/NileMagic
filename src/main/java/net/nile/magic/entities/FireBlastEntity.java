package net.nile.magic.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireBlastEntity extends OwnedSpellEntity {

    private Entity m_owner;

    private boolean m_shouldLinger;

    private boolean m_lingering;

    private int m_lingeringTicks;

    private int m_maxLingeringTicks;

    private float m_damage;

    public void errupt(){
        world.getOtherEntities(this, this.getBoundingBox(), this::isEntityEnemy).forEach(
                    (entity)->{
                        entity.damage(DamageSource.magic(this, m_owner), getDamage());
                    }
                );
    }

    protected void onCollision(HitResult hitResult) {

        HitResult.Type type = hitResult.getType();
        
        if (type == HitResult.Type.ENTITY) {
           this.onEntityHit((EntityHitResult)hitResult);
        } else if (type == HitResult.Type.BLOCK) {
           this.onBlockHit((BlockHitResult)hitResult);
        }
  
        if(type != HitResult.Type.MISS){
            if(m_shouldLinger){
                setVelocity(Vec3d.ZERO);
                m_lingering = true;
            }
            else{
                remove();
            }
        }
     }
  
    protected void onEntityHit(EntityHitResult entityHitResult) {
        entityHitResult.getEntity().damage(DamageSource.magic(this, m_owner), getDamage() * 2);
        errupt();
    }
  
    public float getDamage() {
        return m_damage;
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        errupt();
    }

    public FireBlastEntity(EntityType<?> type, World world) {
        this(world, null, 10);
    }

    public FireBlastEntity(World world, Entity owner, float damage){
        super(Entities.FIRE_BLAST, world);
        m_damage = damage;
    }

    public FireBlastEntity(World world, Entity owner, float damage, int maxLingeringTicks){
        super(Entities.FIRE_BLAST, world);
        m_damage = damage;
        m_shouldLinger = true;
        m_maxLingeringTicks = Math.max(0, maxLingeringTicks);
    }

    @Override
    protected void initDataTracker() {
        // :/
        
    }

    @Override
    protected void readCustomDataFromTag(CompoundTag tag) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void writeCustomDataToTag(CompoundTag tag) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tick() {
        //ArrowEntity
        
        //This method (ProjectileUtil.getCollision) will return a block collision only if there was no 
        //entity collision! care!
        //this time, its ok because we wil lremove the fire blast regardless of what it hit, as long as
        //it hit something
        //however, tehre could be cases where we dont respond to a block collision if we got an entity collision too!

        if(m_lingering){
            
            m_lingeringTicks++;
            if(m_lingeringTicks >= m_maxLingeringTicks){
                remove();
            }
            else{
                errupt();
            }
        }
        else{
            onCollision(ProjectileUtil.getCollision(this, this::isEntityEnemy));
        }
        

        Vec3d newPos = getPos().add(getVelocity());

        updatePosition(newPos.x, newPos.y, newPos.z);

        super.tick();
    }

    @Override
    public Entity getOwner() {
        return m_owner;
    }
    
}
