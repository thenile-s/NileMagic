package net.nile.magic.players;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

//loosely defines how a player's mana should behave

// Read nbt gets called after csrtrc, meaning mana will be set to 0 as the nbt tag has 0
// jsut rejoin the world without exiting mc to test persistent shit
//
//
//gj on optimised sync packets

public class PlayerManaComponent implements IManaComponent {

    private static final String currentKey = "cur";

    private static final String maxKey = "max";

    private static final String recoveryKey = "rcv";

    private static final String regenKey = "rgn";

    private static final String degenKey = "dgn";

    private boolean m_valueChanged;

    private float m_current;

    private float m_max;

    private float m_regen;

    private float m_degen;

    private int m_recoveryTicks;

    private PlayerEntity m_player;

    private int m_regenTicks;

    private static final int m_neededRegenTicks = 100;

    private static final String regenTicksKey = "rgt";

    public PlayerManaComponent(PlayerEntity player) {
        m_player = player;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        setMax(tag.getFloat(maxKey));
        setCurrent(tag.getFloat(currentKey));
        setRegen(tag.getFloat(regenKey));
        setDegen(tag.getFloat(degenKey));
        setRecoveryTicks(tag.getInt(recoveryKey));
        m_regenTicks = tag.getInt(regenTicksKey);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {

        // not worth to store degen and regen deltas as nbt since they account for at
        // most 1 mana point anyway
        // maybe change this later anyway
        // what if mana should be based on float, not int :/

        tag.putFloat(maxKey, getMax());
        tag.putFloat(currentKey, getCurrent());
        tag.putFloat(regenKey, getRegen());
        tag.putFloat(degenKey, getDegen());
        tag.putInt(recoveryKey, getRecoveryTicks());
        tag.putInt(regenTicksKey, m_regenTicks);
        // NileMagic.logger.info(tag.toString());

    }

    @Override
    public float getCurrent() {
        return m_current;
    }

    @Override
    public void setCurrent(float value) {
        if (value == m_current) {
            return;
        }
        value = Math.max(value, 0);
        value = Math.min(value, getMax());
        m_current = value;
        m_valueChanged = true;
    }

    @Override
    public float getMax() {
        return m_max;
    }

    @Override
    public void setMax(float value) {
        if (value == m_max) {
            return;
        }
        value = Math.max(value, 0);
        m_max = value;
        m_valueChanged = true;
        setCurrent(Math.min(getCurrent(), getMax()));
    }

    @Override
    public boolean canRecover() {
        return getRecoveryTicks() <= 0;
    }

    @Override
    public int getRecoveryTicks() {
        return m_recoveryTicks;
    }

    @Override
    public void setRecoveryTicks(int value) {
        // no need to sync this for now, so no value changed
        m_recoveryTicks = Math.max(value, 0);
    }

    @Override
    public void recover(float amount, boolean considerCooldown) {

        amount = Math.max(amount, 0);

        if (considerCooldown && !canRecover())
            return;

        setCurrent(getCurrent() + amount);

    }

    @Override
    public void deplete(float amount) {

        amount = Math.max(amount, 0);

        setCurrent(getCurrent() - amount);

        m_regenTicks = 0;
    }

    @Override
    public float getRegen() {
        return m_regen;
    }

    @Override
    public void setRegen(float value) {
        m_regen = Math.max(value, 0);
    }

    @Override
    public void serverTick() {

        setRecoveryTicks(m_recoveryTicks - 1);

        if (m_degen != 0) {
            deplete(m_degen);
        }

        if (m_regenTicks < m_neededRegenTicks) {
            m_regenTicks++;
        } else if(m_regen != 0) {
            recover(m_regen, false);
        }

        if (m_valueChanged) {
            PlayerComponents.MANA.sync(m_player);
            m_valueChanged = false;
        }

        // if(m_valueChanged){
        // m_valueChanged = false;
        // PlayerComponents.MANA.sync(m_player);
        // }
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == m_player;
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        buf.writeFloat(m_max);
        buf.writeFloat(m_current);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        setMax(buf.readFloat());
        setCurrent(buf.readFloat());
    }

    @Override
    public float getDegen() {
        return m_degen;
    }

    @Override
    public void setDegen(float value) {
        m_degen = Math.max(value, 0);
    }

    @Override
    public void reset() {
        
        setMax(0);
        m_current = 0;
        m_regen = 0;
        m_regenTicks = 0;
        m_degen = 0;
    }
}
