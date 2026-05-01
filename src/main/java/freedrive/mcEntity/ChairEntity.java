package freedrive.mcEntity;

import cn.nukkit.entity.Entity;
import cn.nukkit.entity.passive.EntityHorse;
import cn.nukkit.entity.passive.EntityHorseBase;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于马实体的载具。
 * <p>
 * 继承 EntityHorseBase 获得完整的骑乘 + 玩家移动控制能力。
 * - 可见的马模型（不隐身）
 * - 玩家骑上去后可控制前后左右移动，跟真马一样
 * - 免疫一切伤害，其他玩家无法破坏
 * - 下马时自动销毁实体
 * <p>
 * 不使用 AI 寻路，未骑乘时静止不动。
 */
public class ChairEntity extends EntityHorseBase {

    public ChairEntity(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    /**
     * 客户端渲染为马（NETWORK_ID = 23）。
     */
    @Override
    public int getNetworkId() {
        return EntityHorse.NETWORK_ID; // 23
    }

    /**
     * 免疫一切伤害 — 其他玩家（包括创造模式）无法破坏。
     */
    @Override
    public boolean attack(EntityDamageEvent source) {
        return false;
    }

    /**
     * 固定速度：5 blocks/s (0.25 blocks/tick)
     */
    @Override
    public double getSpeed() {
        return 0.25f;
    }

    /**
     * 跨步高度限制为 0.5 格（半砖），只能走上半砖台阶。
     */
    @Override
    protected double getStepHeight() {
        return 0.5;
    }

    /**
     * 每 tick 检查：没有乘客就立刻销毁。
     * 有乘客时锁死 motionY = 0，禁止跳跃。
     */
    @Override
    public boolean onUpdate(int currentTick) {
        if (this.passengers.isEmpty()) {
            this.close();
            return false;
        }
        // 锁死垂直速度，禁止跳跃
        this.motionY = 0;
        return super.onUpdate(currentTick);
    }

    /**
     * 阻止自动生成随机漫游目标，未骑乘时保持静止。
     */
    @Override
    protected void checkTarget() {
        // 不调用 super，不做任何寻路
    }

    /**
     * 玩家下马时自动销毁实体。
     */
    @Override
    public boolean dismountEntity(Entity entity, boolean sendLinks) {
        if (super.dismountEntity(entity, sendLinks)) {
            this.close();
            return true;
        }
        return false;
    }

    /**
     * 关闭时清理所有乘客，防止残留。
     */
    @Override
    public void close() {
        if (this.passengers != null && !this.passengers.isEmpty()) {
            List<Entity> copy = new ArrayList<>(this.passengers);
            for (Entity passenger : copy) {
                this.dismountEntity(passenger);
            }
        }
        super.close();
    }
}
